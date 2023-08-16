package structures;

import java.util.HashMap;
import java.util.Map;

import exceptions.ColorNotFoundException;

/** Class to represent the colors of the Rubik's cube. */
public enum Color {
    WHITE("W"),
    YELLOW("Y"),
    GREEN("G"),
    BLUE("B"),
    RED("R"),
    ORANGE("O");

    private final String colorCode;
    private static Boolean initialized = false;
    /** Variable to provide faster mapping, as 6 enum values aren't hard on the memory and we will use it a lot later. */
    private static Map<String, Color> colorMap = new HashMap<>();

    private Color(String colorCode) {
        this.colorCode = colorCode;
    }

    /** @return The single character code of the given color. */
    @Override
    public String toString() {
        return colorCode;
    }

    /**
     * @param colorCode One character code of the color.
     * @return Color enum type equivalent of the parameter.
     * @throws ColorNotFoundException If colorCode has no pair in the Color enum.
     */
    public static Color toEnum(String colorCode) throws ColorNotFoundException {
        if (!Color.initialized) {
            Color.initializeMap();
        }
        Color result = Color.colorMap.get(colorCode);
        if (result == null) {
            throw new ColorNotFoundException();
        }
        return result;
    }

    private static void initializeMap() {
        for (Color i : Color.values()) {
            Color.colorMap.put(i.toString(), i);
        }
    }
}
