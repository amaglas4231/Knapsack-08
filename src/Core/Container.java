package Core;

public class Container {
    private int length;
    private int height;
    private int width;
    private int[][][] container; // 3d representation of the container

    private int value;

    /**
     * Default Constructor sets the length to 33 (16.5 * 2), height to 5 (2.5 * 2),
     * width to 8 (4 * 2) and the container to a new empty array
     */
    public Container() {
        length = 33;
        height = 5;
        width = 8;

        container = new int[length][height][width];
    }

    /**
     * Private Constructor that makes a new container based on a given 3d array.
     * 
     * @param cont - 3d representation of the container
     */
    private Container(int[][][] cont, int value) {
        length = cont.length;
        height = cont[0].length;
        width = cont[0][0].length;

        this.container = cont;
        this.value = value;
    }

    public int[][][] getRepresentation() {
        return container;
    }

    /**
     * Getter for the length of the container.
     * 
     * @return - the length
     */
    public int getLength() {
        return length;
    }

    /**
     * Getter for the height of the container.
     * 
     * @return - the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter for the width of the container.
     * 
     * @return - the width
     */
    public int getWidth() {
        return width;
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
    public void increaseValue(int v) {
        value += v;
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
        int gap = 0;

        for (int x = 0; x < container.length; x++) {
            for (int y = 0; y < container[0].length; y++) {
                for (int z = 0; z < container[0][0].length; z++) {
                    if (container[x][y][z] == 0) {
                        gap++;
                    }
                }
            }
        }

        return gap;
    }

    /**
     * Method used to create an identical Container to the actual Container.
     * 
     * @return A new, identical Container.
     */
    public Container copy() {
        int[][][] newContainer = new int[container.length][container[0].length][container[0][0].length];

        for (int i = 0; i < container.length; i++) {
            for (int j = 0; j < container[0].length; j++) {
                for (int k = 0; k < container[0][0].length; k++) {
                    newContainer[i][j][k] = container[i][j][k];
                }
            }
        }

        return new Container(newContainer, this.value);
    }

    /**
     * Adds the 'piece' to the specified coordinates.
     * 
     * @param parcel - piece to be placed
     * @param x      - the x coordinate
     * @param y      - the y coordinate
     * @param z      - the z coordinate
     */
    public void addParcel(int[][][] parcel, int value, int x, int y, int z) {
        for (int i = 0; i < parcel.length; i++) {
            for (int j = 0; j < parcel[0].length; j++) {
                for (int k = 0; k < parcel[0][0].length; k++) {
                    // System.out.println(x + " " + y + " " + z);
                    container[x + i][y + j][z + k] = parcel[i][j][k] * value;
                }
            }
        }
    }

    /**
     * Checks if a certain piece fits in the container at the specified coordinates.
     * 
     * @param piece - piece to be placed
     * @param x     - the x coordinate
     * @param y     - the y coordinate
     * @param z     - the z coordinate
     * @return - 'true' if it can be placed
     */
    public boolean checkIfFits(int[][][] piece, int x, int y, int z) {
        if (x < 0 || y < 0 || z < 0) {
            return false;
        } else {
            for (int i = 0; i < piece.length; i++) {
                for (int j = 0; j < piece[i].length; j++) {
                    for (int k = 0; k < piece[i][j].length; k++) {
                        if (piece[i][j][k] != 0) {
                            if (x + i >= container.length || y + j >= container[0].length
                                    || z + k >= container[0][0].length || container[x + i][y + j][z + k] != 0) {
                                return false;
                            }

                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Method used to print the contents of the container.
     * Used for debugging only.
     */
    public void printContainer() {
        for (int i = 0; i < container.length; i++) {
            for (int j = 0; j < container[0][0].length; j++) {
                for (int k = 0; k < container[0].length; k++) {
                    System.out.print(container[i][k][j] + " ");
                }
                System.out.println(" ");
            }
            System.out.println(" ");
        }
    }

}