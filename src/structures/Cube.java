package structures;

/** Abstract class for Rubik's cubes. */
public abstract class Cube {
    /** See the documentation about the indexing. */
    private Side[] sides;
    private int cubeSize;

    /** Default constructor, paints the cube the official, done way. */
    public Cube(int cubeSize) {
        this.cubeSize = cubeSize;
        this.sides = new Side[6];
        Color[] order = { Color.WHITE, Color.BLUE, Color.ORANGE, Color.GREEN, Color.RED, Color.YELLOW };
        for (int i = 0; i < this.sides.length; i++) {
            this.sides[i] = new Side(cubeSize, order[i]);
        }
    }

    /**
     * @param sides Must have a length of 6.
     * @throws IllegalArgumentException If the parameter doesn't have a length of 6
     *                                  or is null.
     */
    public Cube(int cubeSize, Side[] sides) throws IllegalArgumentException {
        this.cubeSize = cubeSize;
        if (sides == null || sides.length != 6) {
            throw new IllegalArgumentException();
        }
        this.sides = sides;
    }

    /**
     * 
     * @return true If every side has only one color, false otherwise.
     */
    public Boolean areAllSidesOneColor() {
        for (Side i : this.sides) {
            if (!i.isAllTileTheSameColor()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Top side
        sb.append(this.generateDivider(1));
        for (int i = 0; i < this.cubeSize; i++) {
            sb.append("\n|");
            for (int j = 0; j < this.cubeSize; j++) {
                sb.append(" " + this.sides[0].getTileColor(i, j).toString() + " |");
            }
            sb.append("\n" + this.generateDivider(1));
        }
        // "long" side
        sb.append("\n" + this.generateDivider(4));
        for (int i = 0; i < this.cubeSize; i++) {
            sb.append("\n");
            for (int j = 1; j <= 4; j++) {
                sb.append("|");
                for (int k = 0; k < this.cubeSize; k++) {
                    sb.append(" " + this.sides[j].getTileColor(i, k) + " |");
                }
            }
            sb.append("\n" + this.generateDivider(4));
        }
        // Bottom side
        sb.append("\n" + this.generateDivider(1));
        for (int i = 0; i < this.cubeSize; i++) {
            sb.append("\n|");
            for (int j = 0; j < this.cubeSize; j++) {
                sb.append(" " + this.sides[5].getTileColor(i, j).toString() + " |");
            }
            sb.append("\n" + this.generateDivider(1));
        }
        return sb.toString();
    }

    private String generateDivider(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("+");
            for (int j = 0; j < this.cubeSize; j++) {
                sb.append("---+");
            }
        }
        return sb.toString();
    }
}
