package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.rowfilter.intermediate;

import by.aurorasoft.fuelinfosearcher.functionalinterface.filterfactory.interim.UnitedFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter.interim.UnitedFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.united.UnitedFilter;
import org.springframework.stereotype.Component;

import java.util.OptionalInt;

import static java.util.OptionalInt.empty;

@Component
public final class UnitedFilterTagHandler extends AbstractInterimFilterTagHandler<UnitedFilter, UnitedFilterFactory> {
    private static final String TAG_NAME = "filter-by";

    public UnitedFilterTagHandler(final UnitedFilterFactoryDictionary filterFactoryDictionary) {
        super(TAG_NAME, filterFactoryDictionary);
    }

    @Override
    protected OptionalInt findDefaultFiltrationCellIndex() {
        return empty();
    }

}
