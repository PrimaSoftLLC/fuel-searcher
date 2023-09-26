package by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelsearcher.util.SubTableTitleUtil;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.SubTableTitleUtil.findPropertyNames;
import static org.junit.Assert.assertSame;
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
        try (final MockedStatic<SubTableTitleUtil> mockedTitleUtil = mockStatic(SubTableTitleUtil.class)) {
            final String givenSubTableTitleTemplateWithPropertyNames = "{механизм} с {трактор}";
            final SearchersParsingContext givenContext = createContext(givenSubTableTitleTemplateWithPropertyNames);

            final Stream<String> givenPropertyNames = Stream.of("механизм", "трактор");
            mockedTitleUtil.when(
                    () -> findPropertyNames(same(givenSubTableTitleTemplateWithPropertyNames))
            ).thenReturn(givenPropertyNames);

            final Stream<String> actual = this.tagHandler.findAliases(givenContext);
            assertSame(givenPropertyNames, actual);
        }
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
        final String givenSubTableTitleTemplateWithPropertyNames = "{механизм} с {трактор}";
        final SearchersParsingContext givenContext = createContext(givenSubTableTitleTemplateWithPropertyNames);

        this.tagHandler.accumulateAdditionalValues(givenContext);

        verify(givenContext, times(1)).accumulateSubTableTitleTemplate(
                same(givenSubTableTitleTemplateWithPropertyNames)
        );
    }

    @SuppressWarnings("SameParameterValue")
    private static SearchersParsingContext createContext(final String lastContent) {
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);
        when(givenContext.getLastContent()).thenReturn(lastContent);
        return givenContext;
    }
}
