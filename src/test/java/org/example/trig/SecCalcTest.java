package org.example.trig;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SecCalcTest {

    private static final double PRECISION = 1e-6;

    private CosCalc cosMock;

    private final double[][] cosValues = {
            { -3.1415926, -1.0000000 },
            { -3.0000000, -0.9899925 },
            { -2.5000000, -0.8011436 },
            { -2.0000000, -0.4161468 },
            { -1.570796326,  0.0000000 },
            { -1.5000000,  0.0707372 },
            { -1.0000000,  0.5403023 },
            { -0.5000000,  0.8775826 },
            {  0.0000000,  1.0000000 },
            {  0.5000000,  0.8775826 },
            {  1.0000000,  0.5403023 },
            {  1.5000000,  0.0707372 },
            {  1.570796326,  0.0000000 },
            {  2.0000000, -0.4161468 },
            {  2.5000000, -0.8011436 },
            {  3.0000000, -0.9899925 },
            {  3.1415926, -1.0000000 },
            { 10.0000000, -0.8390715 },
            { 20.0000000,  0.4080821 },
            { 50.0000000,  0.9649660 },
            { 99.9999999,  0.8623188 },
    };

    @Before
    public void setUpMocks() {
        cosMock = mock(CosCalc.class);
        for (double[] value : cosValues) {
            when(cosMock.cos(value[0], PRECISION * 1e-1)).thenReturn(value[1]);
        }
    }

    private final double[][] validArguments = {
            { -3.1415926, -1.000000 },
            { -3.0000000, -1.010109 },
            { -2.5000000, -1.248216 },
            { -2.0000000, -2.402998 },
            { -1.5000000, 14.136833 },
            { -1.0000000,  1.850816 },
            { -0.5000000,  1.139494 },
            {  0.0000000,  1.000000 },
            {  0.5000000,  1.139494 },
            {  1.0000000,  1.850816 },
            {  1.5000000, 14.136833 },
            {  2.0000000, -2.402998 },
            {  2.5000000, -1.248216 },
            {  3.0000000, -1.010109 },
            {  3.1415926, -1.000000 },
            { 10.0000000, -1.191793 },
            { 20.0000000,  2.450488 },
            { 50.0000000,  1.036306 },
            { 99.9999999,  1.159664 },
    };

    @Test
    public void passX_calcCos_success() {
        SecCalc secCalc = new SecCalc(cosMock);

        for (double[] arguments : validArguments) {
            double x = arguments[0], expected = arguments[1];

            double real = secCalc.sec(x, PRECISION);

            assertEquals(expected, real, PRECISION);
            verify(cosMock).cos(x, PRECISION * 1e-1);
        }
    }

    private final double[] specialArguments = {
            -1.570796326,
             1.570796326,
    };

    @Test
    public void passSpecialX_calcCos_returnInfinity() {
        SecCalc secCalc = new SecCalc(cosMock);

        for (double x : specialArguments) {

            double real = secCalc.sec(x, PRECISION);

            assertTrue(Double.isInfinite(real));
        }
    }

    private final double[][] invalidArguments = {
            { 0.0000000,  0.000000 },
            { 0.0000000, -0.000001 },
            { 0.0000000, -1.000000 },
    };

    @Test
    public void passInvalidParameters_throwException() {
        SecCalc secCalc = new SecCalc(cosMock);

        for (double[] arguments : invalidArguments) {
            double x = arguments[0], precision = arguments[1];

            assertThrows(IllegalArgumentException.class, () -> secCalc.sec(x, precision));
            verifyNoInteractions(cosMock);
        }
    }

    @Test
    public void passX_calcCosNonMock_success() {
        SecCalc secCalc = new SecCalc(new CosCalc(new SinCalc()));

        for (double[] arguments : validArguments) {
            double x = arguments[0], expected = arguments[1];

            double real = secCalc.sec(x, PRECISION);

            assertEquals(expected, real, PRECISION);
        }
    }

    @Test
    public void passSpecialX_calcCosNonMock_returnInfinity() {
        SecCalc secCalc = new SecCalc(new CosCalc(new SinCalc()));

        for (double x : specialArguments) {

            double real = secCalc.sec(x, PRECISION);

            assertTrue(Double.isInfinite(real));
        }
    }

}