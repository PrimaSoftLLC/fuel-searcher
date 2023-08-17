package by.aurorasoft.fuelinfosearcher.service.searching.simple;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractRoutingLength;

@Service
public final class TwentyThirdTableFuelInfoSearchingService extends AbstractSimpleTableFuelInfoSearchingService {
    private static final String TABLE_NAME = "ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА";
    private static final String[] FUEL_INFO_HEADERS = new String[]{
            "Менее 150", "151...200", "201...300", "301...400", "401...600", "601...1000", "Более 1000"
    };

    private static final int CELL_INDEX_MACHINERY = 1;
    private static final int CELL_INDEX_WORKING_WIDTH = 3;
    private static final int CELL_INDEX_YIELD = 2;

    public TwentyThirdTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, FUEL_INFO_HEADERS);
    }

    @Override
    protected Stream<BiFunction<List<XWPFTableRow>, FuelInfoSpecification, List<XWPFTableRow>>> createStartRowFilters() {
        return Stream.of(
                TwentyThirdTableFuelInfoSearchingService::findRowsByMachinery,
                TwentyThirdTableFuelInfoSearchingService::findRowsByWorkingWidth
        );
    }

    @Override
    protected BiFunction<List<XWPFTableRow>, FuelInfoSpecification, Optional<XWPFTableRow>> createFinalRowFilter() {
        return TwentyThirdTableFuelInfoSearchingService::findRowByYield;
    }

    @Override
    protected String extractFuelInfoHeaderCellValue(final FuelInfoSpecification specification) {
        return extractRoutingLength(specification);
    }

    private static List<XWPFTableRow> findRowsByMachinery(final List<XWPFTableRow> rows,
                                                          final FuelInfoSpecification specification) {
        return FuelDocumentRowFilterUtil.findRowsByMachinery(
                rows,
                specification,
                CELL_INDEX_MACHINERY
        );
    }

    private static List<XWPFTableRow> findRowsByWorkingWidth(final List<XWPFTableRow> rows,
                                                             final FuelInfoSpecification specification) {
        return FuelDocumentRowFilterUtil.findRowsByWorkingWidth(
                rows,
                specification,
                CELL_INDEX_WORKING_WIDTH
        );
    }

    private static Optional<XWPFTableRow> findRowByYield(final List<XWPFTableRow> rows,
                                                         final FuelInfoSpecification specification) {
        return FuelDocumentRowFilterUtil.findRowByYield(
                rows,
                specification,
                CELL_INDEX_YIELD
        );
    }

}
