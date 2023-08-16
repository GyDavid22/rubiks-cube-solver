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
            }
        };
        manipulations[0].execute(true, false);
        System.out.println(c.toString());
    }
}
