package Implementation;

public class Parcels {

    private String name;
    private int rotation;
    private int colors;
    private double value;
    private int placed;
    private int amount;

    /**
     * Constructor for a Parcel
     * @param n     - which parcel this is ('A', 'B', 'C')
     * @param r     - rotation of the parcel
     * @param c     - color
     * @param v     - value
     * @param p     - where is it placed // ???
     * @param a     - how many of these parcels do we have
     */
    Parcels(String n, int r, int c, double v, int p, int a) {
        this.name = n;
        this.rotation = r;
        this.colors = c;
        this.value = v;
        this.placed = p;
        this.amount = a;
    }

// GETTERS
    public String getName() {
        return this.name;
    }

    public int getRotations() {
        return this.rotation;
    }

    public int getColor() {
        return this.colors;
    }

    public double getValue() {
        return this.value;
    }

    public int getPlaced() {
        return this.placed;
    }

    public int getAmount() {
        return this.amount;
    }

// SETTERS
    public String setName(String n) {
        return this.name = n;
    }

    public int setRotations(int r) {
        return this.rotation = r;
    }

    public int setColor(int c) {
        return this.colors = c;
    }

    public double setValue(double v) {
        return this.value = v;
    }

    public int setPlaced(int p) {
        return this.placed = p;
    }

    public int setAmount(int a) {
        return this.amount = a;
    }

// OTHER METHODS

}
