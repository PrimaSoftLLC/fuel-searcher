package by.aurorasoft.fuelinfosearcher.service.searching.simple.ploughing;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.chain.RowFilterChain;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.conclusive.PloughingDepthRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.group.AbstractGroupRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.united.CorpusCountRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.united.MachineryRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.united.TractorRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.TEMPAbstractSimpleTableFuelInfoSearchingService;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractRoutingLength;

public abstract class AbstractPloughingFuelInfoSearchingService extends TEMPAbstractSimpleTableFuelInfoSearchingService {
    private static final String[] FUEL_INFO_HEADERS = new String[]{
            "Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"
    };

    private static final int CELL_INDEX_TRACTOR = 1;
    private static final int CELL_INDEX_MACHINERY = 2;
    private static final int CELL_INDEX_CORPUS_COUNT = 3;
    private static final int CELL_INDEX_PLOUGHING_DEPTH = 4;

    public AbstractPloughingFuelInfoSearchingService(final FuelDocument fuelDocument, final String fuelTableName) {
        super(fuelDocument, fuelTableName, FUEL_INFO_HEADERS);
    }

    @Override
    protected final RowFilterChain createRowFilterChain() {
        return RowFilterChain.builder()
                .intermediateFilter(this.createGroupRowFilter())
                .intermediateFilter(createTractorRowFilter())
                .intermediateFilter(createMachineryRowFilter())
                .intermediateFilter(createCorpusCountRowFilter())
                .conclusiveFilter(createPloughingDepthRowFilter())
                .build();
    }

    @Override
    protected final String extractFuelHeaderCellValue(final FuelSpecification specification) {
        return extractRoutingLength(specification);
    }

    protected abstract AbstractGroupRowFilter createGroupRowFilter();

    private static TractorRowFilter createTractorRowFilter() {
        return new TractorRowFilter(CELL_INDEX_TRACTOR);
    }

    private static MachineryRowFilter createMachineryRowFilter() {
        return new MachineryRowFilter(CELL_INDEX_MACHINERY);
    }

    private static CorpusCountRowFilter createCorpusCountRowFilter() {
        return new CorpusCountRowFilter(CELL_INDEX_CORPUS_COUNT);
    }

    private static PloughingDepthRowFilter createPloughingDepthRowFilter() {
        return new PloughingDepthRowFilter(CELL_INDEX_PLOUGHING_DEPTH);
    }
}
