package by.aurorasoft.fuelinfosearcher.service.searching.simple.ploughing.byspecificresistance;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.group.AbstractGroupRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.group.SpecificResistanceRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.ploughing.AbstractPloughingFuelInfoSearchingService;

public abstract class AbstractPloughingFuelInfoSearchingBySpecificResistanceService
        extends AbstractPloughingFuelInfoSearchingService {

    public AbstractPloughingFuelInfoSearchingBySpecificResistanceService(final FuelDocument fuelDocument,
                                                                         final String fuelTableName) {
        super(fuelDocument, fuelTableName);
    }

    @Override
    protected final AbstractGroupRowFilter createGroupRowFilter() {
        return new SpecificResistanceRowFilter();
    }
}
