package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.dictionary.rowfilter.interim;

import by.aurorasoft.fuelinfosearcher.functionalinterface.filterfactory.interim.GroupFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searcher.filter.interim.group.*;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

@Component
public final class GroupFilterFactoryDictionary extends InterimFilterFactoryDictionary<GroupFilterFactory> {

    //TODO: read from file
    private static final Map<String, GroupFilterFactory> FACTORIES_BY_KEYS = ofEntries(
            entry("удельное сопротивление", SpecificResistanceGroupFilter::new),
            entry("тип почвы", SoilTypeGroupFilter::new),
            entry("глубина обработки", ProcessingDepthGroupFilter::new),
            entry("норма высева", SowingNormGroupFilter::new),
            entry("тип удобрения", FertilizerTypeGroupFilter::new),
            entry("класс груза", CargoClassGroupFilter::new),
            entry("группа дорог", RoadGroupGroupFilter::new)
    );

    public GroupFilterFactoryDictionary() {
        super(FACTORIES_BY_KEYS);
    }

}
