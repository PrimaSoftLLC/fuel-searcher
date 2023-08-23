package by.aurorasoft.fuelinfosearcher.service.searching.composite;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.chain.RowFilterChain;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.conclusive.TransportDistanceRowFilter;
import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate.group.CargoClassRowFilter;
import by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Stream;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractRoadGroup;

@Service
public final class TwentySeventhTableFuelInfoSearchingService extends AbstractCompositeTableFuelInfoSearchingService {
    private static final String TABLE_NAME = "ТРАНСПОРТИРОВКА ГРУЗОВ ТРАКТОРАМИ С ОДНИМ ПРИЦЕПОМ";
    private static final String[] FUEL_INFO_HEADERS = new String[]{"I", "II", "III"};
    private static final String TEMPLATE_PARAGRAPH_CONTENT_WITH_TRACTOR_AND_MACHINERY = "ТРАКТОР %s + %s. При механизированной погрузке и разгрузке";

    private static final int CELL_INDEX_TRANSPORT_DISTANCE = 1;

    public TwentySeventhTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, FUEL_INFO_HEADERS);
    }

    @Override
    protected RowFilterChain createRowFilterChain() {
        return RowFilterChain.builder()
                .intermediateFilter(new CargoClassRowFilter())
                .conclusiveFilter(new TransportDistanceRowFilter(CELL_INDEX_TRANSPORT_DISTANCE))
                .build();
    }

    @Override
    protected String extractFuelHeaderCellValue(final FuelSpecification specification) {
        return extractRoadGroup(specification);
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
}
