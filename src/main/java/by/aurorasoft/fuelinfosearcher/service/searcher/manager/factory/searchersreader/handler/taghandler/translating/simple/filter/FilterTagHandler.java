package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating.simple.filter;

import by.aurorasoft.fuelinfosearcher.functionalinterface.filterfactory.FilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.dictionary.rowfilter.FilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating.simple.SimpleTranslatingTagHandler;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating.simple.filter.exception.NoSuchFilterException;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.Filter;
import org.xml.sax.Attributes;

import static java.lang.Integer.parseInt;

public abstract class FilterTagHandler<FILTER extends Filter<?>, FILTER_FACTORY extends FilterFactory<FILTER>>
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

    protected abstract int findDefaultFiltrationCellIndex();

    protected abstract void accumulateFilter(final SearchersParsingContext context, final FILTER filter);

    private FILTER createFilter(final SearchersParsingContext context, final FILTER_FACTORY filterFactory) {
        final int filtrationCellIndex = this.findFiltrationCellIndex(context);
        return filterFactory.apply(filtrationCellIndex);
    }

    private int findFiltrationCellIndex(final SearchersParsingContext context) {
        final Attributes attributes = context.getLastAttributes();
        final String cellIndex = attributes.getValue(FILTRATION_CELL_INDEX_ATTRIBUTE_NAME);
        return cellIndex != null ? parseInt(cellIndex) : this.findDefaultFiltrationCellIndex();
    }
}
