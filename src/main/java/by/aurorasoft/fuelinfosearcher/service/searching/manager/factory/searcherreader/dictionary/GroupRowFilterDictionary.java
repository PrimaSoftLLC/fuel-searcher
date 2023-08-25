package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader.dictionary;

import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.group.AbstractGroupRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.group.SpecificResistanceRowFilter;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

@Component
public final class GroupRowFilterDictionary extends AbstractDictionary<AbstractGroupRowFilter> {
    private static final Map<String, AbstractGroupRowFilter> FILTERS_BY_PROPERTY_NAMES = ofEntries(
            entry("удельное сопротивление", new SpecificResistanceRowFilter())
    );

    public GroupRowFilterDictionary() {
        super(FILTERS_BY_PROPERTY_NAMES);
    }
}
