package SimAnn;

import java.util.ArrayList;

import UI.UserVariables;

public class SimAneal {
    /*
    private static ArrayList<Parcels> parcelsList;
    private int[][] sequence;
    private Container bestContainer;

    static int[][][][] parcelARotations;
    static int[][][][] parcelBRotations;
    static int[][][][] parcelCRotations;

    private static double temperature; // current temperature.
    private static double lastTemp; // last temperature.
    private static String reductionType; // there are three type reduction temperature.

    private static double beta = 1; // it can be changed to set the amount of decrease temperature.
    private static double alpha = 1; // it can be changed to set the amount of decrease temperature as well.
    // it is necessary random solutions are obtained from another local search
    // algorithm.

    /**
     * 
     *
    public SimAneal() {
        // change the formating of the rotations
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

        ParcelList pList = new ParcelList();

        pList.addWithAmount(new Parcels("A", parcelARotations.length, 3, 0, 30), 30);
        pList.addWithAmount(new Parcels("B", parcelBRotations.length, 4, 0, 10), 10);
        pList.addWithAmount(new Parcels("C", parcelCRotations.length, 5, 0, 22), 22);

        parcelsList = pList.getFullArray();

        sequence = new int[4][pList.getTotalSize()];
        // row 0 for dimension x
        // row 1 for dimension y
        // row 2 for dimension z
        // row 3 for rotations
        // colums for the parcels
    }

    /**
     * 
     * @param t - temperature
     * @param l -
     * @param r -
     *
    public SimulatedAnnealing(double t, double l, String r) {
        temperature = t;
        lastTemp = l;
        reductionType = r;
    }

    /**
     * 
     *
    public static void CoolingProcess() {
        String reductionTypeLast = reductionType.toLowerCase();

        switch (reductionType) {
            case "linear reduction":
                LinearReduction();
            case "geometric reduction":
                GeometricReduction();
            case "slow decrease reduction":
                SlowDecreaseReduction();
            default:
                System.out.println("Please enter the type of cooling process.");
        }
    }

    public static double LinearReduction() {
        temperature = temperature - alpha;
        return temperature;
    }

    public static double GeometricReduction() {
        temperature = temperature * alpha;
        return temperature;
    }

    public static double SlowDecreaseReduction() {
        temperature = temperature / 1 + beta * temperature;
        return temperature;
    }

    /**
     * Method the generate the neighbourhood of a given sequence.
     * 
     * @param s The given sequence triple to generate the neighbourhood from
     * @return An ArrayList of sequence triples defining the created neighbourhood.
     *
    private ArrayList<int[][]> createNeighbourhood(int[][] s) {
        ArrayList<int[][]> sequenceList = new ArrayList<>();

        // one change
        sequenceList.add(switchItems(s, (int) (Math.random() * s.length)));

        // 2 changes
        sequenceList.add(switchItems(switchItems(s, 1), 0));
        sequenceList.add(switchItems(switchItems(s, 2), 1));
        sequenceList.add(switchItems(switchItems(s, 2), 0));
        sequenceList.add(switchItems(switchItems(s, 3), 0));
        sequenceList.add(switchItems(switchItems(s, 3), 1));
        sequenceList.add(switchItems(switchItems(s, 3), 2));

        // 3 changes
        sequenceList.add(switchItems(switchItems(switchItems(s, 2), 1), 0));
        sequenceList.add(switchItems(switchItems(switchItems(s, 3), 2), 1));
        sequenceList.add(switchItems(switchItems(switchItems(s, 3), 1), 0));
        sequenceList.add(switchItems(switchItems(switchItems(s, 3), 2), 0));

        // 4changes
        sequenceList.add(switchItems(switchItems(switchItems(switchItems(s, 3), 2), 1), 0));
        return sequenceList;
    }

    /**
     * Method used to switch two items inside a sequence.
     * 
     * @param s     The given sequence triple.
     * @param index The index of the sequence inside which two box are going to be
     *              permuted.
     *              (0 = sequence A; 1 = sequence B; 2 = sequence C; 3 = rotation
     *              vector)
     * @return The sequence triple with the permuted items.
     *
    private int[][] switchItems(int[][] s, int index) {
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
     * The simulated annealing loop. It runs for a given time and sets the best
     * truck to the best result it found.
     *
    private void simulate() {
        // long startTime = System.currentTimeMillis();

        createNeighbourhood();

        // construct the truck
        bestContainer = new Container(new int[33][5][8]);
        fillContainer(sequence);
        bestContainer.setRepresentation(UserVariables.container);

        // gets the unit to initialize bestUnit variable. Unit: volume or value
        int bestUnit;
        bestUnit = bestContainer.getValue();

        // if(value) bestUnit = bestContainer.getValue();
        // else bestUnit = bestContainer.getParcelVolume();

        // annealing loop
        while (bestContainer.getGapAmount() > 0) { // System.currentTimeMillis()- startTime < timeToRun &&
            ArrayList<int[][]> neighbourhood = createNeighbourhood(sequence); // place list here

            // tries to fill truck with random neighbour
            fillContainer(neighbourhood.get((int) (Math.random() * neighbourhood.size())));
            Container newC = new Container(UserVariables.container);

            int unit;
            unit = newC.getValue();

            // if(value) unit = newT.getValue();
            // else unit = newT.getParcelVolume();

            boolean better = false;

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

            // saves configuration
            if (better) {
                bestContainer = newC;
                bestUnit = unit;
            }
        }
        System.out.println(bestUnit);
    }

    private void createNeighbourhood() {
    }

    /**
     * Evaluates the total value of the truck using the values of A,B and C parcels,
     * respectively.
     * 
     * @return the total value.
     *
    public static int EvaluateTotalValue() {
        return 0;

    }

    /**
     * Fills the truck with the best configuration.
     * 
     * @return the current truck.
     *
    public void fillContainer(int[][] s) {
        // need to check every parcel type, every rotation of the parcel, and x,y,z axis
        // of container
        for (int i = 0; i < parcelsList.size(); i++) {
            for (int j = 0; j < parcelsList.get(i).getRotations(); j++) {
                int[][][] piece = getRotations(i, j); // i - piece, j - rotation
                for (int k = 0; k < UserVariables.container.length; k++) {
                    for (int l = 0; l < UserVariables.container[0].length; l++) {
                        for (int m = 0; m < UserVariables.container[0][0].length; m++) {
                            if (parcelsList.get(i).getPlaced() >= parcelsList.get(i).getAmount()) {
                                if (i < parcelsList.size()) {
                                    if (i < 2) {
                                        i++;
                                    }
                                }
                            } else {
                                int[] position = { k, l, m };
                                int[] PieceAndRotations = { i, j };
                                addParcel(piece, position, PieceAndRotations, parcelsList.get(i).getValue()); // see parameters of this function
                            }
                        }
                    }
                }
            }
        }
    }

    public static int[][][] getRotations(int p, int r) {
        int[][][] parcel = new int[0][0][0];
        String type = parcelsList.get(p).getName();

        if (type.equals("A")) {
            parcel = parcelARotations[r];
        } else if (type.equals("B")) {
            parcel = parcelBRotations[r];
        } else if (type.equals("C")) {
            parcel = parcelCRotations[r];
        }
        return parcel;
    }

    /**
     * 
     * @param parcel:             piece to be added to the container, can be either
     *                            parcel or pentominoe
     * @param position:           position in the container where piece is added
     *                            (stores the x,y,z coordinates)
     * @param PiecesAndRotations: store first the ID of the piece, then its rotation
     *                            index
     *
    public static void addParcel(int[][][] parcel, int[] position, int[] PiecesAndRotations, int value) {
        if (!checkEmpty(parcel, position)) {
            for (int i = 0; i < parcel.length; i++) {
                for (int j = 0; j < parcel[i].length; j++) {
                    for (int k = 0; k < parcel[i][j].length; k++) {
                        if (parcel[i][j][k] == 1) {
                            UserVariables.container[position[0] + i][position[1] + j][position[2] + k] = 1; //
                        }
                    }
                }
            }
        }
        int PieceID = PiecesAndRotations[0];
        // int RotationID = PiecesAndRotations[1];
        parcelsList.get(PieceID).setPlaced(parcelsList.get(PieceID).getPlaced() + 1);
        System.out.println("Parcels: " + PiecesAndRotations[0] + " with rotations: " + PiecesAndRotations[1]
                + " placed at: " + position[0] + ", " + position[1] + ", " + position[2]);
    }

    /**
     * 
     * @param parcel:   piece to add to the container, parcel or pentominoe
     * @param position: location where to add piece in container
     * @return: return true if we can indeed put the piece in the intended location
     *
    public static boolean checkEmpty(int[][][] parcel, int[] position) {
        if (position[0] < 0 || position[1] < 0 || position[2] < 0) {
            return true;
        } else {
            for (int i = 0; i < parcel.length; i++) {
                for (int j = 0; j < parcel[i].length; j++) {
                    for (int k = 0; k < parcel[i][j].length; k++) {
                        if (parcel[i][j][k] == 1) {
                            if (UserVariables.container[position[0] + i][position[1] + j][position[2] + k] != 1) {
                                return true;
                            }

                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks whether there is enough space left to put a parcel.
     * 
     * @return false if it is possible to put at least one parcel in it; true if the
     *         truck is completely full.
     *
    public static boolean checkTruckFilled() {
        return true;
    }

    /**
     * Simulated Annealing Algorithm.
     * 
     * @param "difference" indicates the difference of the parcel number between the
     *                     configurations.
     * @param "betterConf" it is true if the configuration from neighborhood is
     *                     better; it is false the first configuration is better.
     */
    // public static void SAalgorithm() {
    // int difference = CreateConfiguration() - CreateNeighborhoodConf();
    // boolean betterConf;
    // while (temperature == lastTemp || checkTruckFilled() == true) { // the
    // terminated restriction can be changed.
    // if (difference > 0) {
    // CreateNeighborhoodConf();
    // betterConf = true;
    // } else if (Math.exp(difference / temperature) > Math.random()) {
    // CreateNeighborhoodConf();
    // betterConf = true;
    // } else {
    // CreateConfiguration();
    // betterConf = false;
    // }
    // CoolingProcess();
    // fillTruck();

    // }
    // }
}
