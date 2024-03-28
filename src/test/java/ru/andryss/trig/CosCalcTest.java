package ru.andryss.trig;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class CosCalcTest {

    private static final double PRECISION = 1e-6;

    private SinCalc sinMock;

    private final double[][] sinValues = {
            { -3.1415926,  0.0000000 },
            { -2.6179938, -0.5000000 },
            { -2.0943951, -0.8660254 },
            { -1.570796326, -1.0000000 },
            { -1.0471975, -0.8660253 },
            { -0.5235987, -0.5000000 },
            {  0.0000000,  0.0000000 },
            {  0.5235987,  0.5000000 },
            {  1.0471975,  0.8660253 },
            {  1.570796326,  1.0000000 },
            {  2.0943951,  0.8660254 },
            {  2.6179938,  0.5000000 },
            {  3.1415926,  0.0000000 },
            {  5.0000000, -0.9589242 },
            { 10.0000000, -0.5440211 },
            { 20.0000000,  0.9129452 },
            { 50.0000000, -0.2623748 },
            { 99.9999999, -0.5063657 },
    };

    @Before
    public void setUpMocks() {
        sinMock = mock(SinCalc.class);
        for (double[] value : sinValues) {
            when(sinMock.sin(value[0], PRECISION * PRECISION)).thenReturn(value[1]);
        }
    }

    private final double[][] validArguments = {
            { -3.1415926, -1.000000 },
            { -2.6179938, -0.866025 },
            { -2.0943951, -0.500000 },
            { -1.570796326,  0.000000 },
            { -1.0471975,  0.500000 },
            { -0.5235987,  0.866025 },
            {  0.0000000,  1.000000 },
            {  0.5235987,  0.866025 },
            {  1.0471975,  0.500000 },
            {  1.570796326,  0.000000 },
            {  2.0943951, -0.500000 },
            {  2.6179938, -0.866025 },
            {  3.1415926, -1.000000 },
            {  5.0000000,  0.283662 },
            { 10.0000000, -0.839072 },
            { 20.0000000,  0.408082 },
            { 50.0000000,  0.964966 },
            { 99.9999999,  0.862318 },
    };

    @Test
    public void passX_calcCos_success() {
        CosCalc cosCalc = new CosCalc(sinMock);

        for (double[] arguments : validArguments) {
            double x = arguments[0], expected = arguments[1];

            double real = cosCalc.cos(x, PRECISION);

            assertEquals(expected, real, PRECISION);
            verify(sinMock).sin(x, PRECISION * PRECISION);
        }
    }

    private final double[][] invalidArguments = {
            { 0.0000000,  0.000000 },
            { 0.0000000, -0.000001 },
            { 0.0000000, -1.000000 },
    };

    @Test
    public void passInvalidParameters_throwException() {
        CosCalc cosCalc = new CosCalc(sinMock);

        for (double[] arguments : invalidArguments) {
            double x = arguments[0], precision = arguments[1];

            assertThrows(IllegalArgumentException.class, () -> cosCalc.cos(x, precision));
            verifyNoInteractions(sinMock);
        }
    }

    @Test
    public void passX_calcCosNonMock_success() {
        CosCalc cosCalc = new CosCalc(new SinCalc());

        for (double[] arguments : validArguments) {
            double x = arguments[0], expected = arguments[1];

            double real = cosCalc.cos(x, PRECISION);

            assertEquals(expected, real, PRECISION);
        }
    }

}