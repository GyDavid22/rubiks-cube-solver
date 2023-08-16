package main;

import helpers.IManipulation;
import structures.Cube3x3;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Cube3x3 c = new Cube3x3();
        System.out.println(c.toString());
        System.out.println(c.areAllSidesOneColor());
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
        for (int i = 0; i < 4; i++) {
            manipulations[0].execute(true, false);
            System.out.println(c.toString());
        }
        System.out.println("-----------------------");
        for (int i = 0; i < 4; i++) {
            manipulations[1].execute(true, false);
            System.out.println(c.toString());
        }
    }
}
