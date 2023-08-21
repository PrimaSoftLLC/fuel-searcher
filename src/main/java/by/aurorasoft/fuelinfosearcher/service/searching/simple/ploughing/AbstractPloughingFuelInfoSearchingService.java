package by.aurorasoft.fuelinfosearcher.service.searching.simple.ploughing;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.filter.FinalRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.filter.StartRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.AbstractSimpleTableFuelInfoSearchingService;
import by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
    protected final Stream<StartRowFilter> createStartRowFilters() {
        return Stream.of(
                this::findRowsByGroupValue,
                AbstractPloughingFuelInfoSearchingService::findRowsByTractor,
                AbstractPloughingFuelInfoSearchingService::findRowsByMachinery,
                AbstractPloughingFuelInfoSearchingService::findRowsByCorpusCount
        );
    }

    @Override
    protected final FinalRowFilter createFinalRowFilter() {
        return AbstractPloughingFuelInfoSearchingService::findRowByPloughingDepth;
    }

    @Override
    protected final String extractFuelHeaderCellValue(final FuelInfoSpecification specification) {
        return extractRoutingLength(specification);
    }

    protected abstract List<XWPFTableRow> findRowsByGroupValue(final List<XWPFTableRow> rows,
                                                               final FuelInfoSpecification specification);

    private static List<XWPFTableRow> findRowsByTractor(final List<XWPFTableRow> rows,
                                                        final FuelInfoSpecification specification) {
        return FuelDocumentRowFilterUtil.findRowsByTractor(
                rows,
                specification,
                CELL_INDEX_TRACTOR
        );
    }

    private static List<XWPFTableRow> findRowsByMachinery(final List<XWPFTableRow> rows,
                                                          final FuelInfoSpecification specification) {
        return FuelDocumentRowFilterUtil.findRowsByMachinery(
                rows,
                specification,
                CELL_INDEX_MACHINERY
        );
    }

    private static List<XWPFTableRow> findRowsByCorpusCount(final List<XWPFTableRow> rows,
                                                            final FuelInfoSpecification specification) {
        return FuelDocumentRowFilterUtil.findRowsByCorpusCount(
                rows,
                specification,
                CELL_INDEX_CORPUS_COUNT
        );
    }

    private static Optional<XWPFTableRow> findRowByPloughingDepth(final List<XWPFTableRow> rows,
                                                                  final FuelInfoSpecification specification) {
        return FuelDocumentRowFilterUtil.findRowByPloughingDepth(
                rows,
                specification,
                CELL_INDEX_PLOUGHING_DEPTH
        );
    }
}
