package org.example.trig;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class SinCalcTest {

    private static final double PRECISION = 1e-6;

    private final double[][] validArguments = {
            { -3.1415926,  0.000000 },
            { -2.6179938, -0.500000 },
            { -2.0943951, -0.866025 },
            { -1.5707963, -1.000000 },
            { -1.0471975, -0.866025 },
            { -0.5235987, -0.500000 },
            {  0.0000000,  0.000000 },
            {  0.5235987,  0.500000 },
            {  1.0471975,  0.866025 },
            {  1.5707963,  1.000000 },
            {  2.0943951,  0.866025 },
            {  2.6179938,  0.500000 },
            {  3.1415926,  0.000000 },
            {  5.0000000, -0.958924 },
            { 10.0000000, -0.544021 },
            { 20.0000000,  0.912945 },
            { 50.0000000, -0.262375 },
            { 99.9999999, -0.506365 },
            { 1000000.00, -0.349993 },
            // { 99999999.9,  0.963262 },
    };

    @Test
    public void passX_calcSin_success() {
        SinCalc sinCalc = new SinCalc();

        for (double[] arguments : validArguments) {
            double x = arguments[0], expected = arguments[1];

            double real = sinCalc.sin(x, PRECISION);

            assertEquals(expected, real, PRECISION);
        }
    }

    private final double[][] invalidArguments = {
            { 0.0000000,  0.000000 },
            { 0.0000000, -0.000001 },
            { 0.0000000, -1.000000 },
    };

    @Test
    public void passInvalidParameters_throwException() {
        SinCalc sinCalc = new SinCalc();

        for (double[] arguments : invalidArguments) {
            double x = arguments[0], precision = arguments[1];

            assertThrows(IllegalArgumentException.class, () -> sinCalc.sin(x, precision));
        }
    }

}