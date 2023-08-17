package by.aurorasoft.fuelinfosearcher.service.searching.composite;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.filter.FinalRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.filter.StartRowFilter;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Stream;

@Service
public final class ThirteenTableFuelInfoSearchingService extends AbstractCompositeTableFuelInfoSearchingService {
    private static final String TABLE_NAME = "ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ";
    private static final String[] FUEL_INFO_HEADERS = new String[]{"Менее 30", "31...50", "Более 50"};

    public ThirteenTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, FUEL_INFO_HEADERS);
    }

    @Override
    protected Stream<StartRowFilter> createStartRowFilters() {
        return Stream.of(
                FuelDocumentRowFilterUtil::
        );
    }

    @Override
    protected FinalRowFilter createFinalRowFilter() {
        return null;
    }

    @Override
    protected String extractFuelHeaderCellValue(final FuelSpecification specification) {
        return null;
    }

    @Override
    protected String findElementTableTitleTemplate() {
        return null;
    }

    @Override
    protected Stream<Function<FuelSpecification, String>> findElementTableTitleTemplateArgumentExtractors() {
        return null;
    }
}
