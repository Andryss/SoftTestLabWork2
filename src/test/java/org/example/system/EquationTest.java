package org.example.system;

import org.example.log.LnCalc;
import org.example.log.LogCalc;
import org.example.trig.SecCalc;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class EquationTest {

    private static final double PRECISION = 1e-6;

    private SecCalc secMock;
    private LogCalc logMock;
    private LnCalc lnMock;

    private final double[][] secValues = {
            { -6.2831853,  1.000000 },
            { -6.0000000,  1.041482 },
            { -5.5000000,  1.411094 },
            { -5.0000000,  3.525320 },
            { -4.5000000, -4.743928 },
            { -4.0000000, -1.529886 },
            { -3.5000000, -1.067855 },
            { -3.1415926, -1.000000 },
            { -3.0000000, -1.010109 },
            { -2.5000000, -1.248216 },
            { -2.0000000, -2.402998 },
            { -1.5000000, 14.136833 },
            { -1.0000000,  1.850816 },
            { -0.5000000,  1.139494 },
            { -0.0000000,  1.000000 },
    };

    private final double[][] log3Values = {
            { 0.3500000, -0.955589278 },
            { 0.4960922, -0.638071765 },
            { 0.5000000, -0.630929754 },
            { 0.6127040, -0.445901921 },
            { 0.7500000, -0.261859507 },
            { 1.0000000,  0.000000000 },
            { 1.2500000,  0.203114014 },
            { 1.5000000,  0.369070246 },
            { 1.7500000,  0.509384242 },
            { 2.0000000,  0.630929754 },
    };

    private final double[][] lgValues = {
            { 0.3500000, -0.455931955 },
            { 0.4960922, -0.304437601 },
            { 0.5000000, -0.301029996 },
            { 0.6127040, -0.212749284 },
            { 0.7500000, -0.124938737 },
            { 1.0000000,  0.000000000 },
            { 1.2500000,  0.096910013 },
            { 1.5000000,  0.176091259 },
            { 1.7500000,  0.243038049 },
            { 2.0000000,  0.301029996 },
    };

    private final double[][] lnValues = {
            { 0.3500000, -1.049822124 },
            { 0.4960922, -0.700993482 },
            { 0.5000000, -0.693147181 },
            { 0.6127040, -0.489873330 },
            { 0.7500000, -0.287682072 },
            { 1.0000000,  0.000000000 },
            { 1.2500000,  0.223143551 },
            { 1.5000000,  0.405465108 },
            { 1.7500000,  0.559615788 },
            { 2.0000000,  0.693147181 },
    };

    @Before
    public void setUpMocks() {
        secMock = mock(SecCalc.class);
        for (double[] values : secValues) {
            when(secMock.sec(values[0], PRECISION)).thenReturn(values[1]);
        }

        logMock = mock(LogCalc.class);
        for (double[] values : log3Values) {
            when(logMock.log3(values[0], PRECISION * 1e-3)).thenReturn(values[1]);
        }
        for (double[] values : lgValues) {
            when(logMock.lg(values[0], PRECISION * 1e-3)).thenReturn(values[1]);
        }

        lnMock = mock(LnCalc.class);
        for (double[] values : lnValues) {
            when(lnMock.ln(values[0], PRECISION * 1e-3)).thenReturn(values[1]);
        }
    }

    private final double[][] negativeArguments = {
            { -6.2831853,  1.000000 },
            { -6.0000000,  1.041482 },
            { -5.5000000,  1.411094 },
            { -5.0000000,  3.525320 },
            { -4.5000000, -4.743928 },
            { -4.0000000, -1.529886 },
            { -3.5000000, -1.067855 },
            { -3.1415926, -1.000000 },
            { -3.0000000, -1.010109 },
            { -2.5000000, -1.248216 },
            { -2.0000000, -2.402998 },
            { -1.5000000, 14.136833 },
            { -1.0000000,  1.850816 },
            { -0.5000000,  1.139494 },
            { -0.0000000,  1.000000 },
    };

    @Test
    public void passNegativeX_calcEquation_success() {
        Equation equation = new Equation(secMock, logMock, lnMock);

        for (double[] arguments : negativeArguments) {
            double x = arguments[0], expected = arguments[1];

            double real = equation.calc(x, PRECISION);

            assertEquals(expected, real, PRECISION);
            verify(secMock).sec(x, PRECISION);
            verifyNoInteractions(logMock);
            verifyNoInteractions(lnMock);
        }
    }

    private final double[][] positiveArguments = {
            { 0.3500000, 47.049050 },
            { 0.4960922,  0.000000 },
            { 0.5000000,  0.001439 },
            { 0.6127040,  0.166650 },
            { 0.7500000,  0.080845 },
            { 1.0000000,  0.000000 },
            { 1.2500000,  0.050119 },
            { 1.5000000,  0.186379 },
            { 1.7500000,  0.549187 },
            { 2.0000000,  1.818077 },
    };

    @Test
    public void passPositiveX_calcEquation_success() {
        Equation equation = new Equation(secMock, logMock, lnMock);

        for (double[] argument : positiveArguments) {
            double x = argument[0], expected = argument[1];

            double real = equation.calc(x, PRECISION);

            assertEquals(expected, real, PRECISION);
            verify(logMock).log3(x, PRECISION * 1e-3);
            verify(logMock).lg(x, PRECISION * 1e-3);
            verify(lnMock).ln(x, PRECISION * 1e-3);
            verifyNoInteractions(secMock);
        }
    }

}