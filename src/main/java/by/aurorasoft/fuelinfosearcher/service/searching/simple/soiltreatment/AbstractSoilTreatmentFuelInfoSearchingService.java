package by.aurorasoft.fuelinfosearcher.service.searching.simple.soiltreatment;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.filter.FinalRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.filter.StartRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.AbstractSimpleTableFuelInfoSearchingService;
import by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractRoutingLength;

public abstract class AbstractSoilTreatmentFuelInfoSearchingService extends AbstractSimpleTableFuelInfoSearchingService {
    private static final String[] FUEL_INFO_HEADERS = new String[]{
            "Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"
    };

    private static final int CELL_INDEX_TRACTOR = 1;
    private static final int CELL_INDEX_MACHINERY = 2;
    private static final int CELL_INDEX_WORKING_WIDTH = 3;

    public AbstractSoilTreatmentFuelInfoSearchingService(final FuelDocument fuelDocument, final String fuelTableName) {
        super(fuelDocument, fuelTableName, FUEL_INFO_HEADERS);
    }

    @Override
    protected final Stream<StartRowFilter> createStartRowFilters() {
        return Stream.of(
                FuelDocumentRowFilterUtil::findRowsByProcessingDepth,
                AbstractSoilTreatmentFuelInfoSearchingService::findRowsByTractor,
                AbstractSoilTreatmentFuelInfoSearchingService::findRowsByMachinery
        );
    }

    @Override
    protected final FinalRowFilter createFinalRowFilter() {
        return AbstractSoilTreatmentFuelInfoSearchingService::findRowByWorkingWidth;
    }

    @Override
    protected final String extractFuelHeaderCellValue(final FuelSpecification specification) {
        return extractRoutingLength(specification);
    }

    private static List<XWPFTableRow> findRowsByTractor(final List<XWPFTableRow> rows,
                                                        final FuelSpecification specification) {
        return FuelDocumentRowFilterUtil.findRowsByTractor(
                rows,
                specification,
                CELL_INDEX_TRACTOR
        );
    }

    private static List<XWPFTableRow> findRowsByMachinery(final List<XWPFTableRow> rows,
                                                          final FuelSpecification specification) {
        return FuelDocumentRowFilterUtil.findRowsByMachinery(
                rows,
                specification,
                CELL_INDEX_MACHINERY
        );
    }

    private static Optional<XWPFTableRow> findRowByWorkingWidth(final List<XWPFTableRow> rows,
                                                                final FuelSpecification specification) {
        return FuelDocumentRowFilterUtil.findRowByWorkingWidth(
                rows,
                specification,
                CELL_INDEX_WORKING_WIDTH
        );
    }
}
