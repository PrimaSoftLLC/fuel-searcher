//package by.aurorasoft.fuelinfosearcher.service.searching.simple.rackingandturninghay;
//
//import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
//import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
//import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.chain.RowFilterChain;
//import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.conclusive.WorkingWidthRowFilter;
//import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.united.MachineryRowFilter;
//import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.united.TractorRowFilter;
//import by.aurorasoft.fuelinfosearcher.service.searching.simple.TEMPAbstractSimpleTableFuelInfoSearchingService;
//
//import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractRoutingLength;
//
//public abstract class AbstractRackingAndTurningRayFuelInfoSearchingService extends TEMPAbstractSimpleTableFuelInfoSearchingService {
//    private static final String[] FUEL_INFO_HEADERS = {
//            "Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"
//    };
//
//    private static final int CELL_INDEX_TRACTOR = 1;
//    private static final int CELL_INDEX_MACHINERY = 2;
//    private static final int CELL_INDEX_WORKING_WIDTH = 3;
//
//    public AbstractRackingAndTurningRayFuelInfoSearchingService(final FuelDocument fuelDocument,
//                                                                final String fuelTableName) {
//        super(fuelDocument, fuelTableName, FUEL_INFO_HEADERS);
//    }
//
//    @Override
//    protected final RowFilterChain createRowFilterChain() {
//        return RowFilterChain.builder()
//                .intermediateFilter(createTractorRowFilter())
//                .intermediateFilter(createMachineryRowFilter())
//                .conclusiveFilter(createWorkingWidthRowFilter())
//                .build();
//    }
//
//    @Override
//    protected final String extractFuelHeaderCellValue(final FuelSpecification specification) {
//        return extractRoutingLength(specification);
//    }
//
//    private static TractorRowFilter createTractorRowFilter() {
//        return new TractorRowFilter(CELL_INDEX_TRACTOR);
//    }
//
//    private static MachineryRowFilter createMachineryRowFilter() {
//        return new MachineryRowFilter(CELL_INDEX_MACHINERY);
//    }
//
//    private static WorkingWidthRowFilter createWorkingWidthRowFilter() {
//        return new WorkingWidthRowFilter(CELL_INDEX_WORKING_WIDTH);
//    }
//}
