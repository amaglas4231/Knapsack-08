package Core;

/*
* class to contain all rotations of pentominoes and parcels 
*/
public class Rotations {
    // Each 1 corresponds to 0.5m
    //

    public static int[][][][] getL() {
        int[][][][] LInt =
                { { { { 1 }, { 1 }, { 1 }, { 1 } }, { { 1 }, { 0 }, { 0 }, { 0 } } },
                        { { { 1, 1, 1, 1 } }, { { 1, 0, 0, 0 } } },
                        { { { 1 }, { 1 }, { 1 }, { 1 } }, { { 0 }, { 0 }, { 0 }, { 1 } } },
                        { { { 1, 1, 1, 1 } }, { { 0, 0, 0, 1 } } },
                        { { { 1 }, { 0 }, { 0 }, { 0 } }, { { 1 }, { 1 }, { 1 }, { 1 } } },
                        { { { 1, 0, 0, 0 } }, { { 1, 1, 1, 1 } } },
                        { { { 0 }, { 0 }, { 0 }, { 1 } }, { { 1 }, { 1 }, { 1 }, { 1 } } },
                        { { { 0, 0, 0, 1 } }, { { 1, 1, 1, 1 } } },
                        { { { 1, 1 }, { 1, 0 }, { 1, 0 }, { 1, 0 } } },
                        { { { 1, 0, 0, 0 }, { 1, 1, 1, 1 } } },
                        { { { 0, 1 }, { 0, 1 }, { 0, 1 }, { 1, 1 } } },
                        { { { 1, 1, 1, 1 }, { 0, 0, 0, 1 } } },
                        { { { 1, 1 }, { 0, 1 }, { 0, 1 }, { 0, 1 } } },
                        { { { 1, 1, 1, 1 }, { 1, 0, 0, 0 } } },
                        { { { 1, 0 }, { 1, 0 }, { 1, 0 }, { 1, 1 } } },
                        { { { 0, 0, 0, 1 }, { 1, 1, 1, 1 } } },
                        { { { 1 }, { 0 } }, { { 1 }, { 0 } }, { { 1 }, { 0 } }, { { 1 }, { 1 } } },
                        { { { 1, 0 } }, { { 1, 0 } }, { { 1, 0 } }, { { 1, 1 } } },
                        { { { 0 }, { 1 } }, { { 0 }, { 1 } }, { { 0 }, { 1 } }, { { 1 }, { 1 } } },
                        { { { 0, 1 } }, { { 0, 1 } }, { { 0, 1 } }, { { 1, 1 } } },
                        { { { 1 }, { 1 } }, { { 1 }, { 0 } }, { { 1 }, { 0 } }, { { 1 }, { 0 } } },
                        { { { 1, 1 } }, { { 1, 0 } }, { { 1, 0 } }, { { 1, 0 } } },
                        { { { 1 }, { 1 } }, { { 0 }, { 1 } }, { { 0 }, { 1 } }, { { 0 }, { 1 } } },
                        { { { 1, 1 } }, { { 0, 1 } }, { { 0, 1 } }, { { 0, 1 } } } };

        return LInt;
    }

    public static int[][][][] getP() {
        int[][][][] PInt =
                { { { { 1, 1 }, { 1, 1 }, { 1, 0 } } },
                        { { { 1, 1, 0 }, { 1, 1, 1 } } },
                        { { { 0, 1 }, { 1, 1 }, { 1, 1 } } },
                        { { { 1, 1, 1 }, { 0, 1, 1 } } },
                        { { { 1, 1 }, { 1, 1 }, { 0, 1 } } },
                        { { { 1, 1, 1 }, { 1, 1, 0 } } },
                        { { { 1, 0 }, { 1, 1 }, { 1, 1 } } },
                        { { { 0, 1, 1 }, { 1, 1, 1 } } },
                        { { { 1 }, { 1 }, { 0 } }, { { 1 }, { 1 }, { 1 } } },
                        { { { 1, 1, 0 } }, { { 1, 1, 1 } } },
                        { { { 0 }, { 1 }, { 1 } }, { { 1 }, { 1 }, { 1 } } },
                        { { { 0, 1, 1 } }, { { 1, 1, 1 } } },
                        { { { 1 }, { 1 }, { 1 } }, { { 1 }, { 1 }, { 0 } } },
                        { { { 1, 1, 1 } }, { { 1, 1, 0 } } },
                        { { { 1 }, { 1 }, { 1 } }, { { 0 }, { 1 }, { 1 } } },
                        { { { 1, 1, 1 } }, { { 0, 1, 1 } } },
                        { { { 1 }, { 0 } }, { { 1 }, { 1 } }, { { 1 }, { 1 } } },
                        { { { 1, 0 } }, { { 1, 1 } }, { { 1, 1 } } },
                        { { { 0 }, { 1 } }, { { 1 }, { 1 } }, { { 1 }, { 1 } } },
                        { { { 0, 1 } }, { { 1, 1 } }, { { 1, 1 } } },
                        { { { 1 }, { 1 } }, { { 1 }, { 1 } }, { { 0 }, { 1 } } },
                        { { { 1, 1 } }, { { 1, 1 } }, { { 0, 1 } } },
                        { { { 1 }, { 1 } }, { { 1 }, { 1 } }, { { 1 }, { 0 } } },
                        { { { 1, 1 } }, { { 1, 1 } }, { { 1, 0 } } } };

        return PInt;
    }

