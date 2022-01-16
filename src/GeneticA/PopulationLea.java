package GeneticA;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class PopulationLea {
    public static int PopulationSize = 100;
    public static int[] Chromosomes = new int[3];
    public static int a = 5;
    public static double[] score;
    public static ArrayList<ArrayList<Integer>> Population = new ArrayList<ArrayList<Integer>>();

    public static ArrayList<ArrayList<Integer>> InitializePop() {
        for (int i = 0; i < PopulationSize; i++) {
            ArrayList<Integer> element = new ArrayList<Integer>();
            element.add(ThreadLocalRandom.current().nextInt(10, 25));
            element.add(ThreadLocalRandom.current().nextInt(10, 25));
            element.add(ThreadLocalRandom.current().nextInt(10, 25));
            Population.add(element);
        }

        return Population;
    }

    // Used for Pentoes
    public static ArrayList<ArrayList<Integer>> InitializePop2() {
        for (int i = 0; i < PopulationSize; i++) {
            ArrayList<Integer> element = new ArrayList<Integer>();
            element.add(ThreadLocalRandom.current().nextInt(10, 200));
            element.add(ThreadLocalRandom.current().nextInt(10, 200));
            element.add(ThreadLocalRandom.current().nextInt(10, 200));
            Population.add(element);
        }
        
        return Population;
    }
}
