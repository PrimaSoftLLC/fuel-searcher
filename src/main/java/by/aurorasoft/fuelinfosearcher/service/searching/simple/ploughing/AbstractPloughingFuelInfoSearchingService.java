package by.aurorasoft.fuelinfosearcher.service.searching.simple.ploughing;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.model.IntPair;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.AbstractSimpleTableFuelInfoSearchingService;
import by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil.findRowsByTractor;
import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.*;

public abstract class AbstractPloughingFuelInfoSearchingService extends AbstractSimpleTableFuelInfoSearchingService {
    private static final int CELL_INDEX_GROUP_VALUE = 0;
    private static final int CELL_INDEX_MACHINERY = 2;
    private static final int CELL_INDEX_CORPUS_COUNT = 3;
    private static final int CELL_INDEX_PLOUGHING_DEPTH = 4;

    public AbstractPloughingFuelInfoSearchingService(final FuelDocument fuelDocument,
                                                     final String fuelTableName,
                                                     final String[] routingLengths,
                                                     final int firstFuelInfoOffset) {
        super(fuelDocument, fuelTableName, routingLengths, firstFuelInfoOffset);
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
                .map(indexRowGroupValue -> indexRowGroupValue + 1)
                .mapToObj(indexFirstMatchingRow -> this.findIndexBordersRowsMatchingSpecificResistance(indexFirstMatchingRow, rows))
                .map(borderRowIndexes -> extractRows(rows, borderRowIndexes))
                .findFirst();
    }

    private OptionalInt findIndexRowByGroupValue(final List<XWPFTableRow> rows,
                                                 final FuelInfoSpecification specification) {
        return findIndexFirstRowByContent(
                rows,
                CELL_INDEX_GROUP_VALUE,
                specification,
                this::extractGroupValue
        );
    }

    private IntPair findIndexBordersRowsMatchingSpecificResistance(final int indexFirstMatchingRow,
                                                                   final List<XWPFTableRow> rows) {
        final int nextIndexLastMatchingRow = this.findIndexRowNextGroupValueOrLastRow(rows, indexFirstMatchingRow);
        return new IntPair(indexFirstMatchingRow, nextIndexLastMatchingRow);
    }

    private int findIndexRowNextGroupValueOrLastRow(final List<XWPFTableRow> rows,
                                                    final int startSearchingIndex) {
        return findIndexFirstRowByContentRegex(
                rows,
                startSearchingIndex,
                CELL_INDEX_GROUP_VALUE,
                this.findGroupValueRegex()
        ).orElse(rows.size());
    }

    private static Optional<List<XWPFTableRow>> findRowsByMachinery(final List<XWPFTableRow> rows,
                                                                    final FuelInfoSpecification specification) {
        return findUnitedRowsByContent(
                rows,
                CELL_INDEX_MACHINERY,
                specification,
                FuelInfoSpecificationUtil::extractMachinery
        );
    }

    private static Optional<List<XWPFTableRow>> findRowsByCorpusCount(final List<XWPFTableRow> rows,
                                                                      final FuelInfoSpecification specification) {
        return findUnitedRowsByContent(
                rows,
                CELL_INDEX_CORPUS_COUNT,
                specification,
                FuelInfoSpecificationUtil::extractCorpusCount
        );
    }

    private static Optional<XWPFTableRow> findRowByPloughingDepth(final List<XWPFTableRow> rows,
                                                                  final FuelInfoSpecification specification) {
        return findFirstRowByContent(
                rows,
                CELL_INDEX_PLOUGHING_DEPTH,
                specification,
                FuelInfoSpecificationUtil::extractPloughingDepth
        );
    }
}
