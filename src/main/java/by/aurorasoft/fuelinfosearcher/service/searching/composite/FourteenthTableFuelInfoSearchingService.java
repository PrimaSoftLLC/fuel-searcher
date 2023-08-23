package by.aurorasoft.fuelinfosearcher.service.searching.composite;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.chain.RowFilterChain;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.conclusive.TransportDistanceRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.group.RoadGroupRowFilter;
import by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Stream;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractSpreadRate;

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
    protected Stream<Function<FuelSpecification, String>> findElementTableTitleTemplateArgumentExtractors() {
        return Stream.of(
                FuelSpecificationExtractingPropertyUtil::extractTractor,
                FuelSpecificationExtractingPropertyUtil::extractMachinery
        );
    }

    @Override
    protected RowFilterChain createRowFilterChain() {
        return RowFilterChain.builder()
                .intermediateFilter(new RoadGroupRowFilter())
                .conclusiveFilter(new TransportDistanceRowFilter(CELL_INDEX_TRANSPORT_DISTANCE))
                .build();
    }

    @Override
    protected String extractFuelHeaderCellValue(final FuelSpecification specification) {
        return extractSpreadRate(specification);
    }
}
