package main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import exceptions.InvalidFileException;
import helpers.AStarStructure;
import helpers.Manipulator3x3;
import structures.Cube3x3;

public class Solver3x3 {
    private Manipulator3x3 manipulator;
    private final int maxDepth = 25;
    // private final List<Long> times = new ArrayList<>();

    public Solver3x3(String fileName) throws InvalidFileException {
        this.manipulator = new Manipulator3x3(new Cube3x3(fileName));
    }

    public void solve() {
        System.out.println("Input state:");
        System.out.println(this.manipulator.cubeToString());
        Manipulator3x3 solution = null;// this.searchSolutionBFS();
        // long time, finish;
        // for (int i = 1; i <= this.maxDepth; i++) {
        // System.out.print("Depth: " + i + ". ETA: " + this.calcEtaInS() + "s. ");
        // Stack<Manipulator3x3> stackParam = new Stack<>();
        // stackParam.add(this.manipulator.clone());
        // time = System.currentTimeMillis();
        // solution = this.searchSolutionIterativeDeepeningDFS(0, stackParam, i);
        // finish = System.currentTimeMillis();
        // times.add(finish - time);
        // System.out.println("Finished in " + (((double)(finish - time) / 1000) +
        // "s"));
        // if (solution != null) {
        // break;
        // }
        // }

        solution = this.searchSolutionAStar();

        if (solution != null) {
            System.out.println("The solution:");
            System.out.println(solution.cubeToString());
            System.out.println(String.join(", ", solution.getPreviousSteps()));
        }
    }

    // private Double calcEtaInS() {
    // if (this.times.size() < 2) {
    // return null;
    // }
    // ArrayList<Double> ratios = new ArrayList<>();
    // for (int i = 1; i < this.times.size(); i++) {
    // ratios.add((double)this.times.get(i) / this.times.get(i - 1));
    // }
    // return (this.times.get(this.times.size() - 1) * ratios.stream().mapToDouble(a
    // -> a).average().getAsDouble()) / 1000;
    // }

    // private Manipulator3x3 searchSolutionIterativeDeepeningDFS(int depth,
    // Stack<Manipulator3x3> states, int maxdepth) {
    // if (states.peek().isSolved()) {
    // return states.peek();
    // }
    // if (depth >= maxdepth) {
    // return null;
    // }
    // boolean[] values = { true, false };
    // Manipulator3x3 result = null;
    // Manipulator3x3 currentStateClone = null;
    // Boolean isDuplicate = false;
    // for (boolean doubleTurn : values) {
    // for (boolean clockwise : values) {
    // for (int i = 0; i < 6; i++) {
    // currentStateClone = states.peek().clone();
    // currentStateClone.getManipulations()[i].execute(clockwise, doubleTurn);
    // for (Manipulator3x3 j : states) {
    // if (j.cubeEquals(currentStateClone)) {
    // isDuplicate = true;
    // }
    // }
    // if (isDuplicate) {
    // isDuplicate = false;
    // continue;
    // }
    // states.add(currentStateClone);
    // result = this.searchSolutionIterativeDeepeningDFS(depth + 1, states,
    // maxdepth);
    // if (result != null) {
    // return result;
    // } else {
    // states.pop();
    // }
    // }
    // }
    // }
    // return null;
    // }

    // private Manipulator3x3 searchSolutionBFS() {
    // if (this.manipulator.isSolved()) {
    // return this.manipulator;
    // }
    // Stack<Manipulator3x3> toCheck = new Stack<>();
    // ArrayList<Manipulator3x3> newOnes = new ArrayList<>();
    // toCheck.add(this.manipulator.clone());
    // Manipulator3x3 current, currentClone;
    // boolean[] values = { true, false };
    // for (int i = 0; i < this.maxDepth; i++) {
    // while (!toCheck.isEmpty()) {
    // current = toCheck.pop();
    // for (boolean doubleTurn : values) {
    // for (boolean clockwise : values) {
    // for (int k = 0; k < 6; k++) {
    // currentClone = current.clone();
    // currentClone.getManipulations()[k].execute(clockwise, doubleTurn);
    // if (currentClone.isSolved()) {
    // return currentClone;
    // }
    // newOnes.add(currentClone);
    // }
    // }
    // }
    // }
    // toCheck.addAll(newOnes);
    // newOnes.clear();
    // }
    // return null;
    // }

    private Manipulator3x3 searchSolutionAStar() {
        if (this.manipulator.isSolved()) {
            return this.manipulator;
        }
        ArrayList<AStarStructure> toCheck = new ArrayList<>();
        ArrayList<Manipulator3x3> statuses = new ArrayList<>();
        toCheck.add(new AStarStructure(this.manipulator.clone(), this.manipulator.cubeScore(), 0));
        statuses.add(this.manipulator);
        Manipulator3x3 current, currentClone;
        AStarStructure checkNow = null;
        boolean[] values = { true, false };
        Boolean found = false;
        while (!toCheck.isEmpty()) {
            toCheck.sort(new Comparator<AStarStructure>() {
                @Override
                public int compare(AStarStructure o1, AStarStructure o2) {
                    return Integer.compare(o1.depth + o1.score, o2.depth + o2.score);
                }
            });
            checkNow = toCheck.remove(0);
            current = checkNow.manipulator;
            for (boolean doubleTurn : values) {
                for (boolean clockwise : values) {
                    for (int k = 0; k < 6; k++) {
                        currentClone = current.clone();
                        currentClone.getManipulations()[k].execute(clockwise, doubleTurn);
                        if (currentClone.isSolved()) {
                            return currentClone;
                        }
                        found = false;
                        for (Manipulator3x3 i : statuses) {
                            if (i.cubeEquals(currentClone)) {
                                found = true;
                                break;
                            }
                        }
                        if ((!found) && checkNow.depth + 1 < this.maxDepth) {
                            toCheck.add(new AStarStructure(currentClone, currentClone.cubeScore(), checkNow.depth + 1));
                            statuses.add(currentClone);
                        }
                    }
                }
            }
        }
        return null;
    }
}
