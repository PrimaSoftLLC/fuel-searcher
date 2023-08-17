package by.aurorasoft.fuelinfosearcher.service.searching.composite;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.filter.FinalRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.filter.StartRowFilter;
import by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil;
import by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractRoutingLength;

@Service
public final class ThirteenTableFuelInfoSearchingService extends AbstractCompositeTableFuelInfoSearchingService {
    private static final String TABLE_NAME = "ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ";
    private static final String[] FUEL_INFO_HEADERS = new String[]{"Менее 30", "31...50", "Более 50"};
    private static final int CELL_INDEX_TRANSPORT_DISTANCE = 1;
    private static final String ELEMENT_TABLE_TITLE_TEMPLATE = "%s с разбрасывателем %s Производительность погрузчика более 60 т/ч";

    public ThirteenTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, FUEL_INFO_HEADERS);
    }

    @Override
    protected Stream<StartRowFilter> createStartRowFilters() {
        return Stream.of(
                FuelDocumentRowFilterUtil::findRowsByCargoClass,
                FuelDocumentRowFilterUtil::findRowsByRoadGroup
        );
    }

    @Override
    protected FinalRowFilter createFinalRowFilter() {
        return ThirteenTableFuelInfoSearchingService::findRowByTransportDistance;
    }

    @Override
    protected String extractFuelHeaderCellValue(final FuelSpecification specification) {
        return extractRoutingLength(specification);
    }

    @Override
    protected String findElementTableTitleTemplate() {
        return ELEMENT_TABLE_TITLE_TEMPLATE;
    }

    @Override
    protected Stream<Function<FuelSpecification, String>> findElementTableTitleTemplateArgumentExtractors() {
        return Stream.of(
                FuelInfoSpecificationUtil::extractTractor,
                FuelInfoSpecificationUtil::extractMachinery
        );
    }

    private static Optional<XWPFTableRow> findRowByTransportDistance(final List<XWPFTableRow> rows,
                                                                     final FuelSpecification specification) {
        return FuelDocumentRowFilterUtil.findRowByTransportDistance(
                rows,
                specification,
                CELL_INDEX_TRANSPORT_DISTANCE
        );
    }
}
