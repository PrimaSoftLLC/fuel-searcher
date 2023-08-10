package by.aurorasoft.fuelinfosearcher.service.searching.simple.soiltreatment;

import by.aurorasoft.fuelinfosearcher.model.*;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.AbstractSimpleTableFuelInfoSearchingService;
import by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil.findRowsByMachinery;
import static by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil.findRowsByTractor;
import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.*;
import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.findFirstRowByContent;

public abstract class AbstractSoilTreatmentFuelInfoSearchingService extends AbstractSimpleTableFuelInfoSearchingService {
    private static final int CELL_INDEX_PROCESSING_DEPTH = 0;
    private static final int CELL_INDEX_WORKING_WIDTH = 3;

    private static final String REGEX_CONTENT_PROCESSING_DEPTH = "Глубина обработки \\d+((…)|(...))\\d+ см";

    public AbstractSoilTreatmentFuelInfoSearchingService(final FuelDocument fuelDocument,
                                                         final String fuelTableName,
                                                         final String[] routingLengths,
                                                         final int firstFuelInfoOffset) {
        super(fuelDocument, fuelTableName, routingLengths, firstFuelInfoOffset);
    }

    @Override
    protected final Optional<XWPFTableRow> findAppropriateRow(final List<XWPFTableRow> elementTableRows,
                                                              final FuelInfoSpecification specification) {
        return findRowsByProcessingDepth(elementTableRows, specification)
                .flatMap(rows -> findRowsByTractor(rows, specification))
                .flatMap(rows -> findRowsByMachinery(rows, specification))
                .flatMap(rows -> findRowByWorkingWidth(rows, specification));
    }

    private static Optional<List<XWPFTableRow>> findRowsByProcessingDepth(final List<XWPFTableRow> rows,
                                                                          final FuelInfoSpecification specification) {
        return findIndexRowByProcessingDepth(rows, specification)
                .stream()
                .map(indexRowProcessingDepth -> indexRowProcessingDepth + 1)
                .mapToObj(indexFirstMatchingRow -> findIndexBordersRowsMatchingPloughingDepth(indexFirstMatchingRow, rows))
                .map(borderRowIndexes -> extractRows(rows, borderRowIndexes))
                .findFirst();
    }

    private static OptionalInt findIndexRowByProcessingDepth(final List<XWPFTableRow> rows,
                                                             final FuelInfoSpecification specification) {
        return findIndexFirstRowByContent(
                rows,
                CELL_INDEX_PROCESSING_DEPTH,
                specification,
                FuelInfoSpecificationUtil::extractProcessingDepth
        );
    }

    private static IntPair findIndexBordersRowsMatchingPloughingDepth(final int indexFirstMatchingRow,
                                                                      final List<XWPFTableRow> rows) {
        final int nextIndexLastMatchingRow = findIndexRowNextProcessingDepthOrLastRow(rows, indexFirstMatchingRow);
        return new IntPair(indexFirstMatchingRow, nextIndexLastMatchingRow);
    }

    private static int findIndexRowNextProcessingDepthOrLastRow(final List<XWPFTableRow> rows,
                                                                final int startSearchingIndex) {
        return findIndexFirstRowByContentRegex(
                rows,
                startSearchingIndex,
                CELL_INDEX_PROCESSING_DEPTH,
                REGEX_CONTENT_PROCESSING_DEPTH
        ).orElse(rows.size());
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
