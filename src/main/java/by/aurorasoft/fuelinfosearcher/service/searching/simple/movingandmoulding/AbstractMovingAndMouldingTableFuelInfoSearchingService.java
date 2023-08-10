package by.aurorasoft.fuelinfosearcher.service.searching.simple.movingandmoulding;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.AbstractSimpleTableFuelInfoSearchingService;
import by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;

import static by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil.findRowsByTractor;
import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.findFirstRowByContent;
import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.findUnitedRowsByContent;

public abstract class AbstractMovingAndMouldingTableFuelInfoSearchingService extends AbstractSimpleTableFuelInfoSearchingService {
    private static final int CELL_INDEX_MACHINERY = 2;

    public AbstractMovingAndMouldingTableFuelInfoSearchingService(final FuelDocument fuelDocument,
                                                                  final String fuelTableName,
                                                                  final String[] routingLengths,
                                                                  final int firstFuelInfoOffset) {
        super(fuelDocument, fuelTableName, routingLengths, firstFuelInfoOffset);
    }

    @Override
    protected final Optional<XWPFTableRow> findAppropriateRow(final List<XWPFTableRow> elementTableRows,
                                                              final FuelInfoSpecification specification) {
        return findRowsByTractor(elementTableRows, specification)
                .flatMap(rows -> findRowsByMachinery(rows, specification))
                .flatMap(rows -> findRowsByWorkingWidth(rows, specification))
                .flatMap(rows -> findRowByYield(rows, specification));
    }

    protected abstract int findIndexCellOfWorkingWidth();

    protected abstract int findIndexCellOfYield();

    private static Optional<List<XWPFTableRow>> findRowsByMachinery(final List<XWPFTableRow> rows,
                                                                    final FuelInfoSpecification specification) {
        return findUnitedRowsByContent(
                rows,
                CELL_INDEX_MACHINERY,
                specification,
                FuelInfoSpecificationUtil::extractMachinery
        );
    }

    private Optional<List<XWPFTableRow>> findRowsByWorkingWidth(final List<XWPFTableRow> rows,
                                                                final FuelInfoSpecification specification) {
        return findUnitedRowsByContent(
                rows,
                this.findIndexCellOfWorkingWidth(),
                specification,
                FuelInfoSpecificationUtil::extractWorkingWidth
        );
    }

    private Optional<XWPFTableRow> findRowByYield(final List<XWPFTableRow> rows,
                                                  final FuelInfoSpecification specification) {
        return findFirstRowByContent(
                rows,
                this.findIndexCellOfYield(),
                specification,
                FuelInfoSpecificationUtil::extractYield
        );
    }
}
