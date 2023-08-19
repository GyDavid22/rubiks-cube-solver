package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import helpers.Manipulator3x3;

public class Player3x3 extends Manipulator3x3 {
    public void run() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        Boolean doubleTurn, clockwise;
        String line;
        Boolean run = true;
        while (run) {
            System.out.println(this.cubeToString());
            System.out.println(String.join(", ", this.getPreviousSteps()));
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
                this.getManipulations()[0].execute(clockwise, doubleTurn);
            } else if (line.contains("D")) {
                this.getManipulations()[1].execute(clockwise, doubleTurn);
            } else if (line.contains("L")) {
                this.getManipulations()[2].execute(clockwise, doubleTurn);
            } else if (line.contains("R")) {
                this.getManipulations()[3].execute(clockwise, doubleTurn);
            } else if (line.contains("F")) {
                this.getManipulations()[4].execute(clockwise, doubleTurn);
            } else if (line.contains("B")) {
                this.getManipulations()[5].execute(clockwise, doubleTurn);
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
}
