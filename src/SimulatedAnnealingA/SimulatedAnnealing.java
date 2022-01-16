package SimulatedAnnealingA;

import java.util.ArrayList;

import Core.Container;
import Core.ParcelList;
import Core.Parcels;
import Core.PentominoeList;
import Core.Pentominoes;
import Core.Rotations;

public class SimulatedAnnealing {
    private static double beta; // cooling factor
    private static double alpha; // heating factor
    private static boolean valueOrVolume = true; // true = best value, false = best volume
    private static String parcORpent; // "abc" for A/B/C parcels, "pent" for pentominoes

    public Container bestContainer;
    private ArrayList<Parcels> parcelsList;
    private ArrayList<Pentominoes> pentominoesList;
    private int[][] configuration;

    private static int[][][][] parcelARotations;
    private static int[][][][] parcelBRotations;
    private static int[][][][] parcelCRotations;

    private static int[][][][] pentominoeLRotations;
    private static int[][][][] pentominoePRotations;
    private static int[][][][] pentominoeTRotations;

    private static final double INITIAL_TEMPERATURE = 0.2;
    private static final int ROTATIONS_PARCELS = 6; // 3 for A, 6 for B and 1 for C
    private static final int ROTATIONS_PENTOMINOES = 24; // 24 for L & P and 12 for T

    private static long timeToRun = 10000;

    /**
     * Default constructor. The list is set to the maximum amount of each parcels to
     * be used.
     * 
     * @param parcelType - string that is the type of parcel "abc" or "pent"
     */
    public SimulatedAnnealing(String parcelType) {
        setTypeParcel(parcelType);
        initiateParcelsRotations();

        if (parcORpent.equals("abc")) {
            ParcelList parcelList = new ParcelList();

            parcelList.addWithAmount(new Parcels("A", parcelARotations.length, 3, 12));
            parcelList.addWithAmount(new Parcels("B", parcelBRotations.length, 4, 35));
            parcelList.addWithAmount(new Parcels("C", parcelCRotations.length, 5, 187));

            parcelsList = parcelList.getFullArray();

            configuration = new int[2][parcelList.getTotalSize()]; // could also have 2 separate arrays: one for order
                                                                   // and
            // one for rotation
        } else {
            PentominoeList pentList = new PentominoeList();

            pentList.addWithAmount(new Pentominoes("T", pentominoeTRotations.length, 5,
                    187));
            pentList.addWithAmount(new Pentominoes("P", pentominoePRotations.length, 4,
                    35));
            pentList.addWithAmount(new Pentominoes("L", pentominoeLRotations.length, 3,
                    12));

            pentominoesList = pentList.getFullArray();

            configuration = new int[2][pentList.getTotalSize()]; // could also have 2 separate arrays: one for order and
            // one for rotation
        }

    }

    //

    /**
     * Constructor of this class. The list that will be used to fill a Container.
     * 
     * @param pList - list of parcels
     */
    public SimulatedAnnealing(Parcels[] pList) {
        parcelsList = new ArrayList<>();
        for (Parcels p : pList) {
            parcelsList.add(p.copy());
        }

        configuration = new int[2][parcelsList.size()];
    }

    /**
     * Constructor of this class. The list that will be used to fill a container.
     * 
     * @param pList - list of pentominoes
     */
    public SimulatedAnnealing(Pentominoes[] pList) {
        pentominoesList = new ArrayList<>();
        for (Pentominoes p : pList) {
            pentominoesList.add(p.copy());
        }

        configuration = new int[2][pentominoesList.size()];
    }

    /**
     * Used to initiate the parcels/pentominoes rotations.
     */
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
     * Sets the type of parcel that will be used to fill the container.
     * 
     * @param type - string that is "abc" or "pent"
     */
    private static void setTypeParcel(String type) {
        parcORpent = type;
    }

    /**
     * Method used to fill a container and return it filled.
     * 
     * @return - the filled container.
     */
    public Container fillContainer() {
        simulate();

        return bestContainer;
    }

