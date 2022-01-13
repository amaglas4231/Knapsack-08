package SimAnn;

import java.util.ArrayList;

import Core.Container;
import Core.ParcelList;
import Core.Parcels;

public class SimulatedAnnealing {
    private double beta = 0.2; // cooling parameter
    private double alpha = 0.002; // heating parameter

    private Container bestContainer;
    private ArrayList<Parcels> parcelsList;
    private int[][] sequence;

    static int[][][][] parcelARotations;
    static int[][][][] parcelBRotations;
    static int[][][][] parcelCRotations;

    private static final double INITIAL_TEMPERATURE = 0.2;
    private static final int TOTAL_ROTATIONS = 6; // to be adapted if pentominoes

    private static long timeToRun = 6000; // 1000 = 1sec

    private static boolean value = true; // true = searches for the best value; false = searches for the best volume

    /**
     * Default constructor of the class. The list is set to the maximum amount of
     * each parcels to be used.
     */
    public SimulatedAnnealing() {
        initiateParcelsRotations();

        ParcelList pList = new ParcelList();

        pList.addWithAmount(new Parcels("A", parcelARotations.length, 3, 0, 30));
        pList.addWithAmount(new Parcels("B", parcelBRotations.length, 4, 0, 10));
        pList.addWithAmount(new Parcels("C", parcelCRotations.length, 5, 0, 22));

        // pList.add(new Parcels("A"), 82);
        // pList.add(new Parcels("B"), 55);
        // pList.add(new Parcels("C"), 48);

        // pList.add(new PentominoParcel("L"),3);
        // pList.add(new PentominoParcel("P"),3);
        // pList.add(new PentominoParcel("T"),3);

        parcelsList = pList.getFullArray();

        sequence = new int[4][pList.getTotalSize()];
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

        sequence = new int[4][parcelsList.size()];
    }

