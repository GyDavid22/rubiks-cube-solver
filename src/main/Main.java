package main;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        try {
            new Solver3x3("testfile.txt").solve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
