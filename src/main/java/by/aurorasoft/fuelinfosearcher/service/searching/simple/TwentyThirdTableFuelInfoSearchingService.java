package by.aurorasoft.fuelinfosearcher.service.searching.simple;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.chain.RowFilterChain;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.conclusive.YieldRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.united.MachineryRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.united.WorkingWidthRowFilter;
import org.springframework.stereotype.Service;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractRoutingLength;

@Service
public final class TwentyThirdTableFuelInfoSearchingService extends TEMPAbstractSimpleTableFuelInfoSearchingService {
    private static final String TABLE_NAME = "ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА";
    private static final String[] FUEL_INFO_HEADERS = new String[]{
            "Менее 150", "151...200", "201...300", "301...400", "401...600", "601...1000", "Более 1000"
    };

    private static final int CELL_INDEX_MACHINERY = 1;
    private static final int CELL_INDEX_WORKING_WIDTH = 3;
    private static final int CELL_INDEX_YIELD = 2;

    public TwentyThirdTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, FUEL_INFO_HEADERS);
    }

    @Override
    protected RowFilterChain createRowFilterChain() {
        return RowFilterChain.builder()
                .intermediateFilter(new MachineryRowFilter(CELL_INDEX_MACHINERY))
                .intermediateFilter(new WorkingWidthRowFilter(CELL_INDEX_WORKING_WIDTH))
                .conclusiveFilter(new YieldRowFilter(CELL_INDEX_YIELD))
                .build();
    }

    @Override
    protected String extractFuelHeaderCellValue(final FuelSpecification specification) {
        return extractRoutingLength(specification);
    }
}
