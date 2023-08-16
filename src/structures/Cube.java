package structures;

/** Abstract class for Rubik's cubes. */
public abstract class Cube {
    /** See the documentation about the indexing. */
    private Side[] sides;
    private int cubeSize;

    /** Paints the cube the official, done way. */
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

    protected Side[] getSides() {
        return this.sides;
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

    public Boolean equals(Cube rhs) {
        Boolean result = true;
        for (int i = 0; i < 6; i++) {
            if (!this.sides[i].equals(rhs.getSides()[i])) {
                return false;
            }
        }
        return result;
    }

    protected void rotateSide(Side s, Boolean clockwise) {
        this.transpose(s);
        if (clockwise) {
            this.reverseRows(s);
        } else {
            this.reverseCols(s);
        }
    }

    private void reverseCols(Side s) {
        for (int i = 0; i < this.cubeSize / 2; i++) {
            for (int j = 0; j < this.cubeSize; j++) {
                Color temp = s.getTileColor(i, j);
                s.setTileColor(i, j, s.getTileColor(this.cubeSize - i - 1, j));
                s.setTileColor(this.cubeSize - i - 1, j, temp);
            }
        }
    }

    private void reverseRows(Side s) {
        for (int i = 0; i < this.cubeSize; i++) {
            for (int j = 0; j < this.cubeSize / 2; j++) {
                Color temp = s.getTileColor(i, j);
                s.setTileColor(i, j, s.getTileColor(i, this.cubeSize - j - 1));
                s.setTileColor(i, this.cubeSize - j - 1, temp);
            }
        }
    }

    private void transpose(Side s) {
        for (int i = 0; i < this.cubeSize; i++) {
            for (int j = i; j < this.cubeSize; j++) {
                Color temp = s.getTileColor(j, i);
                s.setTileColor(j, i, s.getTileColor(i, j));
                s.setTileColor(i, j, temp);
            }
        }
    }
}
