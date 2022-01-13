package GeneticA;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import Core.UserVariables;

public class GeneticAlgorithm {
    private static int PopulationSize = 100;
    private static double MutationRate = 0.75; // idk rn
    // private static int Generation = 0;
    private static int maxIteration = 1000;
    public static boolean Placed = true;

    static int[][][][] parcelARotations;
    static int[][][][] parcelBRotations;
    static int[][][][] parcelCRotations;

    /**
     * 
     */
    public static ArrayList<ArrayList<Integer>> Crossover() {
        /* CROSSOVER AND BABIES CREATION */
        // Using tournament selection
        ArrayList<ArrayList<Integer>> selectedPop = new ArrayList<ArrayList<Integer>>();
        for (int j = 0; j < 10; j++) {
            ArrayList<ArrayList<Integer>> SubGroup = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i < 5; i++) {
                SubGroup.add(PopulationLea.Population.get(ThreadLocalRandom.current().nextInt(0, 100))); // randomly
                                                                                                         // create
                                                                                                         // subgroups of
                                                                                                         // 5 from
                                                                                                         // initial
                                                                                                         // Population
            }

            double[] score = new double[SubGroup.size()];
            FitnessFunction(SubGroup, score);
            quickSort(score, 0, SubGroup.size() - 1, SubGroup);
            Integer n1 = ThreadLocalRandom.current().nextInt(0, 3);
            Integer n2 = ThreadLocalRandom.current().nextInt(0, 3);
            Integer n3 = SubGroup.get(4).get(n1); // 4 is best one since fitness fonction sorts individuals in
                                                  // increasing order
            SubGroup.get(4).set(n2, SubGroup.get(3).get(n2));
            SubGroup.get(3).set(n1, n3);

            for (int i = 0; i < 5; i++) { // restoring the population to 100 (20*5)
                ArrayList<Integer> one = new ArrayList<Integer>();
                ArrayList<Integer> two = new ArrayList<Integer>();
                for (int l = 0; l < 3; l++) {
                    one.add(SubGroup.get(4).get(l));
                    two.add(SubGroup.get(3).get(l));
                }
                selectedPop.add(one); // selecting the best element from subgroup
                selectedPop.add(two); // selecting the second best element from subgroup
            }
        }
        return selectedPop;
    }
    /* END OF BABY FACTORY */

    public static ArrayList<ArrayList<Integer>> MutationsBabyyy(ArrayList<ArrayList<Integer>> Pop) {
        // Pop = Crossover();
        for (int i = 0; i < PopulationSize; i++) {
            int index = ThreadLocalRandom.current().nextInt(0, 3);
            Integer index2;
            if (Pop.get(i).get(index) > 0) {
                index2 = Pop.get(i).get(index) + ThreadLocalRandom.current().nextInt(-10, 11);
            } else { // adding a number between -10 and 10
                index2 = Pop.get(i).get(index) + ThreadLocalRandom.current().nextInt(0, 11);
            }
            if (MutationRate > Math.random()) { // mutate 1/4 times
                Pop.get(i).set(index, index2);
            }
        }
        /*
         * System.out.println("-----------POP post mutation-----------");
         * for(int j = 0; j<PopulationSize; j++){
         * System.out.println(PopulationLea.Population.get(j));
         * }
         * /*try{
         * Thread.sleep(1000);
         * }
         * catch(InterruptedException ex)
         * {
         * Thread.currentThread().interrupt();
         * }
         */
        return Pop;
    }

    /* INPUT IS IN ORDER A C D */
    public static void FitnessFunction(ArrayList<ArrayList<Integer>> SubGroup, double[] score) {
        for (int i = 0; i < SubGroup.size(); i++) {
            ArrayList<Integer> o = new ArrayList<Integer>();
            o = SubGroup.get(i);
            int[] result = DepthSearch.depthSearching(SubGroup.get(i)); /*
                                                                         * difference entre volume total et volume pris
                                                                         */
            if (result[1] > UserVariables.ContainerVolume) {
                score[i] = UserVariables.ContainerVolume - (result[1]);
            } else {
                score[i] = 0;
            }
            score[i] += result[0];
        }
    }

    // A utility function to swap two elements
    static void swap(double[] arr, int i, int j, ArrayList<ArrayList<Integer>> SubGroup) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        ArrayList<Integer> temp2 = SubGroup.get(i);
        SubGroup.set(i, SubGroup.get(j));
        SubGroup.set(j, temp2);
    }

    // COEUR SUR TOI CHOUPINETTE!!!<3
    static int partition(double[] arr, int low, int high, ArrayList<ArrayList<Integer>> SubGroup) {

        double pivot = arr[high];

        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j, SubGroup);
            }
        }
        swap(arr, i + 1, high, SubGroup);
        return (i + 1);
    }

    static void quickSort(double[] arr, int low, int high, ArrayList<ArrayList<Integer>> SubGroup) {

        /*
         * https://www.google.com/search?q=quicksort+gif&rlz=1C1CHBF_frFR784FR784&sxsrf=
         * AOaemvJFx42IbUuRYt1qfWb964dDE5-97w:1641815371601&source=lnms&tbm=isch&sa=X&
         * ved=2ahUKEwjL9Irwjqf1AhVMsaQKHWJZAZkQ_AUoAXoECAEQAw
         */
        if (low < high) {

            int pi = partition(arr, low, high, SubGroup);
            quickSort(arr, low, pi - 1, SubGroup);
            quickSort(arr, pi + 1, high, SubGroup);
        }
    }

    public static void RunGa() {
        PopulationLea.InitializePop();

        for (int i = 0; i < maxIteration; i++) {
            ArrayList<ArrayList<Integer>> selectedPop = Crossover();
            selectedPop = MutationsBabyyy(selectedPop);
            double[] score = new double[selectedPop.size()];
            FitnessFunction(selectedPop, score);
            quickSort(score, 0, selectedPop.size() - 1, selectedPop);
            PopulationLea.Population = selectedPop;
            PopulationLea.score = score;
            FitnessFunction(PopulationLea.Population, score);
            quickSort(score, 0, PopulationLea.Population.size() - 1, PopulationLea.Population);

            /*
             * System.out.println("-----------POP sorted-----------");
             * for(int j = 0; j<PopulationSize; j++){
             * System.out.println(PopulationLea.Population.get(j));
             * System.out.println("Score:"+score[j]);
             * }
             * /*try{
             * Thread.sleep(1000);
             * }
             * catch(InterruptedException ex)
             * {
             * Thread.currentThread().interrupt();
             * }
             */

            // we take the index 0 of the selected pop
            /*
             * ArrayList<Integer> bestChromosome = selectedPop.get(selectedPop.size()-1);
             * //we take the last chromosome of the selected pop since its the best one
             * System.out.println("--------------PARTIAL SOLUTION-------------");
             * System.out.println("Number of Pentoes : ["+ " T : "+
             * bestChromosome.get(0)+" P : "+bestChromosome.get(1)+" L : "+bestChromosome.
             * get(2));
             * System.out.println("Best score is : "+score[selectedPop.size()-1]); //score
             * of fitness function
             */
        }
        double[] score = new double[PopulationLea.Population.size()];
        FitnessFunction(PopulationLea.Population, score);
        quickSort(score, 0, PopulationLea.Population.size() - 1, PopulationLea.Population);
        System.out.println("-----------POP sorted-----------");
        for (int j = 0; j < PopulationSize; j++) {
            System.out.println(PopulationLea.Population.get(j));
            System.out.println("Score:" + score[j]);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        ArrayList<Integer> bestChromosome = PopulationLea.Population.get(PopulationLea.Population.size() - 1);
        System.out.println("---------------FINAL SOLUTION---------------");
        System.out.println("Number of Pentoes : [" + " T : " + bestChromosome.get(0) + " P : " + bestChromosome.get(1)
                + " L : " + bestChromosome.get(2) + " ]");
        System.out.println("Best fitness score is : " + PopulationLea.score[PopulationLea.Population.size() - 1]); // score
                                                                                                                   // of
                                                                                                                   // fitness
                                                                                                                   // function
        System.out.println("Best score is : "
                + ((bestChromosome.get(0) * 5) + (bestChromosome.get(1) * 4) + (bestChromosome.get(2) * 3)));

    }

    public static void main(String[] args) {
        DepthSearch.InitialisePento();
        // for(int i = 0 ; i<10 ; i++){
        RunGa();

    }

    /*
     * PARCELS
     * /*if(PopulationLea.Population.get(i).get(0)*2.0 +
     * PopulationLea.Population.get(i).get(1)*3.375 +
     * PopulationLea.Population.get(i).get(2)*3.0 > UserVariables.ContainerVolume){
     * /*difference entre volume total et volume pris
     * /*volume important que si rentre pas
     * score[i] = UserVariables.ContainerVolume -
     * (PopulationLea.Population.get(i).get(0)*2.0 +
     * PopulationLea.Population.get(i).get(1)*3.375 +
     * PopulationLea.Population.get(i).get(2)*3.0);
     * }else{
     * score[i]=0;
     * }
     */

}
