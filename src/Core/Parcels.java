package Core;

public class Parcels {

    private String name;
    private int rotations; // number of rotations possible per parcel
    // private int colors;
    private int value;
    private int placed;
    private int amount;

    /**
     * 
     * @param n
     * @param r
     * @param v
     * @param p
     * @param a
     */
    public Parcels(String n, int r, /* int c */ int v, int p, int a) {
        this.name = n;
        this.rotations = r;
        // this.colors = c;
        this.value = v;
        this.placed = p;
        this.amount = a;

    }

    /**
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return
     */
    public int getRotations() {
        return this.rotations;
    }

    /*
     * public int getColor(){
     * return this.colors;
     * }
     */

    /**
     * @return
     */
    public int getValue() {
        return this.value;
    }

    /**
     * @return
     */
    public int getPlaced() {
        return this.placed;
    }

    /**
     * @return
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * @return
     */
    public String setName(String n) {
        return this.name = n;
    }

    /**
     * @return
     */
    public int setRotations(int r) {
        return this.rotations = r;
    }

    /*
     * public int setColor(int c){
     * return this.colors = c;
     * }
     */

    /**
     * @return
     */
    public int setValue(int v) {
        return this.value = v;
    }

    /**
     * @return
     */
    public int setPlaced(int p) {
        return this.placed = p;
    }

    /**
     * @return
     */
    public int setAmount(int a) {
        return this.amount = a;
    }

    public Parcels copy() {
        // TODO:
        return null;
    }

}
