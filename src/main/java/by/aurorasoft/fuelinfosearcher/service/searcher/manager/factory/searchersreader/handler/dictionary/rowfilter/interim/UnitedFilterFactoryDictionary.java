package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.dictionary.rowfilter.interim;

import by.aurorasoft.fuelinfosearcher.functionalinterface.filterfactory.interim.UnitedFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searcher.filter.interim.unit.*;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

@Component
public final class UnitedFilterFactoryDictionary extends InterimFilterFactoryDictionary<UnitedFilterFactory> {

    //TODO: read from file
    private static final Map<String, UnitedFilterFactory> FACTORIES_BY_KEYS = ofEntries(
            entry("трактор", TractorUnitFilter::new),
            entry("механизм", MachineryUnitFilter::new),
            entry("количество корпусов", CorpusCountUnitFilter::new),
            entry("ширина захвата", WorkingWidthUnitFilter::new),
            entry("ширина междурядий", RowWidthUnitFilter::new),
            entry("комбайн", CombineUnitFilter::new),
            entry("способ загрузки удобрений и расстояние транспортировки", ChargingMethodAndTransportDistanceUnitFilter::new)
    );

    public UnitedFilterFactoryDictionary() {
        super(FACTORIES_BY_KEYS);
    }

}
