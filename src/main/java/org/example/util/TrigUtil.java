package org.example.util;

public class TrigUtil {

    public static double ranged(double x) {
        while (x > Math.PI) x -= 2 * Math.PI;
        while (x < -Math.PI) x += 2 * Math.PI;
        return x;
    }

}
