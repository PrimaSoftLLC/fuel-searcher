package by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.context.SearchersParsingContext;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public final class SubTableTitleTemplateTagHandlerTest {
    private final SubTableTitleTemplateTagHandler tagHandler = new SubTableTitleTemplateTagHandler(null);

    @Test
    public void startTagShouldBeHandled() {
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);

        this.tagHandler.handleStartTag(givenContext);
    }

    @Test
    public void aliasesShouldBeFound() {
        final SearchersParsingContext givenContext = createContext(
                "{комбайн} }Соотношение{ массы зерна к массе соломы {соотношение массы зерна к массе соломы}"
        );

        final Stream<String> actual = this.tagHandler.findAliases(givenContext);
        final List<String> actualAsList = actual.toList();
        final List<String> expectedAsList = List.of("комбайн", "соотношение массы зерна к массе соломы");
        assertEquals(expectedAsList, actualAsList);
    }

    @Test
    public void translatedValueShouldBeAccumulated() {
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);
        final SpecificationPropertyExtractor givenExtractor = mock(SpecificationPropertyExtractor.class);

        this.tagHandler.accumulateTranslatedValue(givenContext, givenExtractor);

        verify(givenContext, times(1)).accumulateSubTableTitleTemplateArgumentExtractor(
                same(givenExtractor)
        );
    }

    @Test
    public void additionalValuesShouldBeAccumulated() {
        final SearchersParsingContext givenContext = createContext(
                "{комбайн} }Соотношение{ массы зерна к массе соломы {соотношение массы зерна к массе соломы}"
        );

        this.tagHandler.accumulateAdditionalValues(givenContext);

        final String expectedSubTableTitleTemplate = "%s }Соотношение{ массы зерна к массе соломы %s";
        verify(givenContext, times(1)).accumulateSubTableTitleTemplate(
                expectedSubTableTitleTemplate
        );
    }

    @SuppressWarnings("SameParameterValue")
    private static SearchersParsingContext createContext(final String lastContent) {
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);
        when(givenContext.getLastContent()).thenReturn(lastContent);
        return givenContext;
    }
}