    public static int[][][][] getT() {
        int[][][][] TInt =
                { { { { 1, 1, 1 }, { 0, 1, 0 }, { 0, 1, 0 } } },
                        { { { 1, 0, 0 }, { 1, 1, 1 }, { 1, 0, 0 } } },
                        { { { 0, 1, 0 }, { 0, 1, 0 }, { 1, 1, 1 } } },
                        { { { 0, 0, 1 }, { 1, 1, 1 }, { 0, 0, 1 } } },
                        { { { 1 }, { 0 }, { 0 } }, { { 1 }, { 1 }, { 1 } }, { { 1 }, { 0 }, { 0 } } },
                        { { { 1, 0, 0 } }, { { 1, 1, 1 } }, { { 1, 0, 0 } } },
                        { { { 0 }, { 0 }, { 1 } }, { { 1 }, { 1 }, { 1 } }, { { 0 }, { 0 }, { 1 } } },
                        { { { 0, 0, 1 } }, { { 1, 1, 1 } }, { { 0, 0, 1 } } },
                        { { { 0 }, { 1 }, { 0 } }, { { 0 }, { 1 }, { 0 } }, { { 1 }, { 1 }, { 1 } } },
                        { { { 0, 1, 0 } }, { { 0, 1, 0 } }, { { 1, 1, 1 } } },
                        { { { 1 }, { 1 }, { 1 } }, { { 0 }, { 1 }, { 0 } }, { { 0 }, { 1 }, { 0 } } },
                        { { { 1, 1, 1 } }, { { 0, 1, 0 } }, { { 0, 1, 0 } } } };

        return TInt;
    }

    public static int[][][][] getA() {
        int[][][][] AInt =
                { { { { 1, 1, 1, 1 }, { 1, 1, 1, 1 } }, { { 1, 1, 1, 1 }, { 1, 1, 1, 1 } } },
                        { { { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 } }, { { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 } } },
                        { { { 1, 1 }, { 1, 1 } }, { { 1, 1 }, { 1, 1 } }, { { 1, 1 }, { 1, 1 } },
                                { { 1, 1 }, { 1, 1 } } } };

        return AInt;
    }

    public static int[][][][] getB() {
        int[][][][] BInt =
                { { { { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 } },
                        { { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 } } },
                        { { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } },
                                { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } } },
                        { { { 1, 1 }, { 1, 1 }, { 1, 1 } }, { { 1, 1 }, { 1, 1 }, { 1, 1 } },
                                { { 1, 1 }, { 1, 1 }, { 1, 1 } }, { { 1, 1 }, { 1, 1 }, { 1, 1 } } },
                        { { { 1, 1, 1, 1 }, { 1, 1, 1, 1 } }, { { 1, 1, 1, 1 }, { 1, 1, 1, 1 } },
                                { { 1, 1, 1, 1 }, { 1, 1, 1, 1 } } },
                        { { { 1, 1, 1 }, { 1, 1, 1 } }, { { 1, 1, 1 }, { 1, 1, 1 } }, { { 1, 1, 1 }, { 1, 1, 1 } },
                                { { 1, 1, 1 }, { 1, 1, 1 } } },
                        { { { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 } }, { { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 } },
                                { { 1, 1 }, { 1, 1 }, { 1, 1 }, { 1, 1 } } } };

        return BInt;
    }

    public static int[][][][] getC() {
        int[][][][] CInt =
                { { { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } }, { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } },
                        { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } } } };

        return CInt;
    }
}
