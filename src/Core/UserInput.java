package Core;

public class UserInput {

    String type;
    static int amount;
    float value;

    // UserInput("Pentominoe", 10, 4)

    public UserInput(String t, int a, float v) {
        amount = a;
        this.type = t;
        this.value = v;
    }

    public static int getAmount() {
        return amount;
    }

}
