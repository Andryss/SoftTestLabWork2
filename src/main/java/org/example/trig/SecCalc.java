package org.example.trig;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SecCalc {

    private final CosCalc cosCalc;

    public double sec(double x, double precision) {
        if (precision <= 0) {
            throw new IllegalArgumentException("Precision must be more than zero");
        }
        double cos = cosCalc.cos(x, precision * 1e-1);
        return 1 / cos;
    }
}
