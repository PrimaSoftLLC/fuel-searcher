package by.aurorasoft.fuelinfosearcher.service.searching.simple.soiltreatment;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.AbstractSimpleTableFuelInfoSearchingService;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;

import static by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil.*;

public abstract class AbstractSoilTreatmentFuelInfoSearchingService extends AbstractSimpleTableFuelInfoSearchingService {
    private static final int CELL_INDEX_MACHINERY = 2;

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
                .flatMap(rows -> findRowsByMachinery(rows, specification, CELL_INDEX_MACHINERY))
                .flatMap(rows -> findRowByWorkingWidth(rows, specification));
    }
}