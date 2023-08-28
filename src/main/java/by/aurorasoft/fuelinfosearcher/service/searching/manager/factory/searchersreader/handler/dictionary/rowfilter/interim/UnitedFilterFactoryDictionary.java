package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter.interim;

import by.aurorasoft.fuelinfosearcher.functionalinterface.filterfactory.interim.UnitedFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.united.*;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

@Component
public final class UnitedFilterFactoryDictionary extends InterimFilterFactoryDictionary<UnitedFilterFactory> {

    //TODO: read from file
    private static final Map<String, UnitedFilterFactory> FACTORIES_BY_KEYS = ofEntries(
            entry("трактор", TractorRowFilter::new),
            entry("механизм", MachineryRowFilter::new),
            entry("количество корпусов", CorpusCountRowFilter::new),
            entry("ширина захвата", WorkingWidthRowFilter::new),
            entry("ширина междурядий", RowWidthRowFilter::new)
    );

    public UnitedFilterFactoryDictionary() {
        super(FACTORIES_BY_KEYS);
    }

}
