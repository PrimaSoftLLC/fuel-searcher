package by.aurorasoft.fuelinfosearcher.service.searching.simple;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.filter.FinalRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.filter.StartRowFilter;
import by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractRoutingLength;

@Service
public final class TwentySixthTableFuelInfoSearchingService extends AbstractSimpleTableFuelInfoSearchingService {
    private static final String TABLE_NAME = "УБОРКА КУКУРУЗЫ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА";
    private static final String[] FUEL_INFO_HEADERS = new String[]{
            "Менее 150", "151...200", "201...300", "301...400", "401...600", "601...1000", "Более 1000"
    };

    private static final int CELL_INDEX_COMBINE = 1;
    private static final int CELL_INDEX_WORKING_WIDTH = 3;
    private static final int CELL_INDEX_YIELD = 2;

    public TwentySixthTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, FUEL_INFO_HEADERS);
    }

    @Override
    protected Stream<StartRowFilter> createStartRowFilters() {
        return Stream.of(
                TwentySixthTableFuelInfoSearchingService::findRowsByCombine,
                TwentySixthTableFuelInfoSearchingService::findRowsByWorkingWidth
        );
    }

    @Override
    protected FinalRowFilter createFinalRowFilter() {
        return TwentySixthTableFuelInfoSearchingService::findRowByYield;
    }

    @Override
    protected String extractFuelHeaderCellValue(final FuelSpecification specification) {
        return extractRoutingLength(specification);
    }

    private static List<XWPFTableRow> findRowsByCombine(final List<XWPFTableRow> rows,
                                                        final FuelSpecification specification) {
        return FuelDocumentRowFilterUtil.findRowsByCombine(
                rows,
                specification,
                CELL_INDEX_COMBINE
        );
    }

    private static List<XWPFTableRow> findRowsByWorkingWidth(final List<XWPFTableRow> rows,
                                                             final FuelSpecification specification) {
        return FuelDocumentRowFilterUtil.findRowsByWorkingWidth(
                rows,
                specification,
                CELL_INDEX_WORKING_WIDTH
        );
    }

    private static Optional<XWPFTableRow> findRowByYield(final List<XWPFTableRow> rows,
                                                         final FuelSpecification specification) {
        return FuelDocumentRowFilterUtil.findRowByYield(
                rows,
                specification,
                CELL_INDEX_YIELD
        );
    }
}
