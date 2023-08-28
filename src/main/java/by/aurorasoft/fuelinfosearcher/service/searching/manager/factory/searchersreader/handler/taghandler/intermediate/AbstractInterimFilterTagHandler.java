package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.intermediate;

import by.aurorasoft.fuelinfosearcher.functionalinterface.rowfilterfactory.InterimRowFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.context.FuelSearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter.InterimFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.AbstractTagHandler;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.intermediate.exception.FilterNotExistException;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.AbstractInterimRowFilter;
import org.xml.sax.Attributes;

import java.util.Optional;
import java.util.OptionalInt;

import static java.lang.Integer.parseInt;
import static java.util.OptionalInt.empty;

public abstract class AbstractInterimFilterTagHandler extends AbstractTagHandler {
    private static final String FILTRATION_CELL_INDEX_ATTRIBUTE_NAME = "cell-index";

    private final InterimFilterFactoryDictionary filterFactoryDictionary;

    public AbstractInterimFilterTagHandler(final String tagName,
                                           final InterimFilterFactoryDictionary filterFactoryDictionary) {
        super(tagName);
        this.filterFactoryDictionary = filterFactoryDictionary;
    }

    @Override
    public void handle(final FuelSearchersParsingContext context) {
        final AbstractInterimRowFilter rowFilter = this.createFilter(context);
        context.accumulateFilter(rowFilter);
    }

    protected abstract AbstractInterimRowFilter createFilter(final InterimRowFilterFactory filterFactory);

    private AbstractInterimRowFilter createFilter(final FuelSearchersParsingContext context) {
        final InterimRowFilterFactory filterFactory = this.findFilterFactory(context);
        final OptionalInt optionalFiltrationCellIndex = findFiltrationCellIndex(context);
        return optionalFiltrationCellIndex.stream()
                .mapToObj(filterFactory)
                .findFirst()
                .orElseGet(() -> this.createFilter(filterFactory));
    }

    private InterimRowFilterFactory findFilterFactory(final FuelSearchersParsingContext context) {
        final String filterKey = context.getLastContent();
        final Optional<InterimRowFilterFactory> optionalFilterFactory = this.filterFactoryDictionary.find(filterKey);
        return optionalFilterFactory.orElseThrow(FilterNotExistException::new);
    }

    private static OptionalInt findFiltrationCellIndex(final FuelSearchersParsingContext context) {
        final Attributes attributes = context.getLastAttributes();
        final String cellIndex = attributes.getValue(FILTRATION_CELL_INDEX_ATTRIBUTE_NAME);
        return cellIndex != null ? OptionalInt.of(parseInt(cellIndex)) : empty();
    }
}
