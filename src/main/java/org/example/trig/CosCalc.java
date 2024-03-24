package org.example.trig;

import lombok.RequiredArgsConstructor;

import static java.lang.Math.sqrt;
import static org.example.util.TrigUtil.ranged;

@RequiredArgsConstructor
public class CosCalc {

    private final SinCalc sinCalc;

    public double cos(double x, double precision) {
        if (precision <= 0) {
            throw new IllegalArgumentException("Precision must be more than zero");
        }
        double sin = sinCalc.sin(x, precision * 1e-1);
        double cos = sqrt(1 - sin * sin);
        x = ranged(x);
        return (x > -Math.PI/2 && x < Math.PI/2) ? cos : -cos;
    }

}
