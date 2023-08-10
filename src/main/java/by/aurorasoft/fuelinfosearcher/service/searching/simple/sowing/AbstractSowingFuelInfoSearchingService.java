package by.aurorasoft.fuelinfosearcher.service.searching.simple.sowing;

import by.aurorasoft.fuelinfosearcher.model.*;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.AbstractSimpleTableFuelInfoSearchingService;
import by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.*;

public abstract class AbstractSowingFuelInfoSearchingService extends AbstractSimpleTableFuelInfoSearchingService {
    private static final int CELL_INDEX_SOWING_NORM = 0;
    private static final int CELL_INDEX_TRACTOR = 1;
    private static final int CELL_INDEX_MACHINERY = 2;
    private static final int CELL_INDEX_WORKING_WIDTH = 3;

    private static final String REGEX_CONTENT_SOWING_NORM = "Норма высева (семян )?\\d+(–\\d+)? кг/га";

    public AbstractSowingFuelInfoSearchingService(final FuelDocument fuelDocument,
                                                  final String fuelTableName,
                                                  final String[] routingLengths,
                                                  final int firstFuelInfoOffset) {
        super(fuelDocument, fuelTableName, routingLengths, firstFuelInfoOffset);
    }

    @Override
    protected final Optional<XWPFTableRow> findAppropriateRow(final List<XWPFTableRow> elementTableRows,
                                                              final FuelInfoSpecification specification) {
        return findRowsBySowingNorm(elementTableRows, specification)
                .flatMap(rows -> findRowsByTractor(rows, specification))
                .flatMap(rows -> findRowsByMachinery(rows, specification))
                .flatMap(rows -> findRowByWorkingWidth(rows, specification));
    }

    private static Optional<List<XWPFTableRow>> findRowsBySowingNorm(final List<XWPFTableRow> rows,
                                                                     final FuelInfoSpecification specification) {
        return findIndexRowBySowingNorm(rows, specification)
                .stream()
                .map(indexRowSowingNorm -> indexRowSowingNorm + 1)
                .mapToObj(indexFirstMatchingRow -> findIndexBordersRowsMatchingSowingNorm(indexFirstMatchingRow, rows))
                .map(borderRowIndexes -> extractRows(rows, borderRowIndexes))
                .findFirst();
    }

    private static OptionalInt findIndexRowBySowingNorm(final List<XWPFTableRow> rows,
                                                        final FuelInfoSpecification specification) {
        return findIndexFirstRowByContent(
                rows,
                CELL_INDEX_SOWING_NORM,
                specification,
                FuelInfoSpecificationUtil::extractSowingNorm
        );
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
                CELL_INDEX_SOWING_NORM,
                REGEX_CONTENT_SOWING_NORM
        ).orElse(rows.size());
    }

    private static Optional<List<XWPFTableRow>> findRowsByTractor(final List<XWPFTableRow> rows,
                                                                  final FuelInfoSpecification specification) {
        return findUnitedRowsByContent(
                rows,
                CELL_INDEX_TRACTOR,
                specification,
                FuelInfoSpecificationUtil::extractTractor
        );
    }

    private static Optional<List<XWPFTableRow>> findRowsByMachinery(final List<XWPFTableRow> rows,
                                                                    final FuelInfoSpecification specification) {
        return findRowsByContent(
                rows,
                CELL_INDEX_MACHINERY,
                specification,
                FuelInfoSpecificationUtil::extractMachinery
        );
    }

    private static Optional<XWPFTableRow> findRowByWorkingWidth(final List<XWPFTableRow> rows,
                                                                final FuelInfoSpecification specification) {
        return findFirstRowByContent(
                rows,
                CELL_INDEX_WORKING_WIDTH,
                specification,
                FuelInfoSpecificationUtil::extractWorkingWidth
        );
    }
}
