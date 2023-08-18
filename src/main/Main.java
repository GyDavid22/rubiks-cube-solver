package main;

import helpers.IManipulation;
import structures.Cube3x3;

public class Main {
    public static void main(String[] args) {
        Cube3x3 c = new Cube3x3();
        IManipulation[] manipulations = {
                new IManipulation() {
                    @Override
                    public void execute(Boolean clockwise, Boolean doubleTurn) {
                        c.Up(clockwise, doubleTurn);
                    }
                },
                new IManipulation() {
                    @Override
                    public void execute(Boolean clockwise, Boolean doubleTurn) {
                        c.Down(clockwise, doubleTurn);
                    }
                },
                new IManipulation() {
                    @Override
                    public void execute(Boolean clockwise, Boolean doubleTurn) {
                        c.Left(clockwise, doubleTurn);
                    }
                },
                new IManipulation() {
                    @Override
                    public void execute(Boolean clockwise, Boolean doubleTurn) {
                        c.Right(clockwise, doubleTurn);
                    }
                },
                new IManipulation() {
                    @Override
                    public void execute(Boolean clockwise, Boolean doubleTurn) {
                        c.Front(clockwise, doubleTurn);
                    }
                },
                new IManipulation() {
                    @Override
                    public void execute(Boolean clockwise, Boolean doubleTurn) {
                        c.Back(clockwise, doubleTurn);
                    }
                }
        };
        manipulations[0].execute(true, true);
        manipulations[1].execute(true, true);
        manipulations[2].execute(true, true);
        manipulations[3].execute(true, true);
        System.out.println(c.toString());
    }
}
