package helpers;

import java.util.ArrayList;
import java.util.List;

import structures.Cube3x3;

public abstract class Manipulator3x3 {
    private final Cube3x3 cube = new Cube3x3();
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

    public Cube3x3 getCube() {
        return cube;
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
}
