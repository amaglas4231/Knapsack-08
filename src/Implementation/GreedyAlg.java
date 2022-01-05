package Implementation;

import java.util.ArrayList;

import UI.UserVariables;

public class GreedyAlg {
    public static Parcels parcelA;
    public static Parcels parcelB;
    public static Parcels parcelC;
    static int[][][][] parcelARotations;
    static int[][][][] parcelBRotations;
    static int[][][][] parcelCRotations;
    static ArrayList<Parcels> AllParcels;

    public static void RunAlg() {
        AllParcels = new ArrayList<>();
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

        parcelA = new Parcels("A", parcelARotations.length, 3, 0, 30); // PROBLEME
        parcelB = new Parcels("B", parcelBRotations.length, 4, 0, 10);
        parcelC = new Parcels("C", parcelCRotations.length, 5, 0, 22);
        EmptyContainer();
        AllParcels.add(parcelA);
        AllParcels.add(parcelC); // added in this order because we know that the order with the higest
                                 // value/volume ratio
        AllParcels.add(parcelB);

        FillContainer();
        Informations();
    }

    public static void main(String[] args) {
        RunAlg();
    }

    public static void FillContainer() {
        // need to check every parcel type, every rotation of the parcel, and x,y,z axis
        // of container
        for (int i = 0; i < AllParcels.size(); i++) {
            for (int j = 0; j < AllParcels.get(i).getRotations(); j++) {
                int[][][] piece = getRotations(i, j);
                for (int k = 0; k < UserVariables.container.length; k++) {
                    for (int l = 0; l < UserVariables.container[0].length; l++) {
                        for (int m = 0; m < UserVariables.container[0][0].length; m++) {
                            if (AllParcels.get(i).getPlaced() >= AllParcels.get(i).getAmount()) {
                                if (i < AllParcels.size()) {
                                    if (i < 2) {
                                        i++;
                                    }
                                }
                            } else {
                                int[] position = { k, l, m };
                                int[] PieceAndRotations = { i, j };
                                addParcel(piece, position, PieceAndRotations); // see parameters of this function
                            }
                        }
                    }
                }
            }
        }
    }

    public static int[][][] getRotations(int p, int r) {
        int[][][] parcel = new int[0][0][0];
        String type = AllParcels.get(p).getName();

        if (type.equals("A")) {
            parcel = parcelARotations[r];
        } else if (type.equals("B")) {
            parcel = parcelBRotations[r];
        } else if (type.equals("C")) {
            parcel = parcelCRotations[r];
        }
        return parcel;
    }

    public static void EmptyContainer() {
        for (int x = 0; x < UserVariables.container.length; x++) {
            for (int y = 0; y < UserVariables.container[0].length; y++) {
                for (int z = 0; z < UserVariables.container[0][0].length; z++) {
                    UserVariables.container[x][y][z] = 0;
                }
            }
        }
    }

    /**
     * 
     * @param parcel:   piece to add to the container, parcel or pentominoe
     * @param position: location where to add piece in container
     * @return: return true if we can indeed put the piece in the intended location
     */
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
     * 
     * @param parcel:             piece to be added to the container, can be either
     *                            parcel or pentominoe
     * @param position:           position in the container where piece is added
     *                            (stores the x,y,z coordinates)
     * @param PiecesAndRotations: store first the ID of the piece, then its rotation
     *                            index
     * @param filling:            coloring the container at the location where we
     *                            add a piece
     */
    public static void addParcel(int[][][] parcel, int[] position, int[] PiecesAndRotations) {
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
        AllParcels.get(PieceID).setPlaced(AllParcels.get(PieceID).getPlaced() + 1);
        System.out.println("Parcels  " + PiecesAndRotations[0] + "with rotations : " + PiecesAndRotations[1]
                + " placed at " + position[0] + ", " + position[1] + ", " + position[2]);
    }

    /**
     * Method printing the number of parcels/pentominoes we used
     * Printing the score, so nb of parcel used * value of parcel
     */
    public static void Informations() {
        System.out.println("INFORMATION");
        System.out.println("Number of parcel A used :" + parcelA.getPlaced());
        System.out.println("Number of parcel B used :" + parcelB.getPlaced());
        System.out.println("Number of parcel C used :" + parcelC.getPlaced());
        // float score =
        // ((parcelA.getPlaced()*UserVariables.input[0].value)+(parcelB.getPlaced()*UserVariables.input[0].value)+(parcelC.getPlaced()*UserVariables.input[0].value));

        float score = ((parcelA.getPlaced() * 3) + (parcelB.getPlaced() * 4) + (parcelC.getPlaced() * 5));

        System.out.println("Value of parcels " + score);
    }

}