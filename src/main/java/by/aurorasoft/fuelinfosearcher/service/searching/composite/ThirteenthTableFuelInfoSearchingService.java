//package by.aurorasoft.fuelinfosearcher.service.searching.composite;
//
//import by.aurorasoft.fuelinfosearcher.model.*;
//import by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil;
//import org.apache.poi.xwpf.usermodel.XWPFTableRow;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.OptionalInt;
//import java.util.function.Function;
//import java.util.stream.Stream;
//
//import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.*;
//import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.*;
//

//TODO: разбораться что с классами грузов и сделать
//@Service
//public final class ThirteenthTableFuelInfoSearchingService extends AbstractCompositeTableFuelInfoSearchingService {
//    private static final String TABLE_NAME = "ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ";
//
//    private static final String ELEMENT_TABLE_TITLE_TEMPLATE = "%s с разбрасывателем %s\nПроизводительность погрузчика более 60 т/ч";
//
//    private static final String REGEX_CONTENT_OF_ROAD_GROUP = "((Первая)|(Вторая)|(Третья)) группа дорог";
//
//    private static final int CELL_INDEX_WITH_ROAD_GROUP = 0;
//
//    private static final int CELL_INDEX_WITH_TRANSPORT_DISTANCE = 1;
//
//    public ThirteenthTableFuelInfoSearchingService(final FuelInfoOffsetFromRoutingLengthStorage offsetStorage,
//                                                   final FuelDocument fuelDocument) {
//        super(offsetStorage, fuelDocument, TABLE_NAME);
//    }
//
//    @Override
//    protected Optional<FuelInfo> find(final List<XWPFTableRow> elementTableRows,
//                                      final FuelInfoSpecification specification) {
//        Optional<FuelInfo> fuelInfo = findRowsByRoadGroup(elementTableRows, specification)
//                .flatMap(rows -> findRowByTransportDistance(rows, specification))
//                .
//    }
//
//    @Override
//    protected String findElementTableTitleTemplate() {
//        return ELEMENT_TABLE_TITLE_TEMPLATE;
//    }
//
//    @Override
//    protected Stream<Function<FuelInfoSpecification, String>> findElementTableTitleTemplateArgumentExtractors() {
//        return Stream.of(
//                FuelInfoSpecificationUtil::extractTractor,
//                FuelInfoSpecificationUtil::extractMachinery
//        );
//    }
//
//    private static Optional<List<XWPFTableRow>> findRowsByRoadGroup(final List<XWPFTableRow> rows,
//                                                                    final FuelInfoSpecification specification) {
//        Optional<List<XWPFTableRow>> first = findIndexRowByRoadGroup(rows, specification)
//                .stream()
//                .map(indexRowWithRoadGroup -> indexRowWithRoadGroup + 1)
//                .mapToObj(indexFirstMatchingRow -> findIndexBordersRowsMatchingRoadGroup(indexFirstMatchingRow, rows))
//                .map(borderRowIndexes -> extractRows(rows, borderRowIndexes))
//                .findFirst();
//        return first;
//    }
//
//    private static OptionalInt findIndexRowByRoadGroup(final List<XWPFTableRow> rows,
//                                                       final FuelInfoSpecification specification) {
//        final String roadGroup = extractRoadGroup(specification);
//        return findIndexFirstRowByContent(rows, CELL_INDEX_WITH_ROAD_GROUP, roadGroup);
//    }
//
//    private static IntPair findIndexBordersRowsMatchingRoadGroup(final int indexFirstMatchingRow,
//                                                                 final List<XWPFTableRow> rows) {
//        final int nextIndexLastMatchingRow = findIndexRowNextRoadGroupOrLastRow(rows, indexFirstMatchingRow);
//        return new IntPair(indexFirstMatchingRow, nextIndexLastMatchingRow);
//    }
//
//    private static int findIndexRowNextRoadGroupOrLastRow(final List<XWPFTableRow> rows,
//                                                          final int startSearchingIndex) {
//        return findIndexFirstRowByContentRegex(
//                rows,
//                startSearchingIndex,
//                CELL_INDEX_WITH_ROAD_GROUP,
//                REGEX_CONTENT_OF_ROAD_GROUP
//        ).orElse(rows.size());
//    }
//
//    private static List<XWPFTableRow> extractRows(final List<XWPFTableRow> rows, final IntPair borders) {
//        final int indexFirstRow = borders.getFirst();
//        final int nextIndexLastRow = borders.getSecond();
//        return rows.subList(indexFirstRow, nextIndexLastRow);
//    }
//
//    private static Optional<XWPFTableRow> findRowByTransportDistance(final List<XWPFTableRow> rows,
//                                                                     final FuelInfoSpecification specification) {
//        final String transportDistance = extractTransportDistance(specification);
//        Optional<XWPFTableRow> firstRowByContent = findFirstRowByContent(rows, CELL_INDEX_WITH_TRANSPORT_DISTANCE, transportDistance);
//        return firstRowByContent;
//    }
//
//    private static int findIndexCellGenerationNorm(final FuelInfoSpecification specification) {
//        if (extractCargoClass(specification).equals("I")) {
//
//        }
//        else if(extractRoadGroup(specification.equals("")))
//    }
//}
