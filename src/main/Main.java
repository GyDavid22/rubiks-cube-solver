package main;

import structures.Cube3x3;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Cube3x3 c = new Cube3x3();
        System.out.println(c.toString());
        System.out.println(c.areAllSidesOneColor());
    }
}
