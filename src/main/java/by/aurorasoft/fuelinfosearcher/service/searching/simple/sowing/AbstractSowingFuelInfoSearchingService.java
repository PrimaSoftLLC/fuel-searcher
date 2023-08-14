package by.aurorasoft.fuelinfosearcher.service.searching.simple.sowing;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.AbstractSimpleTableFuelInfoSearchingService;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;

import static by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil.*;
import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractRoutingLength;

public abstract class AbstractSowingFuelInfoSearchingService extends AbstractSimpleTableFuelInfoSearchingService {
    private static final String[] FUEL_INFO_HEADERS = new String[]{
            "Менее 150", "150–200", "201–300", "301–400", "401–600", "601–1000", "Более 1000"
    };
    private static final int CELL_INDEX_MACHINERY = 2;

    public AbstractSowingFuelInfoSearchingService(final FuelDocument fuelDocument,
                                                  final String fuelTableName,
                                                  final int firstFuelInfoOffset) {
        super(fuelDocument, fuelTableName, FUEL_INFO_HEADERS, firstFuelInfoOffset);
    }

    @Override
    protected final Optional<XWPFTableRow> findAppropriateRow(final List<XWPFTableRow> elementTableRows,
                                                              final FuelInfoSpecification specification) {
        return findRowsBySowingNorm(elementTableRows, specification)
                .flatMap(rows -> findRowsByTractor(rows, specification))
                .flatMap(rows -> findRowsByMachinery(rows, specification, CELL_INDEX_MACHINERY))
                .flatMap(rows -> findRowByWorkingWidth(rows, specification));
    }

    @Override
    protected final String extractFuelInfoHeaderCellValue(final FuelInfoSpecification specification) {
        return extractRoutingLength(specification);
    }

}
