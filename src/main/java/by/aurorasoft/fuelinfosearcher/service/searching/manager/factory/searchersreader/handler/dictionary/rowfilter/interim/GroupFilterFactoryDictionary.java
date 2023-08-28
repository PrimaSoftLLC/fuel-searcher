package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter.interim;

import by.aurorasoft.fuelinfosearcher.functionalinterface.filterfactory.interim.GroupFilterFactory;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.group.ProcessingDepthRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.group.SoilTypeRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.group.SowingNormRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.group.SpecificResistanceRowFilter;
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
            entry("норма высева", SowingNormRowFilter::new)
    );

    public GroupFilterFactoryDictionary() {
        super(FACTORIES_BY_KEYS);
    }

}
