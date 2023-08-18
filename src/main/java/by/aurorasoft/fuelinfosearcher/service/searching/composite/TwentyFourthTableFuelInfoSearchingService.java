package by.aurorasoft.fuelinfosearcher.service.searching.composite;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.filter.FinalRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.filter.StartRowFilter;
import by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Stream;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractRoutingLength;

@Service
public final class TwentyFourthTableFuelInfoSearchingService extends AbstractCompositeTableFuelInfoSearchingService {
    private static final String TABLE_NAME = "ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ";
    private static final String[] FUEL_INFO_HEADERS = new String[]{
            "Менее 150", "151...200", "201...300", "301...400", "401...600", "601...1000", "Более 1000"
    };
    private static final String ELEMENT_TABLE_TITLE_TEMPLATE = "%s. Соотношение массы зерна к массе соломы %s";

    public TwentyFourthTableFuelInfoSearchingService(FuelDocument fuelDocument, String fuelTableName, String[] fuelInfoHeaders) {
        super(fuelDocument, fuelTableName, fuelInfoHeaders);
    }


    @Override
    protected Stream<StartRowFilter> createStartRowFilters() {
        return null;
    }

    @Override
    protected FinalRowFilter createFinalRowFilter() {
        return null;
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
}
