package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.united;

import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractTractor;

public final class TractorRowFilter extends AbstractUnitedRowFilter {

    public TractorRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected String extractFilteringValue(final FuelSpecification specification) {
        return extractTractor(specification);
    }
}