    void initiateParcelsRotations() {
        // TODO: replace 1s with the value of each parcel
        parcelARotations = new int[][][][] {
                { { { 1, 1, 1, 1 }, { 1, 1, 1, 1 } }, { { 1, 1, 1, 1 }, { 1, 1, 1, 1 } } },
                { { { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 } }, { { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 } } },
                { { { 1, 1 }, { 1, 1 } }, { { 1, 1 }, { 1, 1 } }, { { 1, 1 }, { 1, 1 } }, { { 1, 1 }, { 1, 1 } } }
        };

        parcelBRotations = new int[][][][] {
                { { { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 } },
                        { { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 } } },
                { { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } },
                        { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } } },
                { { { 1, 1 }, { 1, 1 }, { 1, 1 } }, { { 1, 1 }, { 1, 1 }, { 1, 1 } }, { { 1, 1 }, { 1, 1 }, { 1, 1 } },
                        { { 1, 1 }, { 1, 1 }, { 1, 1 } } },
                { { { 1, 1, 1, 1 }, { 1, 1, 1, 1 } }, { { 1, 1, 1, 1 }, { 1, 1, 1, 1 } },
                        { { 1, 1, 1, 1 }, { 1, 1, 1, 1 } } },
                { { { 1, 1, 1 }, { 1, 1, 1 } }, { { 1, 1, 1 }, { 1, 1, 1 } }, { { 1, 1, 1 }, { 1, 1, 1 } },
                        { { 1, 1, 1 }, { 1, 1, 1 } } },
                { { { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 } }, { { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 } },
                        { { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 } } }
        };

        parcelCRotations = new int[][][][] {
                { { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } }, { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } },
                        { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } } }
        };
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
        generate();

        // construct the Container
        bestContainer = new Container();
        bestContainer = fill(sequence);

        // gets the unit to initialize bestUnit variable. Unit: volume or value
        int bestUnit;
        if (value)
            bestUnit = bestContainer.getValue();
        else
            bestUnit = bestContainer.getParcelVolume();

        // annealing loop
        while (System.currentTimeMillis() - startTime < timeToRun && bestContainer.getGapAmount() > 0) {

            // creates neighbourhood
            ArrayList<int[][]> neighbourhood = randomize(sequence); // place list here

            // tries to fill Container with random neighbour
            Container newT = fill(neighbourhood.get((int) (Math.random() * neighbourhood.size())));
            int unit;

            if (value) {
                unit = newT.getValue();
            } else {
                unit = newT.getParcelVolume();
            }

            boolean better = false;

            // check if filling is better in new neighbour
            if (unit > bestUnit)
                better = true;
            else {
                temperature = temperature / (1 + (beta * temperature));
                double delta = (bestUnit - unit) / bestUnit;
                double i = Math.random();

                if (i < Math.exp(-delta / temperature))
                    better = true; // the cooler it gets the smaller chance there is for this to be activated
                else { // reheats the temperature
                    temperature = temperature / (1 - (alpha * temperature));
                }
            }

            // saves configuration
            if (better) {
                bestContainer = newT.copy();
                bestUnit = unit;
            }
        }
        System.out.println(bestUnit);
    }

    /**
     * Generates a random sequence triple to fill the Container. The sequence is
     * used to
     * define the placing order of the items to be placed in the Container.
     */
    private void generate() {
        // A, B & C sequence definition
        for (int i = 0; i < sequence.length - 1; i++) {
            for (int j = 0; j < sequence[0].length; j++) {
                int columnIndex;
                boolean contains;
                do {
                    contains = false;
                    columnIndex = (int) (Math.random() * sequence[0].length); // TODO: could be changed
                    for (int k = j - 1; k > 0; k--)
                        if (sequence[i][k] == columnIndex)
                            contains = true;
                } while (contains);
                sequence[i][j] = columnIndex;
            }
        }

        // rotation vector definition
        for (int i = 0; i < sequence[0].length; i++) {
            sequence[3][i] = (int) (Math.random() * TOTAL_ROTATIONS); // tot_rot = 6
        }
    }

    /**
     * Method the generate the sequence triple neighbourhood of a given sequence.
     * 
     * @param s The given sequence triple to generate the neighbourhood from
     * @return An ArrayList of sequence triples defining the created neighbourhood.
     */
    private ArrayList<int[][]> randomize(int[][] s) {
        ArrayList<int[][]> sequenceList = new ArrayList<>();

        // one change
        sequenceList.add(switchBox(s, (int) (Math.random() * s.length)));

        // 2 changes
        sequenceList.add(switchBox(switchBox(s, 1), 0));
        sequenceList.add(switchBox(switchBox(s, 2), 1));
        sequenceList.add(switchBox(switchBox(s, 2), 0));
        sequenceList.add(switchBox(switchBox(s, 3), 0));
        sequenceList.add(switchBox(switchBox(s, 3), 1));
        sequenceList.add(switchBox(switchBox(s, 3), 2));

        // 3 changes
        sequenceList.add(switchBox(switchBox(switchBox(s, 2), 1), 0));
        sequenceList.add(switchBox(switchBox(switchBox(s, 3), 2), 1));
        sequenceList.add(switchBox(switchBox(switchBox(s, 3), 1), 0));
        sequenceList.add(switchBox(switchBox(switchBox(s, 3), 2), 0));

        // 4changes
        sequenceList.add(switchBox(switchBox(switchBox(switchBox(s, 3), 2), 1), 0));
        return sequenceList;
    }

    /**
     * Method used to switch two items inside a sequence given sequence.
     * 
     * @param s     The given sequence triple.
     * @param index The index of the sequence inside which two box are going to be
     *              permuted.
     *              (0 = sequence A; 1 = sequence B; 2 = sequence C; 3 = rotation
     *              vector)
     * @return The sequence triple with the permuted items.
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
     * Method used to fill a Container with a sequence of parcels. Follows the
     * sequence
     * B.
     * 
     * @param s The sequence with which the Container has to be filled.
     * @return A filled Container.
     */
    private Container fill(int[][] s) {
        Container cont = new Container();

        for (int i = 0; i < s[0].length; i++) {
            Parcels toPlace = parcelsList.get(s[1][i]).copy(); // make copy because of rotation
            int[][][] representationParcel;

            if (toPlace.getName().equals("A")) {
                representationParcel = parcelARotations[s[3][s[1][i]] % 3];
                // TODO: try to change the sequence to only have 2 rows (sequence + rotation) or
                // 2 seperate arrays
            } else if (toPlace.getName().equals("B")) {
                representationParcel = parcelBRotations[s[3][s[1][i]]];
            } else {
                representationParcel = parcelCRotations[0];
            }

            cont.addParcel(representationParcel);
            // TODO: increase value of container
        }
        return cont;
    }

}
