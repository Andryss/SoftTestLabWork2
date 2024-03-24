package org.example.system;

import lombok.RequiredArgsConstructor;
import org.example.log.LnCalc;
import org.example.log.LogCalc;
import org.example.trig.SecCalc;

@RequiredArgsConstructor
public class Equation {

    private final SecCalc secCalc;
    private final LogCalc logCalc;
    private final LnCalc lnCalc;

    public double calc(double x, double precision) {
        return 0;
    }
}
