
import java.util.ArrayList;

/*

All the stuff we can implement : 
-Hill Climbing algorithm 
-Greedy Algorithm : to obtain the lowest bond 
-Simulated Annealing : to get out of the local minima of the hill climbing algorithm 
-Genetic Algorithm
-Algorithm X ? Lol, ambitious heheheh
*/

public class GreedyAlg {

    public static Parcels parcelA;
    public static Parcels parcelB;
    public static Parcels parcelC;
    static int[][][][] parcelARotations;
    static int[][][][] parcelBRotations;
    static int[][][][] parcelCRotations;
    static ArrayList<Parcels> AllParcels;

    public static void GreedyAlgorithmRun() {

        AllParcels = new ArrayList<>();

        // think about the input more
        // parcelA = new Parcels(n, r, c, v, p, a);
        // parcelB = new Parcels(n, r, c, v, p, a);
        // parcelC = new Parcels(n, r, c, v, p, a);
        // AllParcels.add(parcelA);
        // AllParcels.add(parcelB);
        // AllParcels.add(parcelC);

        parcelARotations = new int[][][][] {

        };

        parcelBRotations = new int[][][][] {

        };

        parcelCRotations = new int[][][][] {

        };

    }

    public static void FillContainer(){
        //need to check every parcel type, every rotation of the parcel, and x,y,z axis of container 

        for(int i = 0 ; i< AllParcels.size() ; i++){
            for(int j = 0 ; j<AllParcels.get(i).getRotations(); j++){
                for(int k = 0 ; UserVariables.container.length ; k++){
                    for(int l = 0 ; UserVariables.container[0].length ; l++){
                        for(int m = 0 ; UserVariables.container[0][0].length ; m++){
                           //check if the number of parcel places is not bigger than number of parcel){
                                else{
                                    int[] position = {k,l,m};
                                    int[] PieceAndRotations = {i,j};
                                    addParcel(); //see parameters of this function
                                }
                            }

                        }
                    }
                }
            }
        }

    public static int RatioValues() {

    }

    // public static emptyContainer(){

    // }

    public static boolean checkEmpty(int[][][] parcel, int[] position) {

    }

    public static void addParcel(int[][][] parcel, int[] position, int[] PiecesAndRotations) {
        if (checkEmpty(parcel, position)) {
            for (int i = 0; solid.length; i++) {
                // for(int j = 0 ; )
            }

        }

    }

    public static void Informations() {

    }

}