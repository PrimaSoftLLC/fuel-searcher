package by.aurorasoft.fuelinfosearcher.service.searching.composite;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil.*;
import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractRoutingLength;

@Service
public final class TwelfthTableFuelInfoSearchingService extends AbstractCompositeTableFuelInfoSearchingService {
    private static final String TABLE_NAME = "ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ";
    private static final String[] FUEL_INFO_HEADERS = new String[]{
            "Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"
    };
    private static final String TEMPLATE_PARAGRAPH_CONTENT_WITH_MACHINERY = "Опрыскивателем %s";

    private static final int CELL_INDEX_CHARGING_METHOD_AND_TRANSPORT_DISTANCE = 1;
    private static final int CELL_INDEX_SPREAD_RATE = 2;

    public TwelfthTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, FUEL_INFO_HEADERS);
    }

    @Override
    protected Optional<XWPFTableRow> findAppropriateRow(final List<XWPFTableRow> elementTableRows, final FuelInfoSpecification specification) {
        return findRowsByFertilizerType(elementTableRows, specification)
                .flatMap(rows -> findRowsByChargingMethodAndTransportDistance(rows, specification, CELL_INDEX_CHARGING_METHOD_AND_TRANSPORT_DISTANCE))
                .flatMap(rows -> findRowBySpreadRate(rows, specification, CELL_INDEX_SPREAD_RATE));
    }

    @Override
    protected String extractFuelInfoHeaderCellValue(final FuelInfoSpecification specification) {
        return extractRoutingLength(specification);
    }

    @Override
    protected String findElementTableTitleTemplate() {
        return TEMPLATE_PARAGRAPH_CONTENT_WITH_MACHINERY;
    }

    @Override
    protected Stream<Function<FuelInfoSpecification, String>> findElementTableTitleTemplateArgumentExtractors() {
        return Stream.of(
                FuelInfoSpecificationUtil::extractMachinery
        );
    }
}
