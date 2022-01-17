package Core;

public class UserVariables {

    final static int container_width = 1650;
    final static int container_heigth = 250;
    final static int container_depth = 400;
    public static double ContainerVolume = 1320;

    static int constant = 50; //size of blocks 

    static int score = 0;

    static String InputType = "";
    static String ProblemType = "";
    static String algorithmType = "";
    
    public static int[][][] container = new int[container_width/constant][container_heigth/constant][container_depth/constant];

}
