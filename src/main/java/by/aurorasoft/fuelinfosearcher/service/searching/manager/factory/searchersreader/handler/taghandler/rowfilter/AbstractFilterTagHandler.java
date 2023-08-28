package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.rowfilter;

import by.aurorasoft.fuelinfosearcher.functionalinterface.rowfilterfactory.FilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.context.FuelSearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter.AbstractFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.AbstractTagHandler;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.rowfilter.intermediate.exception.FilterNotExistException;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.rowfilter.intermediate.exception.FiltrationCellIndexNotDefinedException;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.AbstractRowFilter;
import org.xml.sax.Attributes;

import java.util.Optional;
import java.util.OptionalInt;

import static java.lang.Integer.parseInt;

public abstract class AbstractFilterTagHandler<
        FILTER extends AbstractRowFilter<?>,
        FILTER_FACTORY extends FilterFactory<FILTER>
        >
        extends AbstractTagHandler {

    private static final String FILTRATION_CELL_INDEX_ATTRIBUTE_NAME = "cell-index";

    private final AbstractFilterFactoryDictionary<FILTER_FACTORY> filterFactoryDictionary;


    public AbstractFilterTagHandler(final String tagName,
                                    final AbstractFilterFactoryDictionary<FILTER_FACTORY> filterFactoryDictionary) {
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
        return optionalFilterFactory.orElseThrow(FilterNotExistException::new);
    }

    private OptionalInt findFiltrationCellIndex(final FuelSearchersParsingContext context) {
        final Attributes attributes = context.getLastAttributes();
        final String cellIndex = attributes.getValue(FILTRATION_CELL_INDEX_ATTRIBUTE_NAME);
        return cellIndex != null ? OptionalInt.of(parseInt(cellIndex)) : this.findDefaultFiltrationCellIndex();
    }
}
