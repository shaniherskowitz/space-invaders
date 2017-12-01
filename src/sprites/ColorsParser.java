package sprites;

import java.awt.Color;
import java.lang.reflect.Field;

/**
 * finds color based on string.
 */
public class ColorsParser {

    /**
     * creates color parser.
     */
    public ColorsParser() {

    }
// parse color definition and return the specified color.

    /**
     *
     * @param s to convert to color
     * @return the color
     */
    public Color colorFromString(String s) {

        if (s.contains("RGB")) {
            String color = s.substring(10).replace(")", "");
            String[] colors = color.split(",");

            return new Color(Integer.parseInt(colors[0]),
                    Integer.parseInt(colors[1]), Integer.parseInt(colors[2]));

        } else {
            String color = s.substring(6).replace(")", "");
            Color color1;

            try {
                Field field = Class.forName("java.awt.Color").getField(color);
                color1 = (Color) field.get(null);
                return color1;

            } catch (Exception e) {
                color1 = null; // Not defined
            }
        }

        return null;
    }
}
