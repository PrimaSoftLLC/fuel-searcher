package by.aurorasoft.fuelinfosearcher.service.searching.simple.ploughing;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.AbstractSimpleTableFuelInfoSearchingService;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;

import static by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil.*;
import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractRoutingLength;

public abstract class AbstractPloughingFuelInfoSearchingService extends AbstractSimpleTableFuelInfoSearchingService {
    private static final String[] FUEL_INFO_HEADERS = new String[]{
            "Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"
    };

    private static final int CELL_INDEX_TRACTOR = 1;
    private static final int CELL_INDEX_MACHINERY = 2;
    private static final int CELL_INDEX_CORPUS_COUNT = 3;
    private static final int CELL_INDEX_PLOUGHING_DEPTH = 4;

    public AbstractPloughingFuelInfoSearchingService(final FuelDocument fuelDocument, final String fuelTableName) {
        super(fuelDocument, fuelTableName, FUEL_INFO_HEADERS);
    }

    @Override
    protected final Optional<XWPFTableRow> findAppropriateRow(final List<XWPFTableRow> elementTableRows,
                                                              final FuelInfoSpecification specification) {
        return this.findRowsByGroupValue(elementTableRows, specification)
                .flatMap(rows -> findRowsByTractor(rows, specification, CELL_INDEX_TRACTOR))
                .flatMap(rows -> findRowsByMachinery(rows, specification, CELL_INDEX_MACHINERY))
                .flatMap(rows -> findRowsByCorpusCount(rows, specification, CELL_INDEX_CORPUS_COUNT))
                .flatMap(rows -> findRowByPloughingDepth(rows, specification, CELL_INDEX_PLOUGHING_DEPTH));
    }

    @Override
    protected final String extractFuelInfoHeaderCellValue(final FuelInfoSpecification specification) {
        return extractRoutingLength(specification);
    }

    protected abstract Optional<List<XWPFTableRow>> findRowsByGroupValue(final List<XWPFTableRow> elementTableRows,
                                                                         final FuelInfoSpecification specification);
}
