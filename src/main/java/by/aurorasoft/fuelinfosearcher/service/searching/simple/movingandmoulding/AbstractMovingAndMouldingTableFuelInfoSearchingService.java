package by.aurorasoft.fuelinfosearcher.service.searching.simple.movingandmoulding;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.AbstractSimpleTableFuelInfoSearchingService;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;

import static by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil.*;

public abstract class AbstractMovingAndMouldingTableFuelInfoSearchingService extends AbstractSimpleTableFuelInfoSearchingService {

    public AbstractMovingAndMouldingTableFuelInfoSearchingService(final FuelDocument fuelDocument,
                                                                  final String fuelTableName,
                                                                  final String[] routingLengths,
                                                                  final int firstFuelInfoOffset) {
        super(fuelDocument, fuelTableName, routingLengths, firstFuelInfoOffset);
    }

    @Override
    protected final Optional<XWPFTableRow> findAppropriateRow(final List<XWPFTableRow> elementTableRows,
                                                              final FuelInfoSpecification specification) {
        final int cellIndexWorkingWidth = this.findCellIndexWorkingWidth();
        final int cellIndexYield = this.findCellIndexYield();
        return findRowsByTractor(elementTableRows, specification)
                .flatMap(rows -> findRowsByMachinery(rows, specification))
                .flatMap(rows -> findRowsByWorkingWidth(rows, specification, cellIndexWorkingWidth))
                .flatMap(rows -> findRowByYield(rows, specification, cellIndexYield));
    }

    protected abstract int findCellIndexWorkingWidth();

    protected abstract int findCellIndexYield();
}
