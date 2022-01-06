package SearchAlg;
import java.io.*;
import java.util.*;
import Draft.UserInput;
import Draft.Pentominoes;
import Draft.Rotations;
import Draft.UserVariables;

public class depthSearch {
 /*Problem in the way I declared my container
 *If cargo is filled, than there is a 1 at the position (when we will ad UI it will be a color)
 *Do I need to declare a Pentominoe class 
 */
public static Pentominoes PentP;
public static Pentominoes PentT;
public static Pentominoes PentL;
public static int SkippedSpaces;
public static int BackTrackingAttempts;
public static boolean PlacedSuccess = true;
public static int score = 0;
public static int Bestscore = 0;
public static Stack<int[][]> StackBarbuc = new Stack<int[][]>();
static ArrayList <Pentominoes> AllPentominoes = new ArrayList<Pentominoes>();
public static Stack<int[][]> stackAuPoivre = new Stack<int[][]>(); //Oscar genius <3

public static ArrayList<Pentominoes> stupid(){
    /*When I instantiate the rotations, need to pass all the rotations for each Pento from the class Rotations*/
AllPentominoes.add(PentP);
AllPentominoes.add(PentT);
AllPentominoes.add(PentL);

return AllPentominoes;
}

public static void depthSearch(){
        EmptyContainer();
            for(int k = 0 ;k< UserVariables.container.length ; k++){
                for(int l = 0 ; l< UserVariables.container[0].length ; l++){
                    for(int m = 0 ;m< UserVariables.container[0][0].length ; m++){
                        int[] position = {k,l,m};
                        int i;
                        int j;
                        if(UserVariables.container[k][l][m]==0){
                            for(i = 0 ; i<AllPentominoes.size() ; i++){
                                if(UserVariables.container[k][l][m]==0){
                                    break;
                                }
                                for(j = 0 ; j<AllPentominoes.get(i).getRotations().length ; j++){
                                    if(UserVariables.container[k][l][m]==0){
                                        break;
                                }
                                    for(int n = 0 ; n<SkippedSpaces ; n++){
                                        int[] positionSkip = {k,l,m+n}; //choubidou genius <3
                                        int[]piecesAndRotations = {i,j};
                                            if(addParcel(AllPentominoes.get(i).getRotations()[j],positionSkip, piecesAndRotations)){
                                                int[][] a = {positionSkip,{i,j}};
                                                stackAuPoivre.push(a);
                                                score+=AllPentominoes.get(i).getValue();
                                                break;
                                }
                            }
                        }
                    }
                }
                int[] LastTrip = {UserVariables.container.length, UserVariables.container[0].length,UserVariables.container[0][0].length};
                if(position==LastTrip){
                    if(score>Bestscore){
                        Bestscore=score;
                        StackBarbuc=stackAuPoivre;
                    }
                    int[][] removeMove = stackAuPoivre.pop();
                    k = removeMove[0][0];
                    l = removeMove[0][1];
                    m = removeMove[0][2];
                    i = removeMove[1][0];
                    j = removeMove[1][1];
                    int[] positionA = {k,l,m};
                    removePiece(AllPentominoes.get(i).getRotations()[j], positionA);

                }
            }
        }
    }
    EmptyContainer();
    System.out.println(Bestscore);
    Iterator value = StackBarbuc.iterator();
    while(value.hasNext()){
        int[][] popped = StackBarbuc.pop();
        System.out.println("Placed at "+popped[0]+" with piece and rotation "+popped[1]);
    }
}



/**
     * 
     * @param parcel: piece to be added to the container, can be either parcel or pentominoe
     * @param position: position in the container where piece is added (stores the x,y,z coordinates)
     * @param PiecesAndRotations: store first the ID of the piece, then its rotation index
     * @param filling: coloring the container at the location where we add a piece
     */
    public static boolean addParcel(int[][][] parcel, int[]position, int[] PiecesAndRotations){
        if(checkEmpty(parcel, position)){
            for(int i = 0 ; i<parcel.length ; i++){
                for(int j = 0 ; j<parcel[i].length ; j++){
                    for(int k = 0 ; k<parcel[i][j].length ; k++){
                        if(parcel[i][j][k]== 1){ 
                        UserVariables.container[position[0]+i][position[1]+j][position[2]+k] = 1;
                        
                        }
                    }
                }
            }

        int PieceID = PiecesAndRotations[0];
        int RotationID = PiecesAndRotations[1];
        AllPentominoes.get(PieceID).setPlaced(AllPentominoes.get(PieceID).getPlaced()+1);
        System.out.println("Parcels  "+PiecesAndRotations[0]+"with rotations : "+PiecesAndRotations[1]+" placed at "+ position[0]+ ", "+position[1]+", "+position[2]);
        return true;
        }
        return false;
    }


    public static void EmptyContainer(){
        for(int x = 0 ; x<UserVariables.container.length ; x++){
            for(int y = 0 ; y<UserVariables.container[0].length ; y++){
                for(int z = 0 ; z<UserVariables.container[0][0].length ; z++){
                    UserVariables.container[x][y][z] = 0;
                }
            }
        }
    }
    


    public static void removePiece(int[][][] piece, int[] position){
        for(int i = 0 ; i<piece.length ; i++){
            for(int j = 0 ; j<piece[i].length ; j++){
                for(int k = 0 ; k<piece[i][j].length ; k++){
                    if(piece[i][j][k]== 1){ 
                    UserVariables.container[position[0]+i][position[1]+j][position[2]+k] = 0;  
                    }
                }
            }
        }
    }

    /**
     * 
     * @param parcel: piece to add to the container, parcel or pentominoe
     * @param position: location where to add piece in container
     * @return: return true if we can indeed put the piece in the intended location
     */
    public static boolean checkEmpty(int[][][]parcel, int[]position){
        if(position[0] < 0 || position[1] < 0 || position[2] < 0){
            return false;
        }else{             
             for(int i = 0 ; i<parcel.length; i++){
                 for(int j = 0 ; j<parcel[i].length; j++){
                     for(int k = 0 ; k<parcel[i][j].length; k++){
                         if(parcel[i][j][k]==1){
                             if(UserVariables.container[position[0]+i][position[1]+j][position[2]+k] == 1){
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