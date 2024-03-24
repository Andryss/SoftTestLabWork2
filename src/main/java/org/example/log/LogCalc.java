package org.example.log;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogCalc {

    private final LnCalc lnCalc;

    public double log3(double x, double precision) {
        return log(x, 3, precision);
    }

    public double lg(double x, double precision) {
        return log(x, 10, precision);
    }

    private double log(double x, int base, double precision) {
        if (x <= 0) {
            throw new IllegalArgumentException("X must be more than zero");
        }
        if (precision <= 0) {
            throw new IllegalArgumentException("Precision must be more than zero");
        }
        double lnX = lnCalc.ln(x, precision * 1e-1);
        double lnBase = lnCalc.ln(base, precision * 1e-1);
        return lnX / lnBase;
    }
}
