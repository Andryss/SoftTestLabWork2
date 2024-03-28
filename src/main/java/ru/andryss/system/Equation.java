package ru.andryss.system;

import lombok.RequiredArgsConstructor;
import ru.andryss.log.LnCalc;
import ru.andryss.log.LogCalc;
import ru.andryss.trig.SecCalc;

import static java.lang.Math.pow;

@RequiredArgsConstructor
public class Equation {

    private final SecCalc secCalc;
    private final LogCalc logCalc;
    private final LnCalc lnCalc;

    public double calc(double x, double precision) {
        if (x <= 0) {
            return secCalc.sec(x, precision);
        } else {
            // какая точность нужна, чтобы уложиться в precision ????
            double lowPrecision = precision * 1e-3;
            return pow(pow(pow(logCalc.log3(x, lowPrecision) + logCalc.lg(x, lowPrecision), 2), 3) + lnCalc.ln(x, lowPrecision), 2);
        }
    }

}
