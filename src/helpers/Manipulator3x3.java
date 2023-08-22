package helpers;

import java.util.ArrayList;
import java.util.List;

import structures.Cube3x3;

public class Manipulator3x3 implements Cloneable {
    private Cube3x3 cube;
    private final List<String> previousSteps = new ArrayList<>();
    private final IManipulation[] manipulations = { new IManipulation() {
        @Override
        public void execute(Boolean clockwise, Boolean doubleTurn) {
            cube.Up(clockwise, doubleTurn);
            addStep("U", clockwise, doubleTurn);
        }
    }, new IManipulation() {
        @Override
        public void execute(Boolean clockwise, Boolean doubleTurn) {
            cube.Down(clockwise, doubleTurn);
            addStep("D", clockwise, doubleTurn);
        }
    }, new IManipulation() {
        @Override
        public void execute(Boolean clockwise, Boolean doubleTurn) {
            cube.Left(clockwise, doubleTurn);
            addStep("L", clockwise, doubleTurn);
        }
    }, new IManipulation() {
        @Override
        public void execute(Boolean clockwise, Boolean doubleTurn) {
            cube.Right(clockwise, doubleTurn);
            addStep("R", clockwise, doubleTurn);
        }
    }, new IManipulation() {
        @Override
        public void execute(Boolean clockwise, Boolean doubleTurn) {
            cube.Front(clockwise, doubleTurn);
            addStep("F", clockwise, doubleTurn);
        }
    }, new IManipulation() {
        @Override
        public void execute(Boolean clockwise, Boolean doubleTurn) {
            cube.Back(clockwise, doubleTurn);
            addStep("B", clockwise, doubleTurn);
        }
    } };

    public Manipulator3x3(Cube3x3 cube) {
        this.cube = cube;
    }

    public String cubeToString() {
        return this.cube.toString();
    }

    /** For testing purposes only. For manipulation use this.manipulations */
    protected Cube3x3 getCube() {
        return this.cube;
    }

    public List<String> getPreviousSteps() {
        return previousSteps;
    }

    public IManipulation[] getManipulations() {
        return manipulations;
    }

    private void addStep(String stepCode, Boolean clockwise, Boolean doubleTurn) {
        if (doubleTurn) {
            stepCode = "2" + stepCode;
        }
        if (!clockwise) {
            stepCode = stepCode + "'";
        }
        this.previousSteps.add(stepCode);
    }

    public void reset() {
        this.cube = new Cube3x3();
        this.previousSteps.clear();
    }

    /**
     * Only the cube gets a deep copy, the other attributes are shallow copies.
     */
    @Override
    public Manipulator3x3 clone() {
        Manipulator3x3 clone = new Manipulator3x3(this.cube.clone());
        clone.previousSteps.addAll(this.previousSteps);
        return clone;
    }

    public Boolean isSolved() {
        return this.cube.areAllSidesOneColor();
    }

    public Boolean cubeEquals(Manipulator3x3 rhs) {
        return this.cube.equals(rhs.cube);
    }

    public int cubeScore() {
        return this.cube.getScore();
    }
}
