//package by.aurorasoft.fuelinfosearcher.service.searching.composite;
//
//import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
//import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
//import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.chain.RowFilterChain;
//import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.conclusive.YieldRowFilter;
//import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.united.WorkingWidthRowFilter;
//import by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil;
//import org.springframework.stereotype.Service;
//
//import java.util.function.Function;
//import java.util.stream.Stream;
//
//import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractRoutingLength;
//
//@Service
//public final class TwentyFourthTableFuelInfoSearchingService extends AbstractCompositeTableFuelInfoSearchingService {
//    private static final String TABLE_NAME = "ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ";
//    private static final String[] FUEL_INFO_HEADERS = new String[]{
//            "Менее 150", "151...200", "201...300", "301...400", "401...600", "601...1000", "Более 1000"
//    };
//    private static final String ELEMENT_TABLE_TITLE_TEMPLATE = "%s Соотношение массы зерна к массе соломы %s";
//
//    private static final int CELL_INDEX_WORKING_WIDTH = 2;
//    private static final int CELL_INDEX_YIELD = 1;
//
//    public TwentyFourthTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
//        super(fuelDocument, TABLE_NAME, FUEL_INFO_HEADERS);
//    }
//
//    @Override
//    protected RowFilterChain createRowFilterChain() {
//        return RowFilterChain.builder()
//                .intermediateFilter(new WorkingWidthRowFilter(CELL_INDEX_WORKING_WIDTH))
//                .conclusiveFilter(new YieldRowFilter(CELL_INDEX_YIELD))
//                .build();
//    }
//
//    @Override
//    protected String extractFuelHeaderCellValue(final FuelSpecification specification) {
//        return extractRoutingLength(specification);
//    }
//
//    @Override
//    protected String findElementTableTitleTemplate() {
//        return ELEMENT_TABLE_TITLE_TEMPLATE;
//    }
//
//    @Override
//    protected Stream<Function<FuelSpecification, String>> findElementTableTitleTemplateArgumentExtractors() {
//        return Stream.of(
//                FuelSpecificationExtractingPropertyUtil::extractCombine,
//                FuelSpecificationExtractingPropertyUtil::extractWeightRatioGrainToStraw
//        );
//    }
//}
