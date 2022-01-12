package Implementation;

public class Container {
    private int length;
    private int height;
    private int width;
    private int[][][] container;

    private int value;

    /**
     * Default container sets the length to 33 (16.5 * 2), height to 5 (2.5 * 2),
     * width to 8 (4 * 2) and the container to a new empty array
     */
    public Container() {
        length = 33;
        height = 5;
        width = 8;

        container = new int[length][height][width];
    }

    /**
     * 
     * @param cont
     */
    public Container(int[][][] cont) {
        length = cont.length;
        height = cont[0].length;
        width = cont[0][0].length;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < length; k++) {
                    container[i][j][k] = cont[i][j][k];
                }
            }
        }
    }

    /**
     * Getter for the value of the entire container.
     * 
     * @return - the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Setter for the value of the container. Used to modify the value every time a
     * new parcel is added.
     * 
     * @param v - new value to be set
     */
    public void setValue(int v) {
        value = v;
    }

    /**
     * @return - the volume of the container based on the length, height and width
     */
    public int getVolume() {
        return length * height * width;
    }

    /**
     * @return
     */
    public int getParcelVolume() {
        return getVolume() - getGapAmount();
    }

    /**
     * Method to get the amounts of gap left in the Container as a whole.
     * 
     * @return - number of gaps in Container.
     */
    public int getGapAmount() {
        // TODO: count the n.o. gaps
        return 0;
    }

    /**
     * Method used to create an identical Container to the actual Container.
     * 
     * @return A new, identical Container.
     */
    public Container copy() {
        int[][][] newContainer = new int[container.length][container[0].length][container[0][0].length];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < length; k++) {
                    newContainer[i][j][k] = container[i][j][k];
                }
            }
        }

        return new Container(newContainer);
    }

    public void addParcel(int[][][] toPlace) {

        /*
         * if (!checkEmpty(parcel, position)) {
         * for (int i = 0; i < toPlace.length; i++) {
         * for (int j = 0; j < toPlace[i].length; j++) {
         * for (int k = 0; k < toPlace[i][j].length; k++) {
         * if (parcel[i][j][k] == 1) {
         * container[position[0] + i][position[1] + j][position[2] + k] = 1; //
         * }
         * }
         * }
         * }
         * }
         */
    }

    public boolean checkIfFits(int[][][] piece, int x, int y, int z) {
        if (x < 0 || y < 0 || z < 0) {
            return false;
        } else {
            for (int i = 0; i < piece.length; i++) {
                for (int j = 0; j < piece[i].length; j++) {
                    for (int k = 0; k < piece[i][j].length; k++) {
                        if (piece[i][j][k] == 1) {
                            if (x + i > container.length || y + j > container[0].length
                                    || z + k > container[0][0].length || container[x + i][y + j][z + k] != 1) {
                                return false;
                            }

                        }
                    }
                }
            }
        }
        return true;
    }

}
/*
 * private int[][][] rep; // 33 X 5 X 8 or 16.5 x 2.5 x 4
 * 
 * public Container(int[][][] representation) {
 * rep = representation;
 * }
 * 
 * public void addToContainer(int[][][] is, int v) {
 * for (int x = 0; x < rep.length; x++) {
 * for (int y = 0; y < rep.length; y++) {
 * for (int z = 0; z < rep.length; z++) {
 * if(rep[x][y][z] == 0)
 * rep[x][y][z] = v;
 * }
 * }
 * }
 * }
 * 
 * public void changeRep(int x, int y, int z) {
 * rep[x][y][z] = 1;
 * }
 * 
 * public int[][][] getRepresentation() {
 * return rep;
 * }
 * 
 * public void setRepresentation(int[][][] r) {
 * rep = r;
 * }
 * 
 * public int getElement(int x, int y, int z) {
 * return rep[x][y][z];
 * }
 * 
 * public int getValue() {
 * int value = 0;
 * for (int x = 0; x < rep.length; x++) {
 * for (int y = 0; y < rep.length; y++) {
 * for (int z = 0; z < rep.length; z++) {
 * value += rep[x][y][z];
 * }
 * }
 * }
 * 
 * return value;
 * }
 * 
 * public int getGapAmount() {
 * int gap = 0;
 * for (int x = 0; x < rep.length; x++) {
 * for (int y = 0; y < rep.length; y++) {
 * for (int z = 0; z < rep.length; z++) {
 * if (rep[x][y][z] == 0) {
 * gap++;
 * }
 * }
 * }
 * }
 * 
 * return gap;
 * }
 */