package by.aurorasoft.fuelinfosearcher.service.searching.simple.ploughing;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoOffsetFromRoutingLengthStorage;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.model.IntPair;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.AbstractSimpleTableFuelInfoSearchingService;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.*;
import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.*;

public abstract class AbstractPloughingFuelInfoSearchingServices extends AbstractSimpleTableFuelInfoSearchingService {
    private static final int CELL_INDEX_WITH_GROUP_VALUE = 0;
    private static final int CELL_INDEX_WITH_TRACTOR = 1;
    private static final int CELL_INDEX_WITH_MACHINERY = 2;
    private static final int CELL_INDEX_WITH_CORPUS_COUNT = 3;
    private static final int CELL_INDEX_WITH_PLOUGHING_DEPTH = 4;

    public AbstractPloughingFuelInfoSearchingServices(final FuelInfoOffsetFromRoutingLengthStorage offsetStorage,
                                                      final FuelDocument fuelDocument,
                                                      final String fuelTableName) {
        super(offsetStorage, fuelDocument, fuelTableName);
    }

    @Override
    protected final Optional<XWPFTableRow> findAppropriateRow(final List<XWPFTableRow> elementTableRows,
                                                          final FuelInfoSpecification specification) {
        return this.findRowsByGroupValue(elementTableRows, specification)
                .flatMap(rows -> findRowsByTractor(rows, specification))
                .flatMap(rows -> findRowsByMachinery(rows, specification))
                .flatMap(rows -> findRowsByCorpusCount(rows, specification))
                .flatMap(rows -> findRowByPloughingDepth(rows, specification));
    }

    //Группа - это удельное сопротивление для таблиц #1 и #2 и тип почвы для таблицы #3
    protected abstract String extractGroupValue(final FuelInfoSpecification specification);
    protected abstract String findGroupValueRegex();

    private Optional<List<XWPFTableRow>> findRowsByGroupValue(final List<XWPFTableRow> rows,
                                                              final FuelInfoSpecification specification) {
        return findIndexRowByGroupValue(rows, specification)
                .stream()
                .map(indexRowWithGroupValue -> indexRowWithGroupValue + 1)
                .mapToObj(indexFirstMatchingRow -> this.findIndexBordersRowsMatchingSpecificResistance(indexFirstMatchingRow, rows))
                .map(borderRowIndexes -> extractRows(rows, borderRowIndexes))
                .findFirst();
    }

    private OptionalInt findIndexRowByGroupValue(final List<XWPFTableRow> rows,
                                                 final FuelInfoSpecification specification) {
        final String groupValue = this.extractGroupValue(specification);
        return findIndexFirstRowByContent(rows, CELL_INDEX_WITH_GROUP_VALUE, groupValue);
    }

    private IntPair findIndexBordersRowsMatchingSpecificResistance(final int indexFirstMatchingRow,
                                                                   final List<XWPFTableRow> rows) {
        final int nextIndexLastMatchingRow = findIndexRowNextSpecificResistanceOrLastRow(rows, indexFirstMatchingRow);
        return new IntPair(indexFirstMatchingRow, nextIndexLastMatchingRow);
    }

    private int findIndexRowNextSpecificResistanceOrLastRow(final List<XWPFTableRow> rows,
                                                            final int startSearchingIndex) {
        return findIndexFirstRowByContentRegex(
                rows,
                startSearchingIndex,
                CELL_INDEX_WITH_GROUP_VALUE,
                this.findGroupValueRegex()
        ).orElse(rows.size());
    }

    private static List<XWPFTableRow> extractRows(final List<XWPFTableRow> rows, final IntPair borders) {
        final int indexFirstRow = borders.getFirst();
        final int nextIndexLastRow = borders.getSecond();
        return rows.subList(indexFirstRow, nextIndexLastRow);
    }

    private static Optional<List<XWPFTableRow>> findRowsByTractor(final List<XWPFTableRow> rows,
                                                                  final FuelInfoSpecification specification) {
        //TODO: remove variable
        Optional<List<XWPFTableRow>> unitedRowsByContent = findUnitedRowsByContent(
                rows,
                CELL_INDEX_WITH_TRACTOR,
                extractTractor(specification)
        );
        return unitedRowsByContent;
    }

    private static Optional<List<XWPFTableRow>> findRowsByMachinery(final List<XWPFTableRow> rows,
                                                                    final FuelInfoSpecification specification) {
        //TODO: remove variable
        Optional<List<XWPFTableRow>> unitedRowsByContent = findUnitedRowsByContent(
                rows,
                CELL_INDEX_WITH_MACHINERY,
                extractMachinery(specification)
        );
        return unitedRowsByContent;
    }

    private static Optional<List<XWPFTableRow>> findRowsByCorpusCount(final List<XWPFTableRow> rows,
                                                                      final FuelInfoSpecification specification) {
        Optional<List<XWPFTableRow>> unitedRowsByContent = findUnitedRowsByContent(
                rows,
                CELL_INDEX_WITH_CORPUS_COUNT,
                extractCorpusCount(specification)
        );
        return unitedRowsByContent;
    }

    private static Optional<XWPFTableRow> findRowByPloughingDepth(final List<XWPFTableRow> rows,
                                                                  final FuelInfoSpecification specification) {
        final String ploughingDepth = extractPloughingDepth(specification);
        return findFirstRowByContent(rows, CELL_INDEX_WITH_PLOUGHING_DEPTH, ploughingDepth);
    }
}
