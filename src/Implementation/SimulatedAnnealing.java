package Implementation;

import java.util.ArrayList;

public class SimulatedAnnealing {
// variables
    // private static Container container;
    private static int[][][] cont;
    private static int[][][] bestContainer;

    ParcelList parcelList;
    private int[][][][] parcelARotations;
    private int[][][][] parcelBRotations;
    private int[][][][] parcelCRotations;

// Constructors:
    public SimulatedAnnealing() {
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

// For the alg:
    public int[][][] bestContainer() {
        return bestContainer;
    }

    public void createNeighbourhood() {
        
    }

// for containers:
    public void fillCont() {
        while (parcelList.getTail() != null) {
            int value = parcelList.getvalueHead();
            int[][][] parcel = parcelList.removeFirstParcel();

            for (int x = 0; x < cont.length; x++) {
                for (int y = 0; y < cont[0].length; y++) {
                    for (int z = 0; z < cont[0][0].length; z++) {
                        if (checkIfFits(cont, parcel, x, y, z)) {
                            addParcel(parcel, value, x, y, z);
                        }
                    }
                }
            }
        }

        // return cont;
    }

    /**
     * 
     * @param cont2
     * @param parcel
     * @param x
     * @param y
     * @param z
     * @return - true if it can be placed and false if it can't
     */
    private boolean checkIfFits(int[][][] cont2, int[][][] parcel, int x, int y, int z) {
        for (int i = 0; i < parcel.length; i++) {
            for (int j = 0; j < parcel[0].length; j++) {
                for (int k = 0; k < parcel[0][0].length; k++) {
                    if (x + i > cont2.length || y + j > cont2[0].length || z + k > cont2[0][0].length
                            || cont2[x + i][y + j][z + k] != 0) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Adds a parcel in the container at the specified coordinates.
     * @param parcel - 2d array representing the parcel
     * @param value - the value of the parcel
     * @param x - coordinate x where the parcel is going to be placed
     * @param y - coordinate y where the parcel is going to be placed
     * @param z - coordinate z where the parcel is going to be placed
     */
    private void addParcel(int[][][] parcel, int value, int x, int y, int z) {
        for (int i = 0; i < parcel.length; i++) {
            for (int j = 0; j < parcel[0].length; j++) {
                for (int k = 0; k < parcel[0][0].length; k++) {
                    cont[x + i][y + j][z + k] = value;
                }
            }
        }
    }
}
