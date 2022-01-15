package Core;

public class Parcels {

    private String name;
    private int rotations; // number of rotations possible per parcel
    private int value;
    private int amount;

    /**
     * 
     * @param n
     * @param r
     * @param v
     * @param p
     * @param a
     */
    public Parcels(String n, int r, int v, int a) {
        this.name = n;
        this.rotations = r;
        this.value = v;
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

    /**
     * @return
     */
    public int getValue() {
        return this.value;
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

    /**
     * @return
     */
    public int setValue(int v) {
        return this.value = v;
    }

    /**
     * @return
     */
    public int setAmount(int a) {
        return this.amount = a;
    }

    /**
     * @return - a copy of the current parcel
     */
    public Parcels copy() {
        return new Parcels(this.name, this.rotations, this.value, this.amount);
    }

}
