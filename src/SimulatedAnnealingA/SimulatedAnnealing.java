package SimulatedAnnealingA;

import java.util.ArrayList;

import Core.Container;
import Core.ParcelList;
import Core.Parcels;
import Core.Pentominoes;
import Core.Rotations;

// TODO: implement pentominoes!!!

public class SimulatedAnnealing {
    private double beta = 0.2; // cooling factor
    private double alpha = 0.002; // heating factor

    private Container bestContainer;
    private ArrayList<Parcels> parcelsList;
    private int[][] sequence;

    private static int[][][][] parcelARotations;
    private static int[][][][] parcelBRotations;
    private static int[][][][] parcelCRotations;

    private static int[][][][] pentominoeLRotations;
    private static int[][][][] pentominoePRotations;
    private static int[][][][] pentominoeTRotations;

    private static final double INITIAL_TEMPERATURE = 0.2;
    private static final int TOTAL_ROTATIONS = 6; // 24 if pentominoes (24 for L & P and 12 for T)

    private static long timeToRun = 6000;

    private static boolean valueOrVolume = true; // true = best value, false = best volume
    private static String parcORpent = "abc"; // "pent" for pentominoes

    /**
     * Default constructor. The list is set to the maximum amount of each parcels to
     * be used.
     */
    public SimulatedAnnealing() {
        initiateParcelsRotations();

        ParcelList pList = new ParcelList();

        if (parcORpent.equals("abc")) {
            pList.addWithAmount(new Parcels("A", parcelARotations.length, 3, 0, 30));
            pList.addWithAmount(new Parcels("B", parcelBRotations.length, 4, 0, 10));
            pList.addWithAmount(new Parcels("C", parcelCRotations.length, 5, 0, 22));
        }
        // } else {
        //     pList.addWithAmount(new Pentominoes("L", pentominoeLRotations.length, 3, 10));
        //     pList.addWithAmount(new Pentominoes("P", pentominoePRotations.length, 4, 10));
        //     pList.addWithAmount(new Pentominoes("T", pentominoeTRotations.length, 5, 10));
        // }

        parcelsList = pList.getFullArray();

        sequence = new int[2][pList.getTotalSize()]; // could also have 2 separate arrays: one for order and one for
                                                     // rotation
    }

    /**
     * Constructor of this class. The list that will be used to fill a Container.
     * 
     * @param pList
     */
    public SimulatedAnnealing(Parcels[] pList) {
        parcelsList = new ArrayList<>();
        for (Parcels p : pList) {
            parcelsList.add(p.copy());
        }

        sequence = new int[2][parcelsList.size()];
    }

    void initiateParcelsRotations() {
        if (parcORpent.equals("abc")) {
            parcelARotations = Rotations.getA();
            parcelBRotations = Rotations.getB();
            parcelCRotations = Rotations.getC();
        } else {
            pentominoeLRotations = Rotations.getL();
            pentominoePRotations = Rotations.getP();
            pentominoeTRotations = Rotations.getT();
        }
    }

    /**
     * Method used to fill a Container and return it filled.
     * 
     * @return The filled Container.
     */
    public Container fillContainer() {
        simulate();

        return bestContainer;
    }

    /**
     * The simulated annealing loop. It runs for a given time and sets the best
     * container to the best result it found.
     */
    private void simulate() {
        // variable initialization
        long startTime = System.currentTimeMillis();
        double temperature = INITIAL_TEMPERATURE;
        // initialize the sequence
        generateNeighbourhood();

        // construct the Container
        bestContainer = new Container();
        bestContainer = fill(sequence);

        // gets the unit to initialize bestUnit variable. Unit: volume or value
        int bestUnit;
        if (valueOrVolume)
            bestUnit = bestContainer.getValue();
        else
            bestUnit = bestContainer.getParcelVolume();

        // annealing loop
        while (System.currentTimeMillis() - startTime < timeToRun && bestContainer.getGapAmount() > 0) {

            // creates neighbourhood
            ArrayList<int[][]> neighbourhood = randomizeNeighbourhood(sequence);

            // filling the container with a random neighbour
            Container newT = fill(neighbourhood.get((int) (Math.random() * neighbourhood.size())));

            int unit;

            if (valueOrVolume) {
                unit = newT.getValue();
            } else {
                unit = newT.getParcelVolume();
            }

            boolean better = false; // check if filling is better in new neighbour

            if (unit > bestUnit) {
                better = true;
            } else {
                temperature = temperature / (1 + (beta * temperature));
                double delta = (bestUnit - unit) / bestUnit;
                double i = Math.random();

                if (i < Math.exp(-delta / temperature)) {
                    better = true; // the cooler it gets the smaller chance there is for this to be activated
                } else { // reheats the temperature
                    temperature = temperature / (1 - (alpha * temperature));
                }
            }

            if (better) {
                bestContainer = newT.copy();
                bestUnit = unit;
            }
        }
        System.out.println(bestUnit);
    }

