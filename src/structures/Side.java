package structures;

/** Class to represent the sides of the Rubik's cube. */
class Side {
    /** The size of the cube, e.g. 3 means a 3*3 cube. */
    private int cubeSize;

    /** Square shaped array to represent the colors of the side. */
    private Color[][] colors;

    /** Default constructor, paints the side to one color. */
    public Side(int cubeSize, Color c) {
        this.cubeSize = cubeSize;
        this.colors = new Color[this.cubeSize][this.cubeSize];
        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < colors[i].length; j++) {
                this.colors[i][j] = c;
            }
        }
    }

    /**
     * @param colors Must be a square shaped 2 dimensional array.
     * @throws IllegalArgumentException If the parameter doesn't math cubeSize.
     */
    public Side(int cubeSize, Color[][] colors) throws IllegalArgumentException {
        this.cubeSize = cubeSize;
        if (colors == null || colors.length != this.cubeSize) {
            throw new IllegalArgumentException();
        }
        for (Color[] i : colors) {
            if (i == null || i.length != this.cubeSize) {
                throw new IllegalArgumentException();
            }
        }
        this.colors = colors;
    }

    /**
     * @param i First coordinate of the tile on the side as per the documentation.
     * @param j Second coordinate of the tile on the side as per the documentation.
     * @return The given tile.
     */
    public Color getTileColor(int i, int j) {
        return this.colors[i][j];
    }

    /**
     * @param i First coordinate of the tile on the side as per the documentation.
     * @param j Second coordinate of the tile on the side as per the documentation.
     * @param c The color to set on the selected tile.
     */
    public void setTileColor(int i, int j, Color c) {
        this.colors[i][j] = c;
    }

    /**
     * 
     * @return True if every tile is the same color, false otherwise.
     */
    public Boolean isAllTileTheSameColor() {
        Color first = this.colors[0][0];
        for (Color[] i : this.colors) {
            for (Color j : i) {
                if (!j.equals(first)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Method to compare if 2 sides are the same. (Rotated sides will not the same.)
     * @param rhs The side to compare.
     * @return true If the sides are the same, false otherwise.
     */
    public Boolean equals(Side rhs) {
        for (int i = 0; i < this.cubeSize; i++) {
            for (int j = 0; j < this.cubeSize; j++) {
                if (!this.colors[i][j].equals(rhs.getTileColor(i, j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
