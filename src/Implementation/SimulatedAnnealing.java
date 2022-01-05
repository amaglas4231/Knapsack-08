package Implementation;

public class SimulatedAnnealing implements LocalSearchAlgorithm {
    private static double temperature; // current temperature.
    private static double lastTemp; // last temperature.
    private static String reductionType; // there are three type reduction temperature.
    private static double beta = 1; // it can be changed to set the amount of decrease temperature.
    private static double alpha = 1; // it can be changed to set the amount of decrease temperature as well.
    // it is necessary random solutions are obtained from another local search
    // algorithm.

    public SimulatedAnnealing(double temperature, double lastTemp, String reductionType) {
        this.temperature = temperature;
        this.lastTemp = lastTemp;
        this.reductionType = reductionType;
    }

    public static void CoolingProcess() {

        String reductionTypeLast = reductionType.toLowerCase();

        switch (reductionType) {
            case "linear reduction":
                LinearReduction();
            case "geometric reduction":
                GeometricReduction();
            case "slow decrease reduction":
                SlowDecreaseReduction();
            default:
                System.out.println("Please enter the type of cooling process.");
        }
    }

    public static double LinearReduction() {
        temperature = temperature - alpha;
        return temperature;
    }

    public static double GeometricReduction() {
        temperature = temperature * alpha;
        return temperature;
    }

    public static double SlowDecreaseReduction() {
        temperature = temperature / 1 + beta * temperature;
        return temperature;
    }

    /**
     * Creates a random configuration to fill the truck.
     * 
     * @param "conf" the configuration is accepted the best one.
     * @return the number of parcels in the truck.
     */
    public static int CreateConfiguration() {
        return 0;

    }

    /**
     * Creates another configuration which is from neighborhood to fill the truck.
     * 
     * @param "confFromNH" the other configuration from neighborhood.
     * @return the number of parcels in the truck.
     */
    public static int CreateNeighborhoodConf() {
        return 0;
    }

    /**
     * Evaluates the total value of the truck using the values of A,B and C parcels,
     * respectively.
     * 
     * @return the total value.
     */
    public static int EvaluateTotalValue() {
        return 0;

    }

    /**
     * Fills the truck with the best configuration.
     * 
     * @return the current truck.
     */
    public static int fillTruck() {
        return 0;

    }

    /**
     * Checks whether there is enough space left to put a parcel.
     * 
     * @return false if it is possible to put at least one parcel in it; true if the
     *         truck is completely full.
     */
    public static boolean checkTruckFilled() {
        return true;
    }

    /**
     * Simulated Annealing Algorithm.
     * 
     * @param "difference" indicates the difference of the parcel number between the
     *                     configurations.
     * @param "betterConf" it is true if the configuration from neighborhood is
     *                     better; it is false the first configuration is better.
     */
    public static void SAalgorithm() {
        int difference = CreateConfiguration() - CreateNeighborhoodConf();
        boolean betterConf;
        while (temperature == lastTemp || checkTruckFilled() == true) { // the terminated restriction can be changed.
            if (difference > 0) {
                CreateNeighborhoodConf();
                betterConf = true;
            } else if (Math.exp(difference / temperature) > Math.random()) {
                CreateNeighborhoodConf();
                betterConf = true;
            } else {
                CreateConfiguration();
                betterConf = false;
            }
            CoolingProcess();
            fillTruck();

        }
    }
}
