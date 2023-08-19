package structures;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/** Class to represent a whole 3*3 Rubik's cube and to implement the turns. */
public class Cube3x3 extends Cube {

    public Cube3x3() {
        super(3);
    }

    private Cube3x3(Side[] sides) {
        super(3, sides);
    }

    /** U, U', 2U, 2U' */
    public void Up(Boolean clockwise, Boolean doubleTurn) {
        int count = doubleTurn ? 2 : 1;
        for (int i = 0; i < count; i++) {
            // rotate "up" side
            this.rotateSide(this.getSides()[0], clockwise);

            // collect color data from affected other sides
            Color[] colors = new Color[12];
            int colorsIndex = 0;
            List<Integer> affectedSides = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
            for (int j : affectedSides) {
                for (int k = 0; k < this.getSize(); k++) {
                    colors[colorsIndex++] = this.getSides()[j].getTileColor(0, k);
                }
            }

            // shifting affectedSides, so repainting the affected sides, shifted by 1
            // compared to collecting
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

    /** D, D', 2D, 2D' */
    public void Down(Boolean clockwise, Boolean doubleTurn) {
        // same as up but with different numbers
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

    /** L, L', 2L, 2L' */
    public void Left(Boolean clockwise, Boolean doubleTurn) {
        int count = doubleTurn ? 2 : 1;
        for (int i = 0; i < count; i++) {
            // rotate "left" side
            this.rotateSide(this.getSides()[4], clockwise);

            // collect color data from 3 neighboring sides (same indexing works)
            Color[][] colors = new Color[4][this.getSize()];
            int[] affectedSides = { 0, 1, 5 }; // + 3
            for (int j = 0; j < affectedSides.length; j++) {
                for (int k = 0; k < this.getSize(); k++) {
                    colors[j][k] = this.getSides()[affectedSides[j]].getTileColor(k, 0);
                }
            }
            // collect color data from not neighboring side seperately because it needs a
            // different indexing
            for (int j = 0; j < this.getSize(); j++) {
                colors[3][j] = this.getSides()[3].getTileColor(j, 2);
            }

            if (clockwise) {
                // repainting neighboring sides, shifted
                for (int j = 1; j < affectedSides.length; j++) {
                    for (int k = 0; k < this.getSize(); k++) {
                        this.getSides()[affectedSides[j]].setTileColor(k, 0, colors[j - 1][k]);
                    }
                }
                // repainting the non-neighboring side, fetching colors "upside down", because
                // this is how it looks like from the "front"
                for (int j = 0; j < this.getSize(); j++) {
                    this.getSides()[3].setTileColor(this.getSize() - j - 1, 2, colors[2][j]);
                }
                // repainting the last neighboring side, upside down again for the same reason
                for (int j = 0; j < this.getSize(); j++) {
                    this.getSides()[0].setTileColor(this.getSize() - j - 1, 0, colors[3][j]);
                }
            } else {
                // same logic as above
                for (int j = 0; j < affectedSides.length - 1; j++) {
                    for (int k = 0; k < this.getSize(); k++) {
                        this.getSides()[affectedSides[j]].setTileColor(k, 0, colors[j + 1][k]);
                    }
                }
                for (int j = 0; j < this.getSize(); j++) {
                    this.getSides()[3].setTileColor(this.getSize() - j - 1, 2, colors[0][j]);
                }
                for (int j = 0; j < this.getSize(); j++) {
                    this.getSides()[5].setTileColor(this.getSize() - j - 1, 0, colors[3][j]);
                }
            }
        }
    }

    /** R, R', 2R, 2R' */
    public void Right(Boolean clockwise, Boolean doubleTurn) {
        // same as left but with different numbers and clockwise and counterclockwise
        // swapped because this is how it looks like from the front
        int count = doubleTurn ? 2 : 1;
        for (int i = 0; i < count; i++) {
            this.rotateSide(this.getSides()[2], clockwise);

            Color[][] colors = new Color[4][this.getSize()];
            int[] affectedSides = { 0, 1, 5 }; // + 3
            for (int j = 0; j < affectedSides.length; j++) {
                for (int k = 0; k < this.getSize(); k++) {
                    colors[j][k] = this.getSides()[affectedSides[j]].getTileColor(k, 2);
                }
            }
            for (int j = 0; j < this.getSize(); j++) {
                colors[3][j] = this.getSides()[3].getTileColor(j, 0);
            }

            if (clockwise) {
                for (int j = 0; j < affectedSides.length - 1; j++) {
                    for (int k = 0; k < this.getSize(); k++) {
                        this.getSides()[affectedSides[j]].setTileColor(k, 2, colors[j + 1][k]);
                    }
                }
                for (int j = 0; j < this.getSize(); j++) {
                    this.getSides()[3].setTileColor(this.getSize() - j - 1, 0, colors[0][j]);
                }
                for (int j = 0; j < this.getSize(); j++) {
                    this.getSides()[5].setTileColor(this.getSize() - j - 1, 2, colors[3][j]);
                }
            } else {
                for (int j = 1; j < affectedSides.length; j++) {
                    for (int k = 0; k < this.getSize(); k++) {
                        this.getSides()[affectedSides[j]].setTileColor(k, 2, colors[j - 1][k]);
                    }
                }
                for (int j = 0; j < this.getSize(); j++) {
                    this.getSides()[3].setTileColor(this.getSize() - j - 1, 0, colors[2][j]);
                }
                for (int j = 0; j < this.getSize(); j++) {
                    this.getSides()[0].setTileColor(this.getSize() - j - 1, 2, colors[3][j]);
                }
            }
        }
    }

    /** F, F', 2F, 2F' */
    public void Front(Boolean clockwise, Boolean doubleTurn) {
        int count = doubleTurn ? 2 : 1;
        for (int i = 0; i < count; i++) {
            this.rotateSide(this.getSides()[1], clockwise);

            // collecting colors from affected sides
            Color[][] colors = new Color[4][this.getSize()];
            for (int j = 0; j < this.getSize(); j++) {
                colors[0][j] = this.getSides()[0].getTileColor(2, j);
            }
            for (int j = 0; j < this.getSize(); j++) {
                colors[1][j] = this.getSides()[2].getTileColor(j, 0);
            }
            for (int j = 0; j < this.getSize(); j++) {
                colors[2][j] = this.getSides()[5].getTileColor(0, j);
            }
            for (int j = 0; j < this.getSize(); j++) {
                colors[3][j] = this.getSides()[4].getTileColor(j, 2);
            }

            // repainting colors
            if (clockwise) {
                for (int j = 0; j < colors[0].length; j++) {
                    this.getSides()[2].setTileColor(j, 0, colors[0][j]);
                }
                // indexing backwards because of the perspective
                for (int j = 0; j < colors[1].length; j++) {
                    this.getSides()[5].setTileColor(0, j, colors[1][this.getSize() - j - 1]);
                }
                for (int j = 0; j < colors[2].length; j++) {
                    this.getSides()[4].setTileColor(j, 2, colors[2][j]);
                }
                for (int j = 0; j < colors[3].length; j++) {
                    this.getSides()[0].setTileColor(2, j, colors[3][this.getSize() - j - 1]);
                }
            } else {
                for (int j = 0; j < colors[0].length; j++) {
                    this.getSides()[4].setTileColor(j, 2, colors[0][this.getSize() - j - 1]);
                }
                for (int j = 0; j < colors[1].length; j++) {
                    this.getSides()[0].setTileColor(2, j, colors[1][j]);
                }
                for (int j = 0; j < colors[2].length; j++) {
                    this.getSides()[2].setTileColor(j, 0, colors[2][this.getSize() - j - 1]);
                }
                for (int j = 0; j < colors[3].length; j++) {
                    this.getSides()[5].setTileColor(0, j, colors[3][j]);
                }
            }
        }
    }

    /** B, B', 2B, 2B' */
    public void Back(Boolean clockwise, Boolean doubleTurn) {
        int count = doubleTurn ? 2 : 1;
        for (int i = 0; i < count; i++) {
            this.rotateSide(this.getSides()[3], clockwise);

            // collecting colors from affected sides
            Color[][] colors = new Color[4][this.getSize()];
            for (int j = 0; j < this.getSize(); j++) {
                colors[0][j] = this.getSides()[0].getTileColor(0, j);
            }
            for (int j = 0; j < this.getSize(); j++) {
                colors[1][j] = this.getSides()[4].getTileColor(j, 0);
            }
            for (int j = 0; j < this.getSize(); j++) {
                colors[2][j] = this.getSides()[5].getTileColor(2, j);
            }
            for (int j = 0; j < this.getSize(); j++) {
                colors[3][j] = this.getSides()[2].getTileColor(j, 2);
            }

            // repainting colors
            if (clockwise) {
                // indexing backwards because of the perspective
                for (int j = 0; j < colors[0].length; j++) {
                    this.getSides()[4].setTileColor(j, 0, colors[0][this.getSize() - j - 1]);
                }
                for (int j = 0; j < colors[1].length; j++) {
                    this.getSides()[5].setTileColor(2, j, colors[1][j]);
                }
                for (int j = 0; j < colors[2].length; j++) {
                    this.getSides()[2].setTileColor(j, 2, colors[2][this.getSize() - j - 1]);
                }
                for (int j = 0; j < colors[3].length; j++) {
                    this.getSides()[0].setTileColor(0, j, colors[3][j]);
                }
            } else {
                for (int j = 0; j < colors[0].length; j++) {
                    this.getSides()[2].setTileColor(j, 2, colors[0][j]);
                }
                for (int j = 0; j < colors[1].length; j++) {
                    this.getSides()[0].setTileColor(0, j, colors[1][this.getSize() - j - 1]);
                }
                for (int j = 0; j < colors[2].length; j++) {
                    this.getSides()[4].setTileColor(j, 0, colors[2][j]);
                }
                for (int j = 0; j < colors[3].length; j++) {
                    this.getSides()[5].setTileColor(2, j, colors[3][this.getSize() - j - 1]);
                }
            }
        }
    }

    @Override
    public Cube3x3 clone() {
        Side[] newSides = new Side[6];
        for (int i = 0; i < newSides.length; i++) {
            newSides[i] = this.getSides()[i].clone();
        }
        return new Cube3x3(newSides);
    }
}
