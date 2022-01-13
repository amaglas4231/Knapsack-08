package Core;

public class Pentominoes {
    private String name;
    private int[][][][] rotation;
    private int rotations; // number of rotations
    private int value;
    private int amount;

    public Pentominoes(String n, int[][][][] r, int v, int a) {
        this.name = n;
        this.rotation = r;
        this.value = v;
        this.amount = a;

    }

    public Pentominoes(String n, int r, int v, int a) {
        this.name = n;
        this.rotations = r;
        this.value = v;
        this.amount = a;
    }

    public String getName() {
        return this.name;
    }

    public int[][][][] getRotation() {
        return this.rotation;
    }

    public int getRotations() {
        return this.rotations;
    }

    public int getValue() {
        return this.value;
    }

    public int getAmount() {
        return this.amount;
    }

    public String setName(String n) {
        return this.name = n;
    }

    public int[][][][] setRotation(int[][][][] r) {
        return this.rotation = r;
    }

    public int setRotations(int r) {
        return this.rotations = r;
    }

    public int setValue(int v) {
        return this.value = v;
    }

    public void setAmount(int a) {
        this.amount = a;
    }
}
