package by.aurorasoft.fuelsearcher.model;

import by.aurorasoft.fuelsearcher.util.XWPFTableCellUtil;
import org.junit.Test;
import org.mockito.MockedStatic;

import static by.aurorasoft.fuelsearcher.util.XWPFTableCellUtil.isNotDefinedDouble;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

public final class FuelTest {

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void fuelWithDefinedGenerationNormAndDefinedConsumptionShouldBeDefined() {
        try (final MockedStatic<XWPFTableCellUtil> mockedUtil = mockStatic(XWPFTableCellUtil.class)) {
            final double givenGenerationNorm = 5.5;
            final double givenConsumption = 6.6;
            final Fuel givenFuel = new Fuel(givenGenerationNorm, givenConsumption);

            mockedUtil.when(() -> isNotDefinedDouble(eq(givenGenerationNorm))).thenReturn(false);
            mockedUtil.when(() -> isNotDefinedDouble(eq(givenConsumption))).thenReturn(false);

            final boolean actual = givenFuel.isDefinedFuel();
            assertTrue(actual);
        }
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void fuelWithNotDefinedGenerationNormAndDefinedConsumptionShouldBeDefined() {
        try (final MockedStatic<XWPFTableCellUtil> mockedUtil = mockStatic(XWPFTableCellUtil.class)) {
            final double givenGenerationNorm = 5.5;
            final double givenConsumption = 6.6;
            final Fuel givenFuel = new Fuel(givenGenerationNorm, givenConsumption);

            mockedUtil.when(() -> isNotDefinedDouble(eq(givenGenerationNorm))).thenReturn(true);
            mockedUtil.when(() -> isNotDefinedDouble(eq(givenConsumption))).thenReturn(false);

            final boolean actual = givenFuel.isDefinedFuel();
            assertTrue(actual);
        }
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void fuelWithDefinedGenerationNormAndNotDefinedConsumptionShouldBeDefined() {
        try (final MockedStatic<XWPFTableCellUtil> mockedUtil = mockStatic(XWPFTableCellUtil.class)) {
            final double givenGenerationNorm = 5.5;
            final double givenConsumption = 6.6;
            final Fuel givenFuel = new Fuel(givenGenerationNorm, givenConsumption);

            mockedUtil.when(() -> isNotDefinedDouble(eq(givenGenerationNorm))).thenReturn(false);
            mockedUtil.when(() -> isNotDefinedDouble(eq(givenConsumption))).thenReturn(true);

            final boolean actual = givenFuel.isDefinedFuel();
            assertTrue(actual);
        }
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void fuelWithNotDefinedGenerationNormAndNotDefinedConsumptionShouldBeNotDefined() {
        try (final MockedStatic<XWPFTableCellUtil> mockedUtil = mockStatic(XWPFTableCellUtil.class)) {
            final double givenGenerationNorm = 5.5;
            final double givenConsumption = 6.6;
            final Fuel givenFuel = new Fuel(givenGenerationNorm, givenConsumption);

            mockedUtil.when(() -> isNotDefinedDouble(eq(givenGenerationNorm))).thenReturn(true);
            mockedUtil.when(() -> isNotDefinedDouble(eq(givenConsumption))).thenReturn(true);

            final boolean actual = givenFuel.isDefinedFuel();
            assertFalse(actual);
        }
    }
}
