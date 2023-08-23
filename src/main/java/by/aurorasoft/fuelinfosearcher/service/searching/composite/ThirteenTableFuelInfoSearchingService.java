package by.aurorasoft.fuelinfosearcher.service.searching.composite;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.chain.RowFilterChain;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.conclusive.TransportDistanceRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.group.CargoClassRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.group.RoadGroupRowFilter;
import by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Stream;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractRoutingLength;

@Service
public final class ThirteenTableFuelInfoSearchingService extends AbstractCompositeTableFuelInfoSearchingService {
    private static final String TABLE_NAME = "ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ";
    private static final String[] FUEL_INFO_HEADERS = new String[]{"Менее 30", "31...50", "Более 50"};
    private static final int CELL_INDEX_TRANSPORT_DISTANCE = 1;
    private static final String ELEMENT_TABLE_TITLE_TEMPLATE = "%s с разбрасывателем %s. Производительность погрузчика более 60 т/ч";

    public ThirteenTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, FUEL_INFO_HEADERS);
    }

    @Override
    protected RowFilterChain createRowFilterChain() {
        return RowFilterChain.builder()
                .intermediateFilter(new CargoClassRowFilter())
                .intermediateFilter(new RoadGroupRowFilter())
                .conclusiveFilter(new TransportDistanceRowFilter(CELL_INDEX_TRANSPORT_DISTANCE))
                .build();
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
                FuelSpecificationExtractingPropertyUtil::extractTractor,
                FuelSpecificationExtractingPropertyUtil::extractMachinery
        );
    }
}
