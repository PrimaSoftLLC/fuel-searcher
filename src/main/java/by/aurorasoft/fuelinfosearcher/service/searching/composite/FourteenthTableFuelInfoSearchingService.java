package by.aurorasoft.fuelinfosearcher.service.searching.composite;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil;
import by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractSpreadRate;

@Service
public final class FourteenthTableFuelInfoSearchingService extends AbstractCompositeTableFuelInfoSearchingService {
    private static final String TABLE_NAME = "ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ";
    private static final String[] FUEL_INFO_HEADERS = new String[]{"Менее 30", "31...50", "Более 50"};

    private static final String TEMPLATE_PARAGRAPH_CONTENT_WITH_TRACTOR_AND_MACHINERY = "%s с %s";

    private static final int CELL_INDEX_TRANSPORT_DISTANCE = 1;

    public FourteenthTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, FUEL_INFO_HEADERS);
    }

    @Override
    protected String findElementTableTitleTemplate() {
        return TEMPLATE_PARAGRAPH_CONTENT_WITH_TRACTOR_AND_MACHINERY;
    }

    @Override
    protected Stream<Function<FuelInfoSpecification, String>> findElementTableTitleTemplateArgumentExtractors() {
        return Stream.of(
                FuelInfoSpecificationUtil::extractTractor,
                FuelInfoSpecificationUtil::extractMachinery
        );
    }

    @Override
    protected Stream<BiFunction<List<XWPFTableRow>, FuelInfoSpecification, List<XWPFTableRow>>> createStartRowFilters() {
        return Stream.of(FuelDocumentRowFilterUtil::findRowsByRoadGroup);
    }

    @Override
    protected BiFunction<List<XWPFTableRow>, FuelInfoSpecification, Optional<XWPFTableRow>> createFinalRowFilter() {
        return FourteenthTableFuelInfoSearchingService::findRowByTransportDistance;
    }

    @Override
    protected String extractFuelInfoHeaderCellValue(final FuelInfoSpecification specification) {
        return extractSpreadRate(specification);
    }

    private static Optional<XWPFTableRow> findRowByTransportDistance(final List<XWPFTableRow> rows,
                                                                     final FuelInfoSpecification specification) {
        return FuelDocumentRowFilterUtil.findRowByTransportDistance(
                rows,
                specification,
                CELL_INDEX_TRANSPORT_DISTANCE
        );
    }
}
