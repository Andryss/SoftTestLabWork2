package org.example.log;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class LogCalcTest {

    private static final double PRECISION = 1e-6;

    private LnCalc lnMock;

    private final double[][] lnValues = {
            {  0.1000000, -2.3025851 },
            {  0.2000000, -1.6094379 },
            {  0.3000000, -1.2039728 },
            {  0.4000000, -0.9162907 },
            {  0.5000000, -0.6931472 },
            {  0.6000000, -0.5108256 },
            {  0.7000000, -0.3566749 },
            {  0.8000000, -0.2231436 },
            {  0.9000000, -0.1053605 },
            {  1.0000000,  0.0000000 },
            {  1.1000000,  0.0953102 },
            {  1.2000000,  0.1823216 },
            {  1.3000000,  0.2623643 },
            {  1.4000000,  0.3364722 },
            {  1.5000000,  0.4054651 },
            {  3.0000000,  1.0986122 },
            {  9.0000000,  2.1972246 },
            { 10.0000000,  2.3025851 },
            { 20.0000000,  2.9957322 },
            { 50.0000000,  3.9120230 },
            { 99.9999999,  4.6051702 },
            { 10000000.0, 16.1180956 },
    };

    @Before
    public void setUpMocks() {
        lnMock = mock(LnCalc.class);
        for (double[] value : lnValues) {
            when(lnMock.ln(value[0], PRECISION * 1e-1)).thenReturn(value[1]);
        }
    }

    private final double[][] validLog3Arguments = {
            {  0.1000000, -2.095903 },
            {  0.2000000, -1.464974 },
            {  0.3000000, -1.095903 },
            {  0.4000000, -0.834044 },
            {  0.5000000, -0.630930 },
            {  0.6000000, -0.464974 },
            {  0.7000000, -0.324660 },
            {  0.8000000, -0.203114 },
            {  0.9000000, -0.095903 },
            {  1.0000000,  0.000000 },
            {  1.1000000,  0.086755 },
            {  1.2000000,  0.165956 },
            {  1.3000000,  0.238814 },
            {  1.4000000,  0.306270 },
            {  1.5000000,  0.369070 },
//            {  3.0000000,  1.000000 },  // verify should be replaced
            {  9.0000000,  2.000000 },
            { 10.0000000,  2.095903 },
            { 20.0000000,  2.726833 },
            { 50.0000000,  3.560877 },
//            { 10000000.0, 14.671323 },
    };

    @Test
    public void passX_calcLog3_success() {
        LogCalc logCalc = new LogCalc(lnMock);

        for (double[] arguments : validLog3Arguments) {
            double x = arguments[0], expected = arguments[1];

            double real = logCalc.log3(x, PRECISION);

            assertEquals(expected, real, PRECISION);
            verify(lnMock).ln(x, PRECISION * 1e-1);
            // verify(lnMock).ln(3, PRECISION * 1e-1);
        }
    }

    private final double[][] validLgArguments = {
            {  0.1000000, -1.000000 },
            {  0.2000000, -0.698970 },
            {  0.3000000, -0.522879 },
            {  0.4000000, -0.397940 },
            {  0.5000000, -0.301030 },
            {  0.6000000, -0.221849 },
            {  0.7000000, -0.154902 },
            {  0.8000000, -0.096910 },
            {  0.9000000, -0.045757 },
            {  1.0000000,  0.000000 },
            {  1.1000000,  0.041393 },
            {  1.2000000,  0.079181 },
            {  1.3000000,  0.113943 },
            {  1.4000000,  0.146128 },
            {  1.5000000,  0.176091 },
//            { 10.0000000,  1.000000 },  // verify should be replaced
            { 20.0000000,  1.301030 },
            { 50.0000000,  1.698970 },
            { 10000000.0,  7.000000 },
    };

    @Test
    public void passX_calcLg_success() {
        LogCalc logCalc = new LogCalc(lnMock);

        for (double[] arguments : validLgArguments) {
            double x = arguments[0], expected = arguments[1];

            double real = logCalc.lg(x, PRECISION);

            assertEquals(expected, real, PRECISION);
            verify(lnMock).ln(x, PRECISION * 1e-1);
            // verify(lnMock).ln(10, PRECISION * 1e-1);
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
    public void passInvalidParameters_log3_throwException() {
        LogCalc logCalc = new LogCalc(lnMock);

        for (double[] arguments : invalidArguments) {
            double x = arguments[0], precision = arguments[1];

            assertThrows(IllegalArgumentException.class, () -> logCalc.log3(x, precision));
        }
    }

    @Test
    public void passInvalidParameters_lg_throwException() {
        LogCalc logCalc = new LogCalc(lnMock);

        for (double[] arguments : invalidArguments) {
            double x = arguments[0], precision = arguments[1];

            assertThrows(IllegalArgumentException.class, () -> logCalc.lg(x, precision));
        }
    }

}