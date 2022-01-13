package GeneticA;

import java.util.*;

import Core.*;

/*Problem in the way I declared my container --SOLVED
*If cargo is filled, than there is a 1 at the position (when we will add UI it will be a color)
*Do I need to declare a Pentominoe class --DONE
*Need to Link it to a GA --WIP
*/

public class DepthSearch {
    public static Pentominoes PentP;
    public static Pentominoes PentT;
    public static Pentominoes PentL;
    public static int SkippedSpaces = 3;
    public static int BackTrackingAttempts = 0;
    public static int MaximumAttempts = 1000;
    public static boolean PlacedSuccess = true;
    public static int score = 0;
    public static int Bestscore = 0;
    public static Stack<int[][]> StackBarbuc = new Stack<int[][]>();
    static ArrayList<Pentominoes> AllPentominoes;
    public static Stack<int[][]> stackAuPoivre = new Stack<int[][]>();

    public static void main(String[] args) {
        InitialisePento();
        // debugging1(AllPentominoes);
        ArrayList<Integer> Arr = new ArrayList<Integer>();
        Arr.add(210);
        Arr.add(54);
        Arr.add(0);
        depthSearching(Arr);
    }

    // I will link this to a GA so that it knows which
    public static ArrayList<Pentominoes> InitialisePento() {
        AllPentominoes = new ArrayList<Pentominoes>();
        PentL = new Pentominoes("L", Rotations.getL(), 3, 0);
        PentP = new Pentominoes("P", Rotations.getP(), 4, 54); // second highest volume/value ratio
        PentT = new Pentominoes("T", Rotations.getT(), 5, 210); // highest volume/value ratio
        AllPentominoes.add(PentT);
        AllPentominoes.add(PentP);
        AllPentominoes.add(PentL);
        return AllPentominoes;
    }

