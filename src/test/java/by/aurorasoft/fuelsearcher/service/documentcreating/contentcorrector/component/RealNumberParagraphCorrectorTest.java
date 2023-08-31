package by.aurorasoft.fuelsearcher.service.documentcreating.contentcorrector.component;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.regex.MatchResult;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class RealNumberParagraphCorrectorTest {
    private static final int GROUP_NUMBER_INTEGER_PART = 1;
    private static final int GROUP_NUMBER_FRACTIONAL_PART = 2;

    private final RealNumberParagraphCorrector corrector = new RealNumberParagraphCorrector();

    @ParameterizedTest
    @MethodSource("matchResultAndExpectedReplacementArgumentProvider")
    public void replacementShouldBeCreated(final MatchResult givenMatchResult, final String expected) {
        final String actual = this.corrector.createReplacement(givenMatchResult);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> matchResultAndExpectedReplacementArgumentProvider() {
        return Stream.of(
                Arguments.of(
                        createMatchResult("10", "110001300000"),
                        "10.1100013"
                ),
                Arguments.of(
                        createMatchResult("20", "0000000000000000"),
                        "20"
                ),
                Arguments.of(
                        createMatchResult("10", "110001300001"),
                        "10.110001300001"
                )
        );
    }

    private static MatchResult createMatchResult(final String integerPart, final String fractionalPart) {
        final MatchResult givenMatchResult = mock(MatchResult.class);
        when(givenMatchResult.group(eq(GROUP_NUMBER_INTEGER_PART))).thenReturn(integerPart);
        when(givenMatchResult.group(eq(GROUP_NUMBER_FRACTIONAL_PART))).thenReturn(fractionalPart);
        return givenMatchResult;
    }
}
