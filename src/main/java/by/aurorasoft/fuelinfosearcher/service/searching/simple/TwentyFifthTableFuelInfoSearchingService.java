package by.aurorasoft.fuelinfosearcher.service.searching.simple;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfiltertemp.conclusive.TEMPConclusiveRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfiltertemp.start.StartRowFilter;
import by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractRoutingLength;

@Service
public final class TwentyFifthTableFuelInfoSearchingService extends AbstractSimpleTableFuelInfoSearchingService {
    private static final String TABLE_NAME = "УБОРКА КАРТОФЕЛЯ";
    private static final String[] FUEL_INFO_HEADERS = new String[]{
            "Менее 150", "151...200", "201...300", "301...400", "401...600", "601...1000", "Более 1000"
    };

    private static final int CELL_INDEX_TRACTOR = 1;
    private static final int CELL_INDEX_MACHINERY = 2;
    private static final int CELL_INDEX_ROW_WIDTH = 3;
    private static final int CELL_INDEX_YIELD = 4;


    public TwentyFifthTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, FUEL_INFO_HEADERS);
    }

    @Override
    protected Stream<StartRowFilter> createStartRowFilters() {
        return Stream.of(
                FuelDocumentRowFilterUtil::findRowsBySoilType,
                TwentyFifthTableFuelInfoSearchingService::findRowsByTractor,
                TwentyFifthTableFuelInfoSearchingService::findRowsByMachinery,
                TwentyFifthTableFuelInfoSearchingService::findRowsByRowWidth
        );
    }

    @Override
    protected TEMPConclusiveRowFilter createFinalRowFilter() {
        return TwentyFifthTableFuelInfoSearchingService::findRowByYield;
    }

    @Override
    protected String extractFuelHeaderCellValue(final FuelSpecification specification) {
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

    private static List<XWPFTableRow> findRowsByRowWidth(final List<XWPFTableRow> rows,
                                                         final FuelSpecification specification) {
        return FuelDocumentRowFilterUtil.findRowsByRowWidth(
                rows,
                specification,
                CELL_INDEX_ROW_WIDTH
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
