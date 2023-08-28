package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.dictionary.rowfilter.interim;

import by.aurorasoft.fuelinfosearcher.functionalinterface.filterfactory.interim.GroupFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.intermediate.group.*;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

@Component
public final class GroupFilterFactoryDictionary extends InterimFilterFactoryDictionary<GroupFilterFactory> {

    //TODO: read from file
    private static final Map<String, GroupFilterFactory> FACTORIES_BY_KEYS = ofEntries(
            entry("удельное сопротивление", SpecificResistanceRowFilter::new),
            entry("тип почвы", SoilTypeRowFilter::new),
            entry("глубина обработки", ProcessingDepthRowFilter::new),
            entry("норма высева", SowingNormRowFilter::new),
            entry("тип удобрения", FertilizerTypeRowFilter::new),
            entry("класс груза", CargoClassRowFilter::new),
            entry("группа дорог", RoadGroupRowFilter::new)
    );

    public GroupFilterFactoryDictionary() {
        super(FACTORIES_BY_KEYS);
    }

}
