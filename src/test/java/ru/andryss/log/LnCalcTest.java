package ru.andryss.log;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class LnCalcTest {

    private static final double PRECISION = 1e-6;

    private final double[][] validArguments = {
//            {  0.1000000, -2.302585 },
//            {  0.2000000, -1.609438 },
            {  0.3000000, -1.203973 },
            {  0.4000000, -0.916291 },
            {  0.5000000, -0.693147 },
            {  0.6000000, -0.510826 },
            {  0.7000000, -0.356675 },
            {  0.8000000, -0.223144 },
            {  0.9000000, -0.105361 },
            {  1.0000000,  0.000000 },
            {  1.1000000,  0.095310 },
            {  1.2000000,  0.182322 },
            {  1.3000000,  0.262364 },
            {  1.4000000,  0.336472 },
            {  1.5000000,  0.405465 },
            {  2.0000000,  0.693147 },
            {  2.1000000,  0.741937 },
            {  2.7182818,  1.000000 },
            {  7.3890561,  2.000000 },
//            { 10.0000000,  2.302585 },
//            { 20.0000000,  2.995732 },
//            { 50.0000000,  3.912023 },
//            { 99.9999999,  4.605170 },
//            { 10000000.0, 16.118095 },
    };

    @Test
    public void passX_calcLn_success() {
        LnCalc lnCalc = new LnCalc();

        for (double[] arguments : validArguments) {
            double x = arguments[0], expected = arguments[1];

            double real = lnCalc.ln(x, PRECISION);

            assertEquals(expected, real, PRECISION);
        }
    }

    private final double[][] invalidArguments = {
            {  1.0000000,  0.000000 },
            {  1.0000000, -0.000001 },
            {  1.0000000, -1.000000 },
            {  0.0000000,  0.000001 },
            { -0.0000001,  0.000001 },
            { -1.0000000,  0.000001 },
    };

    @Test
    public void passInvalidParameters_throwException() {
        LnCalc lnCalc = new LnCalc();

        for (double[] arguments : invalidArguments) {
            double x = arguments[0], precision = arguments[1];

            assertThrows(IllegalArgumentException.class, () -> lnCalc.ln(x, precision));
        }
    }
}