    /**
     * Generates a random sequence to fill the container. The sequence represents
     * the placing order of the parcels to be placed in the container.
     */
    private void generateNeighbourhood() {
        for (int j = 0; j < sequence[0].length; j++) {
            int columnIndex;
            boolean contains;
            do {
                contains = false;
                columnIndex = (int) (Math.random() * sequence[0].length); // TODO: could be changed
                for (int k = j - 1; k > 0; k--)
                    if (sequence[0][k] == columnIndex)
                        contains = true;
            } while (contains);
            sequence[0][j] = columnIndex;
        }

        // rotation vector definition
        for (int i = 0; i < sequence[0].length; i++) {
            sequence[1][i] = (int) (Math.random() * TOTAL_ROTATIONS); // tot_rot = 6
        }
    }

    /**
     * Method that generates the neighbourhood of a given sequence.
     * 
     * @param s - the given sequence from which the neighbourhood is generated
     * @return - ArrayList of sequences representing the created neighbourhood
     */
    private ArrayList<int[][]> randomizeNeighbourhood(int[][] s) {
        ArrayList<int[][]> sequenceList = new ArrayList<>();

        // no changes
        sequenceList.add(s);

        // one change
        sequenceList.add(switchBox(s, (int) (Math.random() * s.length)));

        // 2 changes
        sequenceList.add(switchBox(switchBox(s, 1), 0));
        // sequenceList.add(switchBox(switchBox(s, 2), 1));
        // sequenceList.add(switchBox(switchBox(s, 2), 0));
        // sequenceList.add(switchBox(switchBox(s, 3), 0));
        // sequenceList.add(switchBox(switchBox(s, 3), 1));
        // sequenceList.add(switchBox(switchBox(s, 3), 2));

        // // 3 changes
        // sequenceList.add(switchBox(switchBox(switchBox(s, 2), 1), 0));
        // sequenceList.add(switchBox(switchBox(switchBox(s, 3), 2), 1));
        // sequenceList.add(switchBox(switchBox(switchBox(s, 3), 1), 0));
        // sequenceList.add(switchBox(switchBox(switchBox(s, 3), 2), 0));

        // // 4changes
        // sequenceList.add(switchBox(switchBox(switchBox(switchBox(s, 3), 2), 1), 0));
        return sequenceList;
    }

    /**
     * Method used to switch two items inside a sequence.
     * 
     * @param s     - the given sequence
     * @param index - the index of the sequence inside which the switch occurs
     * @return - the sequence with the permutated items
     */
    private int[][] switchBox(int[][] s, int index) {
        int a = (int) (Math.random() * s[0].length);
        int b;

        do {
            b = (int) (Math.random() * s[0].length);
        } while (b == a);

        int[][] newS = new int[s.length][s[0].length];

        for (int i = 0; i < s.length; i++) {
            System.arraycopy(s[i], 0, newS[i], 0, s[0].length);
        }

        int temp = newS[index][a];
        newS[index][a] = newS[index][b];
        newS[index][b] = temp;

        return newS;
    }

    /**
     * Method used to fill a container with a sequence of parcels.
     * 
     * @param s - the sequence used to fill the container
     * @return - the filled container
     */
    private Container fill(int[][] s) {
        Container cont = new Container();

        for (int i = 0; i < s[0].length; i++) {
            Parcels toPlace = parcelsList.get(s[0][i]).copy(); // make copy because of rotation
            int[][][] representationParcel;

            if (toPlace.getName().equals("A")) {
                representationParcel = parcelARotations[s[1][s[0][i]] % 3];
            } else if (toPlace.getName().equals("B")) {
                representationParcel = parcelBRotations[s[1][s[0][i]]];
            } else {
                representationParcel = parcelCRotations[0];
            }

            boolean stopPosition = true; // when the parcel is placed the for loops stop
            for (int x = 0; x < cont.getLength() && stopPosition; x++) {
                for (int y = 0; y < cont.getWidth() && stopPosition; y++) {
                    for (int z = 0; z < cont.getHeight() && stopPosition; z++) {
                        if (cont.checkIfFits(representationParcel, x, y, z)) {
                            cont.addParcel(representationParcel, x, y, z);
                            cont.increaseValue(toPlace.getValue());
                            stopPosition = false;
                        }
                    }
                }
            }
        }

        return cont;
    }

}
