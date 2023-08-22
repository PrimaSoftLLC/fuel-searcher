package by.aurorasoft.fuelinfosearcher.service.searching.composite;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfiltertemp.conclusive.TEMPConclusiveRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfiltertemp.start.StartRowFilter;
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
public final class TwentyFourthTableFuelInfoSearchingService extends AbstractCompositeTableFuelInfoSearchingService {
    private static final String TABLE_NAME = "ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ";
    private static final String[] FUEL_INFO_HEADERS = new String[]{
            "Менее 150", "151...200", "201...300", "301...400", "401...600", "601...1000", "Более 1000"
    };
    private static final String ELEMENT_TABLE_TITLE_TEMPLATE = "%s Соотношение массы зерна к массе соломы %s";

    private static final int CELL_INDEX_WORKING_WIDTH = 2;
    private static final int CELL_INDEX_YIELD = 1;

    public TwentyFourthTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, FUEL_INFO_HEADERS);
    }


    @Override
    protected Stream<StartRowFilter> createStartRowFilters() {
        return Stream.of(
                TwentyFourthTableFuelInfoSearchingService::findRowsByWorkingWidth
        );
    }

    @Override
    protected TEMPConclusiveRowFilter createFinalRowFilter() {
        return TwentyFourthTableFuelInfoSearchingService::findRowByYield;
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
                FuelInfoSpecificationUtil::extractCombine,
                FuelInfoSpecificationUtil::extractWeightRatioGrainToStraw
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
