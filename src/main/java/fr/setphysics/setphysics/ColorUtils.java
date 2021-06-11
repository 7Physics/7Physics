package fr.setphysics.setphysics;

import java.awt.*;
import java.util.Random;

public class ColorUtils {
    private static final Random RANDOM = new Random();

    public static Color randomColor() {
        return new Color(RANDOM.nextInt(255), RANDOM.nextInt(255), RANDOM.nextInt(255));
    }

    public static Color[] getTwoDistinctColors() {
        Color color1 = ColorUtils.randomColor();
        Color color2 = ColorUtils.randomColor();
        while (distance(color1, color2) < 0.2) {
            color2 = ColorUtils.randomColor();
        }
        return new Color[]{color1, color2};
    }

    private static double distance(Color a, Color b) {
        return Math.sqrt(
                Math.pow(a.getRed() - b.getRed(), 2) +
                        Math.pow(a.getGreen() - b.getGreen(), 2) +
                        Math.pow(a.getBlue() - b.getBlue(), 2)
        ) / Math.sqrt(
                Math.pow(255, 2) +
                        Math.pow(255, 2) +
                        Math.pow(255, 2)
        );
    }
}
