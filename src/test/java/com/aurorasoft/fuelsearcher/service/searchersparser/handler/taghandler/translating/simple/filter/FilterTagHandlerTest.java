package com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.simple.filter;

import com.aurorasoft.fuelsearcher.service.filter.Filter;
import com.aurorasoft.fuelsearcher.service.factory.filter.FilterFactory;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import lombok.Getter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Test;
import org.xml.sax.Attributes;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.Supplier;

import static java.lang.Integer.MIN_VALUE;
import static java.util.OptionalInt.empty;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public final class FilterTagHandlerTest {
    private static final String FILTRATION_CELL_INDEX_ATTRIBUTE_NAME = "cell-index";

    @Test
    @SuppressWarnings("unchecked")
    public void translatedValueShouldBeAccumulatedWithContextFiltrationCellIndex() {
        final int givenFiltrationCellIndex = 5;
        final SearchersParsingContext givenContext = createContext(givenFiltrationCellIndex);
        final TestFilterTagHandler givenTagHandler = new TestFilterTagHandler(givenContext);

        final FilterFactory<Filter<List<XWPFTableRow>>, ?> givenFilterFactory = mock(FilterFactory.class);
        final Filter<List<XWPFTableRow>> givenFilter = mock(Filter.class);

        when(givenFilterFactory.create(eq(givenFiltrationCellIndex))).thenReturn(givenFilter);

        givenTagHandler.accumulateTranslatedValue(givenContext, givenFilterFactory);

        final Filter<?> actualAccumulatedFilter = givenTagHandler.getAccumulatedFilter();
        assertSame(givenFilter, actualAccumulatedFilter);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void translatedValueShouldBeAccumulatedWithDefaultFiltrationCellIndex() {
        final SearchersParsingContext givenContext = createContext();
        final int givenDefaultFiltrationCellIndex = 5;
        final TestFilterTagHandler givenTagHandler = new TestFilterTagHandler(
                givenDefaultFiltrationCellIndex, givenContext
        );

        final FilterFactory<Filter<List<XWPFTableRow>>, ?> givenFilterFactory = mock(FilterFactory.class);
        final Filter<List<XWPFTableRow>> givenFilter = mock(Filter.class);

        when(givenFilterFactory.create(eq(givenDefaultFiltrationCellIndex))).thenReturn(givenFilter);

        givenTagHandler.accumulateTranslatedValue(givenContext, givenFilterFactory);

        final Filter<?> actualAccumulatedFilter = givenTagHandler.getAccumulatedFilter();
        assertSame(givenFilter, actualAccumulatedFilter);
    }

    @Test(expected = Exception.class)
    @SuppressWarnings("unchecked")
    public void translatedValueShouldNotBeAccumulatedBecauseOfDefaultFiltrationCellIndexNotExist() {
        final SearchersParsingContext givenContext = createContext();
        final TestFilterTagHandler givenTagHandler = new TestFilterTagHandler(givenContext);

        final FilterFactory<Filter<List<XWPFTableRow>>, ?> givenFilterFactory = mock(FilterFactory.class);

        givenTagHandler.accumulateTranslatedValue(givenContext, givenFilterFactory);
    }

    private static SearchersParsingContext createContext() {
        return createContext(() -> mock(Attributes.class));
    }

    @SuppressWarnings("SameParameterValue")
    private static SearchersParsingContext createContext(final int filtrationCellIndex) {
        return createContext(() -> createAttributes(filtrationCellIndex));
    }

    private static SearchersParsingContext createContext(final Supplier<Attributes> attributesSupplier) {
        final Attributes lastAttributes = attributesSupplier.get();
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);
        when(givenContext.getLastAttributes()).thenReturn(lastAttributes);
        return givenContext;
    }

    private static Attributes createAttributes(final int filtrationCellIndex) {
        final String filtrationCellIndexAsString = Integer.toString(filtrationCellIndex);
        final Attributes attributes = mock(Attributes.class);
        when(attributes.getValue(eq(FILTRATION_CELL_INDEX_ATTRIBUTE_NAME))).thenReturn(filtrationCellIndexAsString);
        return attributes;
    }

    private static final class TestFilterTagHandler extends FilterTagHandler<Filter<?>, FilterFactory<?, ?>> {
        private static final int NOT_DEFINED_DEFAULT_FILTRATION_CELL_INDEX = MIN_VALUE;

        private final int defaultFiltrationCellIndex;
        private final SearchersParsingContext expectedContext;

        @Getter
        private Filter<?> accumulatedFilter;

        public TestFilterTagHandler(final int defaultFiltrationCellIndex,
                                    final SearchersParsingContext expectedContext) {
            super(null, null);
            this.defaultFiltrationCellIndex = defaultFiltrationCellIndex;
            this.expectedContext = expectedContext;
        }

        public TestFilterTagHandler(final SearchersParsingContext expectedContext) {
            this(NOT_DEFINED_DEFAULT_FILTRATION_CELL_INDEX, expectedContext);
        }

        @Override
        public void handleStartTag(final SearchersParsingContext context) {
            throw new UnsupportedOperationException();
        }

        @Override
        protected void accumulateAdditionalValues(final SearchersParsingContext context) {
            throw new UnsupportedOperationException();
        }

        @Override
        protected OptionalInt findDefaultFiltrationCellIndex() {
            return this.defaultFiltrationCellIndex != NOT_DEFINED_DEFAULT_FILTRATION_CELL_INDEX
                    ? OptionalInt.of(this.defaultFiltrationCellIndex)
                    : empty();
        }

        @Override
        protected void accumulateFilter(final SearchersParsingContext context, final Filter<?> filter) {
            this.checkContext(context);
            this.accumulatedFilter = filter;
        }

        private void checkContext(final SearchersParsingContext context) {
            if (context != this.expectedContext) {
                throw new IllegalArgumentException("Given and expected contexts aren't identical");
            }
        }
    }
}
