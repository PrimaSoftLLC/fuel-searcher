package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.rowfilter;

import by.aurorasoft.fuelinfosearcher.functionalinterface.filterfactory.FilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.context.FuelSearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter.FilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.TagHandler;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.rowfilter.exception.NoSuchFilterException;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.rowfilter.exception.FiltrationCellIndexNotDefinedException;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.Filter;
import org.xml.sax.Attributes;

import java.util.Optional;
import java.util.OptionalInt;

import static java.lang.Integer.parseInt;

public abstract class FilterTagHandler<FILTER extends Filter<?>, FILTER_FACTORY extends FilterFactory<FILTER>>
        extends TagHandler {

    private static final String FILTRATION_CELL_INDEX_ATTRIBUTE_NAME = "cell-index";

    private final FilterFactoryDictionary<FILTER_FACTORY> filterFactoryDictionary;


    public FilterTagHandler(final String tagName,
                            final FilterFactoryDictionary<FILTER_FACTORY> filterFactoryDictionary) {
        super(tagName);
        this.filterFactoryDictionary = filterFactoryDictionary;
    }

    @Override
    public void handle(final FuelSearchersParsingContext context) {
        final FILTER filter = this.createFilter(context);
        this.accumulateFilter(context, filter);
    }

    protected abstract OptionalInt findDefaultFiltrationCellIndex();

    protected abstract void accumulateFilter(final FuelSearchersParsingContext context, final FILTER filter);

    private FILTER createFilter(final FuelSearchersParsingContext context) {
        final FILTER_FACTORY filterFactory = this.findFilterFactory(context);
        final OptionalInt optionalFiltrationCellIndex = this.findFiltrationCellIndex(context);
        final int filtrationCellIndex = optionalFiltrationCellIndex.orElseThrow(
                FiltrationCellIndexNotDefinedException::new
        );
        return filterFactory.apply(filtrationCellIndex);
    }

    private FILTER_FACTORY findFilterFactory(final FuelSearchersParsingContext context) {
        final String filterKey = context.getLastContent();
        final Optional<FILTER_FACTORY> optionalFilterFactory = this.filterFactoryDictionary.find(filterKey);
        return optionalFilterFactory.orElseThrow(NoSuchFilterException::new);
    }

    private OptionalInt findFiltrationCellIndex(final FuelSearchersParsingContext context) {
        final Attributes attributes = context.getLastAttributes();
        final String cellIndex = attributes.getValue(FILTRATION_CELL_INDEX_ATTRIBUTE_NAME);
        return cellIndex != null ? OptionalInt.of(parseInt(cellIndex)) : this.findDefaultFiltrationCellIndex();
    }
}
