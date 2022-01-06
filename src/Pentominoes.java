package Draft;

public class Pentominoes {

   private String name;
   private int[][][][] rotation;
// private int colors;
   private int value;
   private int placed;
   private int amount;
  
   Pentominoes(String n, int[][][][] r, /*int c*/ int v, int p, int a){
       this.name = n;
       this.rotation = r;
     //  this.colors = c;
       this.value = v;
       this.placed = p;
       this.amount = a;
     
   }


public String getName(){
   return this.name;
}

public int[][][][] getRotations(){
   return this.rotation;
}

/*public int getColor(){
   return this.colors;
}*/

public int getValue(){
   return this.value;
}

public int getPlaced(){
   return this.placed;
} 

public int getAmount(){
   return this.amount;
}


public String setName(String n){
   return this.name = n;
}

public int[][][][] setRotations(int[][][][] r){
   return this.rotation = r;
}

/*public int setColor(int c){
   return this.colors = c;
}*/

public int setValue(int v){
   return this.value = v;
}

public int setPlaced(int p){
   return this.placed = p;
}

public int setAmount(int a){
   return this.amount = a;
}
}
