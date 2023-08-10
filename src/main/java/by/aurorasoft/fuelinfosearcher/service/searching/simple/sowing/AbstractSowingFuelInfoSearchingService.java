package by.aurorasoft.fuelinfosearcher.service.searching.simple.sowing;

import by.aurorasoft.fuelinfosearcher.model.*;
import by.aurorasoft.fuelinfosearcher.service.searching.AbstractTableFuelInfoSearchingService;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.AbstractSimpleTableFuelInfoSearchingService;
import by.aurorasoft.fuelinfosearcher.util.FuelInfoUtil;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.*;
import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.*;

public abstract class AbstractSowingFuelInfoSearchingService extends AbstractSimpleTableFuelInfoSearchingService {
    private static final int CELL_INDEX_WITH_SOWING_NORM = 0;
    private static final int CELL_INDEX_WITH_TRACTOR = 1;
    private static final int CELL_INDEX_WITH_MACHINERY = 2;
    private static final int CELL_INDEX_WITH_WORKING_WIDTH = 3;

    private static final String REGEX_CONTENT_SOWING_NORM = "Норма высева (семян )?\\d+(–\\d+)? кг/га";

    public AbstractSowingFuelInfoSearchingService(final FuelInfoOffsetFromRoutingLengthStorage offsetStorage,
                                                  final FuelDocument fuelDocument,
                                                  final String fuelTableName) {
        super(offsetStorage, fuelDocument, fuelTableName);
    }

    @Override
    protected final Optional<XWPFTableRow> findAppropriateRow(final List<XWPFTableRow> elementTableRows, final FuelInfoSpecification specification) {
        return findRowsBySowingNorm(elementTableRows, specification)
                .flatMap(rows -> findRowsByTractor(rows, specification))
                .flatMap(rows -> findRowsByMachinery(rows, specification))
                .flatMap(rows -> findRowByWorkingWidth(rows, specification));
    }

    private static Optional<List<XWPFTableRow>> findRowsBySowingNorm(final List<XWPFTableRow> rows,
                                                                     final FuelInfoSpecification specification) {
        Optional<List<XWPFTableRow>> first = findIndexRowBySowingNorm(rows, specification)
                .stream()
                .map(indexRowWithSowingNorm -> indexRowWithSowingNorm + 1)
                .mapToObj(indexFirstMatchingRow -> findIndexBordersRowsMatchingSowingNorm(indexFirstMatchingRow, rows))
                .map(borderRowIndexes -> extractRows(rows, borderRowIndexes))
                .findFirst();
        return first;
    }

    private static OptionalInt findIndexRowBySowingNorm(final List<XWPFTableRow> rows,
                                                        final FuelInfoSpecification specification) {
        final String sowingNorm = extractSowingNorm(specification);
        return findIndexFirstRowByContent(rows, CELL_INDEX_WITH_SOWING_NORM, sowingNorm);
    }

    private static IntPair findIndexBordersRowsMatchingSowingNorm(final int indexFirstMatchingRow,
                                                                  final List<XWPFTableRow> rows) {
        final int nextIndexLastMatchingRow = findIndexRowNextSowingNormOrLastRow(rows, indexFirstMatchingRow);
        return new IntPair(indexFirstMatchingRow, nextIndexLastMatchingRow);
    }

    private static int findIndexRowNextSowingNormOrLastRow(final List<XWPFTableRow> rows,
                                                           final int startSearchingIndex) {
        return findIndexFirstRowByContentRegex(
                rows,
                startSearchingIndex,
                CELL_INDEX_WITH_SOWING_NORM,
                REGEX_CONTENT_SOWING_NORM
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
        //TODO: remove variable, maybe call other method: there is no united rows
        Optional<List<XWPFTableRow>> unitedRowsByContent = findUnitedRowsByContent(
                rows,
                CELL_INDEX_WITH_MACHINERY,
                extractMachinery(specification)
        );
        return unitedRowsByContent;
    }

    private static Optional<XWPFTableRow> findRowByWorkingWidth(final List<XWPFTableRow> rows,
                                                                final FuelInfoSpecification specification) {
        final String workingWidth = extractWorkingWidth(specification);
        Optional<XWPFTableRow> firstRowByContent = findFirstRowByContent(rows, CELL_INDEX_WITH_WORKING_WIDTH, workingWidth);
        return firstRowByContent;
    }
}
