package by.aurorasoft.fuelinfosearcher.service.searching.composite;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil;
import by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.Stream;

import static by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil.findRowByTransportDistance;

@Service
public final class TwentySeventhTableFuelInfoSearchingService extends AbstractCompositeTableFuelInfoSearchingService {
    private static final String TABLE_NAME = "ТРАНСПОРТИРОВКА ГРУЗОВ ТРАКТОРАМИ С ОДНИМ ПРИЦЕПОМ";

    //TODO: rename here and in all classes
    private static final String[] ROUTING_LENGTHS = new String[]{"I", "II", "III"};
    private static final int FIRST_FUEL_INFO_OFFSET = 0;

    private static final String TEMPLATE_PARAGRAPH_CONTENT_WITH_TRACTOR_AND_MACHINERY = "ТРАКТОР %s + %s. При механизированной погрузке и разгрузке";

    public TwentySeventhTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, ROUTING_LENGTHS, FIRST_FUEL_INFO_OFFSET);
    }

    @Override
    protected Optional<XWPFTableRow> findAppropriateRow(final List<XWPFTableRow> elementTableRows,
                                                        final FuelInfoSpecification specification) {
        return findRowByTransportDistance(elementTableRows, specification);
    }

    @Override
    protected OptionalInt findFuelInfoHeaderCellIndex(final FuelInfoSpecification specification) {
        return null;
    }

    @Override
    protected String findElementTableTitleTemplate() {
        return TEMPLATE_PARAGRAPH_CONTENT_WITH_TRACTOR_AND_MACHINERY;
    }

    @Override
    protected Stream<Function<FuelInfoSpecification, String>> findElementTableTitleTemplateArgumentExtractors() {
        return Stream.of(
                FuelInfoSpecificationUtil::extractTractor,
                FuelInfoSpecificationUtil::extractMachinery
        );
    }
}
