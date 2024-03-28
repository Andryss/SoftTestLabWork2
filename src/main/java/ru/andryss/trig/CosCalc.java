package ru.andryss.trig;

import lombok.RequiredArgsConstructor;
import ru.andryss.util.TrigUtil;

import static java.lang.Math.sqrt;

@RequiredArgsConstructor
public class CosCalc {

    private final SinCalc sinCalc;

    public double cos(double x, double precision) {
        if (precision <= 0) {
            throw new IllegalArgumentException("Precision must be more than zero");
        }
        double sin = sinCalc.sin(x, precision * precision);
        double cos = sqrt(Math.max(1 - sin * sin, 0));
        x = TrigUtil.ranged(x);
        return (x > -Math.PI/2 && x < Math.PI/2) ? cos : -cos;
    }

}
