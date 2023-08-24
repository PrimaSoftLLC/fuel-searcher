//package by.aurorasoft.fuelinfosearcher.service.searching.simple;
//
//import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
//import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
//import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.chain.RowFilterChain;
//import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.conclusive.YieldRowFilter;
//import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.group.SoilTypeRowFilter;
//import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.united.MachineryRowFilter;
//import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.united.RowWidthRowFilter;
//import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.united.TractorRowFilter;
//import org.springframework.stereotype.Service;
//
//import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractRoutingLength;
//
//@Service
//public final class TwentyFifthTableFuelInfoSearchingService extends TEMPAbstractSimpleTableFuelInfoSearchingService {
//    private static final String TABLE_NAME = "УБОРКА КАРТОФЕЛЯ";
//    private static final String[] FUEL_INFO_HEADERS = new String[]{
//            "Менее 150", "151...200", "201...300", "301...400", "401...600", "601...1000", "Более 1000"
//    };
//
//    private static final int CELL_INDEX_TRACTOR = 1;
//    private static final int CELL_INDEX_MACHINERY = 2;
//    private static final int CELL_INDEX_ROW_WIDTH = 3;
//    private static final int CELL_INDEX_YIELD = 4;
//
//
//    public TwentyFifthTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
//        super(fuelDocument, TABLE_NAME, FUEL_INFO_HEADERS);
//    }
//
//    @Override
//    protected RowFilterChain createRowFilterChain() {
//        return RowFilterChain.builder()
//                .intermediateFilter(new SoilTypeRowFilter())
//                .intermediateFilter(new TractorRowFilter(CELL_INDEX_TRACTOR))
//                .intermediateFilter(new MachineryRowFilter(CELL_INDEX_MACHINERY))
//                .intermediateFilter(new RowWidthRowFilter(CELL_INDEX_ROW_WIDTH))
//                .conclusiveFilter(new YieldRowFilter(CELL_INDEX_YIELD))
//                .build();
//    }
//
//    @Override
//    protected String extractFuelHeaderCellValue(final FuelSpecification specification) {
//        return extractRoutingLength(specification);
//    }
//}
