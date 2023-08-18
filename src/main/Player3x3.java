package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import helpers.IManipulation;
import structures.Cube3x3;

public class Player3x3 {
    private Cube3x3 c = new Cube3x3();
    private List<String> previousSteps = new ArrayList<>();
    private IManipulation[] manipulations = { new IManipulation() {
        @Override
        public void execute(Boolean clockwise, Boolean doubleTurn) {
            c.Up(clockwise, doubleTurn);
            addStep("U", clockwise, doubleTurn);
        }
    }, new IManipulation() {
        @Override
        public void execute(Boolean clockwise, Boolean doubleTurn) {
            c.Down(clockwise, doubleTurn);
            addStep("D", clockwise, doubleTurn);
        }
    }, new IManipulation() {
        @Override
        public void execute(Boolean clockwise, Boolean doubleTurn) {
            c.Left(clockwise, doubleTurn);
            addStep("L", clockwise, doubleTurn);
        }
    }, new IManipulation() {
        @Override
        public void execute(Boolean clockwise, Boolean doubleTurn) {
            c.Right(clockwise, doubleTurn);
            addStep("R", clockwise, doubleTurn);
        }
    }, new IManipulation() {
        @Override
        public void execute(Boolean clockwise, Boolean doubleTurn) {
            c.Front(clockwise, doubleTurn);
            addStep("F", clockwise, doubleTurn);
        }
    }, new IManipulation() {
        @Override
        public void execute(Boolean clockwise, Boolean doubleTurn) {
            c.Back(clockwise, doubleTurn);
            addStep("B", clockwise, doubleTurn);
        }
    } };

    public void run() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        Boolean doubleTurn, clockwise;
        String line;
        Boolean run = true;
        while (run) {
            System.out.println(this.c.toString());
            System.out.println(String.join(", ", this.previousSteps));
            try {
                System.out.print("Next step should be: ");
                line = br.readLine().toUpperCase();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            if (line.contains("2")) {
                doubleTurn = true;
            } else {
                doubleTurn = false;
            }
            if (line.contains("'")) {
                clockwise = false;
            } else {
                clockwise = true;
            }
            if (line.contains("U")) {
                manipulations[0].execute(clockwise, doubleTurn);
            } else if (line.contains("D")) {
                manipulations[1].execute(clockwise, doubleTurn);
            } else if (line.contains("L")) {
                manipulations[2].execute(clockwise, doubleTurn);
            } else if (line.contains("R")) {
                manipulations[3].execute(clockwise, doubleTurn);
            } else if (line.contains("F")) {
                manipulations[4].execute(clockwise, doubleTurn);
            } else if (line.contains("B")) {
                manipulations[5].execute(clockwise, doubleTurn);
            } else if (line.contains("Q")) {
                run = false;
            }
        }
        try {
            br.close();
            isr.close();
        } catch (IOException e) {
        }
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
