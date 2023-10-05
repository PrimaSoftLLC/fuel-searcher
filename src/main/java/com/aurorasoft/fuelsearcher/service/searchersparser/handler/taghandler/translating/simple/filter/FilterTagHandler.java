package com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.simple.filter;

import com.aurorasoft.fuelsearcher.service.filter.Filter;
import com.aurorasoft.fuelsearcher.service.factory.filter.FilterFactory;
import com.aurorasoft.fuelsearcher.service.dictionary.filter.FilterFactoryDictionary;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.exception.NoSuchKeyException;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.simple.SimpleTranslatingTagHandler;
import org.xml.sax.Attributes;

import java.util.OptionalInt;

import static java.lang.Integer.parseInt;

public abstract class FilterTagHandler<FILTER extends Filter<?>, FILTER_FACTORY extends FilterFactory<? extends FILTER, ?>>
        extends SimpleTranslatingTagHandler<FILTER_FACTORY> {
    private static final String FILTRATION_CELL_INDEX_ATTRIBUTE_NAME = "cell-index";

    public FilterTagHandler(final String tagName, final FilterFactoryDictionary<FILTER_FACTORY> dictionary) {
        super(tagName, dictionary, NoSuchFilterException::new);
    }

    @Override
    protected final void accumulateTranslatedValue(final SearchersParsingContext context,
                                                   final FILTER_FACTORY filterFactory) {
        final FILTER filter = this.createFilter(context, filterFactory);
        this.accumulateFilter(context, filter);
    }

    protected abstract OptionalInt findDefaultFiltrationCellIndex();

    protected abstract void accumulateFilter(final SearchersParsingContext context, final FILTER filter);

    private FILTER createFilter(final SearchersParsingContext context, final FILTER_FACTORY filterFactory) {
        final int filtrationCellIndex = this.findFiltrationCellIndex(context);
        return filterFactory.create(filtrationCellIndex);
    }

    private int findFiltrationCellIndex(final SearchersParsingContext context) {
        final Attributes attributes = context.getLastAttributes();
        final String cellIndex = attributes.getValue(FILTRATION_CELL_INDEX_ATTRIBUTE_NAME);
        return cellIndex != null ? parseInt(cellIndex) : this.getDefaultFiltrationCellIndex();
    }

    private int getDefaultFiltrationCellIndex() {
        final OptionalInt optionalDefaultFiltrationCellIndex = this.findDefaultFiltrationCellIndex();
        return optionalDefaultFiltrationCellIndex.orElseThrow(NoDefaultFiltrationCellIndexException::new);
    }

    private static final class NoSuchFilterException extends NoSuchKeyException {

        @SuppressWarnings("unused")
        public NoSuchFilterException() {

        }

        public NoSuchFilterException(final String key) {
            super(key);
        }

        @SuppressWarnings("unused")
        public NoSuchFilterException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public NoSuchFilterException(final String key, final Exception cause) {
            super(key, cause);
        }
    }

    private static final class NoDefaultFiltrationCellIndexException extends RuntimeException {

        public NoDefaultFiltrationCellIndexException() {

        }

        @SuppressWarnings("unused")
        public NoDefaultFiltrationCellIndexException(final String description) {
            super(description);
        }

        @SuppressWarnings("unused")
        public NoDefaultFiltrationCellIndexException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public NoDefaultFiltrationCellIndexException(final String description, final Exception cause) {
            super(description, cause);
        }

    }
}