    /**
     * The simulated annealing loop. It runs for a given time and sets the best
     * container to the best result it found.
     */
    public void simulate() {
        // variable initialization
        long startTime = System.currentTimeMillis();
        double temperature = INITIAL_TEMPERATURE;
        // initialize the sequence
        generateNeighbourhood();

        // constructing the container
        bestContainer = new Container();
        bestContainer = fill(configuration);

        // getting the unit (volume or value) to initialize bestUnit variable
        int bestUnit;
        if (valueOrVolume) // true = value, false = volume
            bestUnit = bestContainer.getValue();
        else
            bestUnit = - bestContainer.getGapAmount();

        // annealing loop
        while (System.currentTimeMillis() - startTime < timeToRun && bestContainer.getGapAmount() > 0) {

            // creating neighbourhood
            ArrayList<int[][]> neighbourhood = randomizeNeighbourhood(configuration);

            // filling the container with a random neighbour
            Container newContainer = fill(neighbourhood.get((int) (Math.random() * neighbourhood.size())));

            int unit; // same as bestUnit, but for the current container
            if (valueOrVolume) {
                unit = newContainer.getValue(); // value of the current container
            } else {
                unit = - newContainer.getGapAmount();
            }

            // System.out.println("unit = " + unit + " | value = " + newContainer.getValue()
            // + " | volume = " + newContainer.getParcelVolume());

            boolean better = false; // check if filling is better in new neighbour
            if (unit > bestUnit) {
                better = true;
            } else {
                temperature = slowDecreaseReduction(temperature); // GeometricReduction(temperature) &&
                // SlowDecreaseReduction(temperature)
                double delta = (bestUnit - unit) / bestUnit;
                double i = Math.random();

                if (i < Math.exp(-delta / temperature)) { // annealing formula
                    better = true; // the cooler it gets the smaller chance there is for this to be activated
                } else { // reheats the temperature
                    temperature = temperature / (1 - (alpha * temperature));
                }
            }

            if (better) {
                bestContainer = newContainer.copy();
                bestUnit = unit;
            }
        }

        // bestContainer.printContainer();
        System.out.println(bestUnit);
    }

    public double linearReduction(double temperature) {
        temperature = temperature - alpha;
        return temperature;
    }

    public double geometricReduction(double temperature) {
        temperature = temperature * alpha;
        return temperature;
    }

    public double slowDecreaseReduction(double temperature) {
        temperature = temperature / 1 + beta * temperature;
        return temperature;
    }

