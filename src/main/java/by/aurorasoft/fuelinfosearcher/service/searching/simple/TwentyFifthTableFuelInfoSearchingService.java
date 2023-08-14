package by.aurorasoft.fuelinfosearcher.service.searching.simple;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil.*;
import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractRoutingLength;

@Service
public final class TwentyFifthTableFuelInfoSearchingService extends AbstractSimpleTableFuelInfoSearchingService {
    private static final String TABLE_NAME = "УБОРКА КАРТОФЕЛЯ";
    private static final String[] FUEL_INFO_HEADERS = new String[]{
            "Менее 150", "151…200", "201…300", "301…400", "401…600", "601…1000", "Более 1000"
    };
    private static final int FIRST_FUEL_INFO_OFFSET = 1;

    private static final int CELL_INDEX_MACHINERY = 2;
    private static final int CELL_INDEX_YIELD = 4;


    public TwentyFifthTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, FUEL_INFO_HEADERS, FIRST_FUEL_INFO_OFFSET);
    }

    @Override
    protected Optional<XWPFTableRow> findAppropriateRow(final List<XWPFTableRow> elementTableRows,
                                                        final FuelInfoSpecification specification) {
        return findRowsBySoilType(elementTableRows, specification)
                .flatMap(rows -> findRowsByTractor(rows, specification))
                .flatMap(rows -> findRowsByMachinery(rows, specification, CELL_INDEX_MACHINERY))
                .flatMap(rows -> findRowsByRowWidth(rows, specification))
                .flatMap(rows -> findRowByYield(rows, specification, CELL_INDEX_YIELD));
    }

    @Override
    protected String extractFuelInfoHeaderCellValue(final FuelInfoSpecification specification) {
        return extractRoutingLength(specification);
    }
}