    public static void debugging1(ArrayList<Pentominoes> a) {
        a = AllPentominoes;
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }
    }

    /*
     * Debugging changes :
     * -deleted the amount in Pentominoe Constructor
     * -
     */
    public static int[] depthSearching(ArrayList<Integer> b) {
        for (int z = 0; z < b.size(); z++) {
            Pentominoes temp = AllPentominoes.get(z);
            Integer a = b.get(z);
            temp.setAmount(a);
            if (a < 0) {
                int[] result = { -10000, 0 };
                return result;
            }
        }
        EmptyContainer();
        for (int k = 0; k < UserVariables.container.length; k++) {
            for (int l = 0; l < UserVariables.container[0].length; l++) {
                for (int m = 0; m < UserVariables.container[0][0].length; m++) {
                    int[] position = { k, l, m };
                    int i;
                    int j;
                    if (UserVariables.container[k][l][m] == 0) {
                        // System.out.println("CLEAN 1");
                        for (i = 0; i < AllPentominoes.size(); i++) {
                            if (UserVariables.container[k][l][m] == 1) {
                                break;
                            }
                            if (AllPentominoes.get(i).getAmount() <= 0) {
                                continue;
                            }
                            for (j = 0; j < AllPentominoes.get(i).getRotations().length; j++) {
                                // System.out.println("CLEAN 2");
                                if (UserVariables.container[k][l][m] == 1) {
                                    // System.out.println("CLEAN 3");
                                    break;
                                }
                                // System.out.println("CLEAN 4");
                                for (int n = 0; n < SkippedSpaces; n++) {
                                    // System.out.println("CLEAN 5");
                                    int sum = m + n;
                                    int[] positionSkip = { k, l, sum };
                                    int[] piecesAndRotations = { i, j };
                                    // System.out.println("CLEAN 6");
                                    if (addParcel(AllPentominoes.get(i).getRotations()[j], positionSkip,
                                            piecesAndRotations)) {
                                        int[][] a = { { k, l, sum }, { i, j } };
                                        stackAuPoivre.push(a);
                                        score += AllPentominoes.get(i).getValue();
                                        // System.out.println("SCORE :");
                                        // System.out.println("SCORE ICI : " +score);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    /*
                     * System.out.println("POSITION :");
                     * for(int o = 0 ; o<position.length ; o++){
                     * System.out.println(position[o]);
                     * }
                     * System.out.println("position 2 :");
                     * System.out.println("k : "+k+" l : "+l+" m : "+m);
                     */

                    int[] LastTrip = { UserVariables.container.length - 1, UserVariables.container[0].length - 1,
                            UserVariables.container[0][0].length - 1 };
                    if (Arrays.equals(position, LastTrip) && MaximumAttempts > BackTrackingAttempts) { // We only access
                                                                                                       // here when
                                                                                                       // there are no
                                                                                                       // positions left
                                                                                                       // in container
                        if (score > Bestscore) {
                            // System.out.println("Im in score");
                            // System.out.println(score);
                            Bestscore = score;
                            StackBarbuc = stackAuPoivre;
                        }
                        int[][] removeMove = stackAuPoivre.pop();
                        k = removeMove[0][0];
                        l = removeMove[0][1];
                        m = removeMove[0][2];
                        i = removeMove[1][0];
                        j = removeMove[1][1];
                        int[] positionA = { k, l, m };
                        BackTrackingAttempts++;
                        removePiece(AllPentominoes.get(i).getRotations()[j], positionA);
                        m++;
                        score -= AllPentominoes.get(i).getValue();
                        // System.out.println("REMOVING A PIECE");
                        AllPentominoes.get(i).setAmount(AllPentominoes.get(i).getAmount() + 1);

                    }
                    // System.out.println("HEERREEE");
                }
            }
        }
        EmptyContainer();
        // System.out.println(Bestscore);
        // System.out.println("FINAL SCORE " + Bestscore);
        // System.out.println("STATISTICS "+"\n T left :
        // "+AllPentominoes.get(0).getAmount()+"\n P left :
        // "+AllPentominoes.get(1).getAmount()+" \n L left :
        // "+AllPentominoes.get(2).getAmount());
        Iterator value = StackBarbuc.iterator();
        // System.out.println("Stacksize "+StackBarbuc.size());
        int NbofT = 0;
        int NbofP = 0;
        int NbofL = 0;
        int stempScore = 0;
        while (value.hasNext()) {
            int[][] popped = StackBarbuc.pop();
            if (popped[1][0] == 0) {
                stempScore += 5;
                NbofT++;
            } else if (popped[1][0] == 1) {
                stempScore += 4;
                NbofP++;
            } else if (popped[1][0] == 2) {
                stempScore += 3;
                NbofL++;
            }
            // System.out.println("Placed at ["+popped[0][0]+", "+popped[0][1]+",
            // "+popped[0][2]+"] with piece and rotation ["+popped[1][0]+" ,
            // "+popped[1][1]+" ]");
        }
        // System.out.println("Score StackScore : "+stempScore);
        // System.out.println(" Number of T "+NbofT+" \n Number of P "+NbofP+" \n Number
        // of L "+NbofL);
        int[] resultGA = { stempScore - (AllPentominoes.get(0).getAmount() * 5 + AllPentominoes.get(1).getAmount() * 4
                + AllPentominoes.get(2).getAmount() * 3), ((NbofT + NbofL + NbofP) * 5) };
        return resultGA;

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
    public static boolean addParcel(int[][][] parcel, int[] position, int[] PiecesAndRotations) {
        if (checkEmpty(parcel, position)) {
            for (int i = 0; i < parcel.length; i++) {
                for (int j = 0; j < parcel[i].length; j++) {
                    for (int k = 0; k < parcel[i][j].length; k++) {
                        if (parcel[i][j][k] == 1) {
                            UserVariables.container[position[0] + i][position[1] + j][position[2] + k] = 1;

                        }
                    }
                }
            }

            int PieceID = PiecesAndRotations[0];
            // int RotationID = PiecesAndRotations[1];
            AllPentominoes.get(PieceID).setAmount(AllPentominoes.get(PieceID).getAmount() - 1);
            // System.out.println("Parcels "+PiecesAndRotations[0]+"with rotations :
            // "+PiecesAndRotations[1]+" placed at "+ position[0]+ ", "+position[1]+",
            // "+position[2]);
            return true;
        }
        return false;
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

    public static void removePiece(int[][][] piece, int[] position) {
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length; j++) {
                for (int k = 0; k < piece[i][j].length; k++) {
                    if (piece[i][j][k] == 1) {
                        UserVariables.container[position[0] + i][position[1] + j][position[2] + k] = 0;

                    }
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
            return false;
        } else if (position[0] + parcel.length > UserVariables.container.length) {
            return false;
        } else if (position[1] + parcel[0].length > UserVariables.container[0].length) {
            return false;
        } else if (position[2] + parcel[0][0].length > UserVariables.container[0][0].length) {
            return false;
        } else {
            for (int i = 0; i < parcel.length; i++) {
                for (int j = 0; j < parcel[i].length; j++) {
                    for (int k = 0; k < parcel[i][j].length; k++) {
                        if (parcel[i][j][k] == 1) { // because pentominoes have holes, so no need to check for holes lol
                            if (UserVariables.container[position[0] + i][position[1] + j][position[2] + k] == 1) {
                                return false;
                            }

                        }
                    }
                }
            }
        }
        return true;
    }
}