package by.aurorasoft.fuelinfosearcher.service.contentcorrector.component;

import org.junit.Test;

import java.util.regex.MatchResult;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//TODO: do parametrized test
public final class RealNumberParagraphCorrectorTest {
    private static final int GROUP_NUMBER_INTEGER_PART = 1;
    private static final int GROUP_NUMBER_FRACTIONAL_PART = 2;

    private final RealNumberParagraphCorrector corrector = new RealNumberParagraphCorrector();

    @Test
    public void replacementShouldBeCreatedWithReducingFractionalPart() {
        final String givenIntegerPart = "10";
        final String givenFractionalPart = "110001300000";
        final MatchResult givenMatchResult = createMatchResult(givenIntegerPart, givenFractionalPart);

        final String actual = this.corrector.createReplacement(givenMatchResult);
        final String expected = "10.1100013";
        assertEquals(expected, actual);
    }

    @Test
    public void replacementShouldBeCreatedWithReducingToNumberWithoutFractionalPart() {
        final String givenIntegerPart = "20";
        final String givenFractionalPart = "0000000000000000";
        final MatchResult givenMatchResult = createMatchResult(givenIntegerPart, givenFractionalPart);

        final String actual = this.corrector.createReplacement(givenMatchResult);
        final String expected = "20";
        assertEquals(expected, actual);
    }

    @Test
    public void replacementShouldBeCreatedWithoutReducing() {
        final String givenIntegerPart = "10";
        final String givenFractionalPart = "110001300001";
        final MatchResult givenMatchResult = createMatchResult(givenIntegerPart, givenFractionalPart);

        final String actual = this.corrector.createReplacement(givenMatchResult);
        final String expected = "10.110001300001";
        assertEquals(expected, actual);
    }

    private static MatchResult createMatchResult(final String integerPart, final String fractionalPart) {
        final MatchResult givenMatchResult = mock(MatchResult.class);
        when(givenMatchResult.group(eq(GROUP_NUMBER_INTEGER_PART))).thenReturn(integerPart);
        when(givenMatchResult.group(eq(GROUP_NUMBER_FRACTIONAL_PART))).thenReturn(fractionalPart);
        return givenMatchResult;
    }
}
