package main;

import structures.Cube3x3;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        try {
            Cube3x3 c = new Cube3x3("testfile.txt");
            System.out.println(c.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
