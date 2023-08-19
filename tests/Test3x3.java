import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

import helpers.Manipulator3x3;
import structures.Cube3x3;

public class Test3x3 extends Manipulator3x3 {
    private List<Cube3x3> states = new ArrayList<>();
    private Map<String, Integer> errors = new HashMap<>();

    private final int moves = 500;
    private final int iterations = 1000;

    /**
     * Unit test for randomly scrambling and doing the steps backward.
     */
    @Test
    public void run() {
        for (int i = 0; i < this.iterations; i++) {
            scramble();
            redo();
            this.states.clear();
            this.reset();
        }
        for (String i : errors.keySet()) {
            System.err.println(i + ": " + this.errors.get(i) + " errors");
        }
        assertTrue("See console for errors.", errors.isEmpty());
    }

    private void registerError(String letter) {
        if (!this.errors.containsKey(letter)) {
            this.errors.put(letter, 0);
        } else {
            this.errors.replace(letter, this.errors.get(letter) + 1);
        }
    }

    public void scramble() {
        Random r = new Random();
        for (int i = 0; i < this.moves; i++) {
            this.getManipulations()[r.nextInt(0, 5 + 1)].execute(r.nextBoolean(), r.nextBoolean());
            states.add(this.getCube().clone());
        }
    }

    public void redo() {
        List<String> prevStepsReversed = new ArrayList<>();
        for (int i = this.getPreviousSteps().size() - 1; i >= 0; i--) {
            prevStepsReversed.add(this.getPreviousSteps().get(i));
        }
        InputStreamReader isr = new InputStreamReader(
                new ByteArrayInputStream(String.join("\n", prevStepsReversed).getBytes()));
        BufferedReader br = new BufferedReader(isr);
        Boolean doubleTurn, clockwise;
        String line;
        states.remove(states.size() - 1);
        Boolean error = false;
        Cube3x3 stateToCheck;
        try {
            line = br.readLine();
            while (line != null) {
                if (line.contains("2")) {
                    doubleTurn = true;
                } else {
                    doubleTurn = false;
                }
                if (!line.contains("'")) {
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
                }
                if (!this.states.isEmpty()) {
                    stateToCheck = this.states.remove(this.states.size() - 1);
                    if (!this.getCube().equals(stateToCheck)) {
                        registerError(line);
                        error = true;
                        break;
                    }
                }
                line = br.readLine();
            }
            assertFalse("Redoing went well, but cube still not solved.",
                    !error && !this.getCube().areAllSidesOneColor());
            br.close();
            isr.close();
        } catch (IOException e) {
        }
    }
}
