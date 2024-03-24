package org.example.trig;

import java.math.BigDecimal;

import static java.math.BigDecimal.*;
import static java.math.MathContext.DECIMAL128;
import static org.example.util.TrigUtil.ranged;

public class SinCalc {

    public double sin(double x, double precision) {
        x = ranged(x);
        if (precision <= 0) {
            throw new IllegalArgumentException("Precision must be more than zero");
        }
        BigDecimal res = ZERO;
        for (int n = 1; n <= 1000; n++) {
            int k = (n << 1) - 1;
            BigDecimal part = valueOf(x).pow(k).divide(factorial(k), DECIMAL128);
            res = res.add(n % 2 == 0 ? part.negate() : part);
            if (part.abs().doubleValue() < precision) {
                return res.doubleValue();
            }
        }
        throw new ArithmeticException("Precision can't be reached");
    }

    private BigDecimal factorial(int n) {
        BigDecimal res = ONE;
        for (int i = 2; i <= n; i++) {
            res = res.multiply(valueOf(i));
        }
        return res;
    }

}