    /**
     * Generates a random sequence to fill the container. The sequence represents
     * the placing order of the parcels/pentominoes to be placed in the container.
     */
    private void generateNeighbourhood() {
        for (int j = 0; j < configuration[0].length; j++) {
            int columnIndex;
            boolean contains;
            do {
                contains = false;
                columnIndex = (int) (Math.random() * configuration[0].length); // TODO: could be changed to:
                // Math.random(sequence[0].length)
                for (int k = j - 1; k > 0; k--)
                    if (configuration[0][k] == columnIndex)
                        contains = true;
            } while (contains);
            configuration[0][j] = columnIndex;
        }

        // rotation vector definition
        if (parcORpent.equals("abc")) {
            for (int i = 0; i < configuration[0].length; i++) {
                configuration[1][i] = (int) (Math.random() * ROTATIONS_PARCELS); // tot_rot = 6
            }
        } else {
            for (int i = 0; i < configuration[0].length; i++) {
                configuration[1][i] = (int) (Math.random() * ROTATIONS_PENTOMINOES); // tot_rot = 24
            }
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

        // System.out.print("\nNEW CONTAINER! ");

        if (parcORpent.equals("abc")) {
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

                int number = 0;
                if (toPlace.getName().equals("A")) {
                    number = 1;
                } else if (toPlace.getName().equals("B")) {
                    number = 2;
                } else {
                    number = 3;
                }

                boolean stopPosition = true; // when the parcel is placed the for loops stop
                int[][][] contRep = cont.getRepresentation();
                for (int x = 0; x < contRep.length && stopPosition; x++) { // 33 length
                    for (int y = 0; y < contRep[0].length && stopPosition; y++) { // 5 height
                        for (int z = 0; z < contRep[0][0].length && stopPosition; z++) { // 8 width
                            if (cont.checkIfFits(representationParcel, x, y, z)) {
                                // System.out.print(toPlace.getName() + "\n");
                                cont.addParcel(representationParcel, number, x, y, z);
                                cont.increaseValue(toPlace.getValue());
                                stopPosition = false;
                            }
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < s[0].length; i++) {
                Pentominoes toPlace = pentominoesList.get(s[0][i]).copy(); // make copy because of rotation
                int[][][] repPentominoe;

                if (toPlace.getName().equals("L")) {
                    repPentominoe = pentominoeLRotations[s[1][s[0][i]]];
                } else if (toPlace.getName().equals("P")) {
                    repPentominoe = pentominoePRotations[s[1][s[0][i]]];
                } else {
                    repPentominoe = pentominoeTRotations[s[1][s[0][i]] % 2];
                }

                int number = 0;
                if (toPlace.getName().equals("L")) {
                    number = 1;
                } else if (toPlace.getName().equals("P")) {
                    number = 2;
                } else {
                    number = 3;
                }

                boolean stopPosition = true; // when the parcel is placed the for loops stop
                for (int x = 0; x < cont.getLength() && stopPosition; x++) {
                    for (int y = 0; y < cont.getWidth() && stopPosition; y++) {
                        for (int z = 0; z < cont.getHeight() && stopPosition; z++) {
                            if (cont.checkIfFits(repPentominoe, x, y, z)) {
                                // System.out.print(toPlace.getName() + " ");
                                cont.addParcel(repPentominoe, number, x, y, z);
                                cont.increaseValue(toPlace.getValue());
                                stopPosition = false;
                            }
                        }
                    }
                }
            }
        }

        return cont;
    }

    public static void main(String[] args) {
// simulation 1
/*
        alpha = 0.009;
        beta = 0.2;

        System.out.println("New experiment: " + beta + " " + alpha);

        for (int i = 0; i < 20; i++) {
            System.out.print((i + 1) + ") ");
            SimulatedAnnealing simann = new SimulatedAnnealing("pent");
            simann.simulate();
        }

        beta = 0.5;

        System.out.println("New experiment: " + beta + " " + alpha);

        for (int i = 0; i < 20; i++) {
            System.out.print((i + 1) + ") ");
            SimulatedAnnealing simann = new SimulatedAnnealing("pent");
            simann.simulate();
        }

        beta = 0.9;

        System.out.println("New experiment: " + beta + " " + alpha);

        for (int i = 0; i < 20; i++) {
            System.out.print((i + 1) + ") ");
            SimulatedAnnealing simann = new SimulatedAnnealing("pent");
            simann.simulate();
        }
*/
// simulation 2
        alpha = 0.005;
        beta = 0.2;

        System.out.println("New experiment: " + beta + " " + alpha);

        for (int i = 0; i < 20; i++) {
            System.out.print((i + 1) + ") ");
            SimulatedAnnealing simann = new SimulatedAnnealing("pent");
            simann.simulate();
        }

        beta = 0.5;

        System.out.println("New experiment: " + beta + " " + alpha);

        for (int i = 0; i < 20; i++) {
            System.out.print((i + 1) + ") ");
            SimulatedAnnealing simann = new SimulatedAnnealing("pent");
            simann.simulate();
        }

        beta = 0.9;

        System.out.println("New experiment: " + beta + " " + alpha);

        for (int i = 0; i < 20; i++) {
            System.out.print((i + 1) + ") ");
            SimulatedAnnealing simann = new SimulatedAnnealing("pent");
            simann.simulate();
        }

// simulation 3
        alpha = 0.002;
        beta = 0.2;

        System.out.println("New experiment: " + beta + " " + alpha);

        for (int i = 0; i < 20; i++) {
            System.out.print((i + 1) + ") ");
            SimulatedAnnealing simann = new SimulatedAnnealing("pent");
            simann.simulate();
        }

        beta = 0.5;

        System.out.println("New experiment: " + beta + " " + alpha);

        for (int i = 0; i < 20; i++) {
            System.out.print((i + 1) + ") ");
            SimulatedAnnealing simann = new SimulatedAnnealing("pent");
            simann.simulate();
        }

        beta = 0.9;

        System.out.println("New experiment: " + beta + " " + alpha);

        for (int i = 0; i < 20; i++) {
            System.out.print((i + 1) + ") ");
            SimulatedAnnealing simann = new SimulatedAnnealing("pent");
            simann.simulate();
        }
    }

}
