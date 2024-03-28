package ru.andryss.log;

import java.math.BigDecimal;

import static java.math.BigDecimal.*;
import static java.math.MathContext.DECIMAL128;

public class LnCalc {

    public double ln(double x, double precision) {
        if (x <= 0) {
            throw new IllegalArgumentException("X must be more than zero");
        }
        if (precision <= 0) {
            throw new IllegalArgumentException("Precision must be more than zero");
        }
        if (x < 2) {
            return ln0(x, precision);
        } else if (x == 2) {
            return 0.6931471805599453;
        } else {
            return ln1(x, precision);
        }
    }

    private double ln0(double x, double precision) {
        BigDecimal res = ZERO, part, prevPart = res;
        for (int n = 1; n <= 1000; n++) {
            part = valueOf(x - 1).pow(n).divide(valueOf(n), DECIMAL128);
            res = res.add(n % 2 == 0 ? part.negate() : part);
            if (part.abs().doubleValue() < precision && part.subtract(prevPart).abs().doubleValue() < precision) {
                return res.doubleValue();
            }
            prevPart = part;
        }
        throw new ArithmeticException("Precision can't be reached");
    }

    private double ln1(double x, double precision) {
        BigDecimal res = valueOf(ln(x - 1, precision)), part, prevPart = res;
        for (int n = 1; n <= 1000; n++) {
            part = ONE.divide(valueOf(x - 1).pow(n), DECIMAL128).divide(valueOf(n), DECIMAL128);
            res = res.add(n % 2 == 0 ? part.negate() : part);
            if (part.abs().doubleValue() < precision && part.subtract(prevPart).abs().doubleValue() < precision) {
                return res.doubleValue();
            }
            prevPart = part;
        }
        throw new ArithmeticException("Precision can't be reached");
    }

}
