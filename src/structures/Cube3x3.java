package structures;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/** Class to represent a whole 3*3 Rubik's cube and to implement the turns. */
public class Cube3x3 extends Cube {

    public Cube3x3() {
        super(3);
    }

    public void Up(Boolean clockwise, Boolean doubleTurn) {
        int count = doubleTurn ? 2 : 1;
        for (int i = 0; i < count; i++) {
            this.rotateSide(this.getSides()[0], clockwise);

            Color[] colors = new Color[12];
            int colorsIndex = 0;
            List<Integer> affectedSides = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
            for (int j : affectedSides) {
                for (int k = 0; k < this.getSize(); k++) {
                    colors[colorsIndex++] = this.getSides()[j].getTileColor(0, k);
                }
            }
            colorsIndex = 0;
            if (clockwise) {
                affectedSides.add(0, affectedSides.remove(affectedSides.size() - 1));
            } else {
                affectedSides.add(affectedSides.remove(0));
            }
            for (int j : affectedSides) {
                for (int k = 0; k < this.getSize(); k++) {
                    this.getSides()[j].setTileColor(0, k, colors[colorsIndex++]);
                }
            }
        }
    }

    public void Down(Boolean clockwise, Boolean doubleTurn) {
        int count = doubleTurn ? 2 : 1;
        for (int i = 0; i < count; i++) {
            this.rotateSide(this.getSides()[5], clockwise);

            Color[] colors = new Color[12];
            int colorsIndex = 0;
            List<Integer> affectedSides = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
            for (int j : affectedSides) {
                for (int k = 0; k < this.getSize(); k++) {
                    colors[colorsIndex++] = this.getSides()[j].getTileColor(2, k);
                }
            }
            colorsIndex = 0;
            if (clockwise) {
                affectedSides.add(affectedSides.remove(0));

            } else {
                affectedSides.add(0, affectedSides.remove(affectedSides.size() - 1));
            }
            for (int j : affectedSides) {
                for (int k = 0; k < this.getSize(); k++) {
                    this.getSides()[j].setTileColor(2, k, colors[colorsIndex++]);
                }
            }
        }
    }

    public void Left(Boolean clockwise, Boolean doubleTurn) {

    }

    public void Right(Boolean clockwise, Boolean doubleTurn) {

    }

    public void Front(Boolean clockwise, Boolean doubleTurn) {

    }

    public void Back(Boolean clockwise, Boolean doubleTurn) {

    }
}
