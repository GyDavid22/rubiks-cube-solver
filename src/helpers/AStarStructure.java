package helpers;

public class AStarStructure {
    public Manipulator3x3 manipulator;
    public int score;
    public int depth;

    public AStarStructure(Manipulator3x3 manipulator, int score, int depth) {
        this.manipulator = manipulator;
        this.score = score;
        this.depth = depth;
    }
}
