package by.aurorasoft.fuelsearcher.model;

import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
import by.aurorasoft.fuelsearcher.util.XWPFTableCellUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;

import static by.aurorasoft.fuelsearcher.util.XWPFTableCellUtil.isNotDefinedDouble;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

public final class FuelTest extends AbstractContextTest {

    @Autowired
    private ObjectMapper objectMapper;

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

    @Test
    public void fuelShouldBeConvertedToJson()
            throws Exception {
        final Fuel givenFuel = new Fuel(5.5, 6.6);

        final String actual = this.objectMapper.writeValueAsString(givenFuel);
        final String expected = "{\"generationNorm\":5.5,\"consumption\":6.6}";
        assertEquals(expected, actual);
    }

    @Test
    public void jsonShouldBeConvertedToFuel()
            throws Exception {
        final String givenJson = "{\"generationNorm\":5.5,\"consumption\":6.6}";

        final Fuel actual = this.objectMapper.readValue(givenJson, Fuel.class);
        final Fuel expected = new Fuel(5.5, 6.6);
        assertEquals(expected, actual);
    }
}
