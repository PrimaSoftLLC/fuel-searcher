package by.aurorasoft.fuelinfosearcher.service.searching.composite;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil.*;

@Service
public final class EleventhTableFuelInfoSearchingService extends AbstractCompositeTableFuelInfoSearchingService {
    private static final String TABLE_NAME = "ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ";
    private static final String[] ROUTING_LENGTHS = new String[]{
            "Менее 150", "150–200", "201–300", "301–400", "401–600", "601–1000", "Более 1000"
    };
    private static final int FIRST_FUEL_INFO_OFFSET = 0;

    private static final String TEMPLATE_PARAGRAPH_CONTENT_MACHINERY_AND_TRACTOR = "РАЗБРАСЫВАТЕЛЕМ %s (трактор %s)";


    public EleventhTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, ROUTING_LENGTHS, FIRST_FUEL_INFO_OFFSET);
    }

    @Override
    protected Optional<XWPFTableRow> findAppropriateRow(final List<XWPFTableRow> elementTableRows,
                                                        final FuelInfoSpecification specification) {
        return findRowsByFertilizerType(elementTableRows, specification)
                .flatMap(rows -> findRowsByChargingMethodAndTransportDistance(rows, specification))
                .flatMap(rows -> findRowBySpreadRate(rows, specification));
    }

    @Override
    protected String findElementTableTitleTemplate() {
        return TEMPLATE_PARAGRAPH_CONTENT_MACHINERY_AND_TRACTOR;
    }

    @Override
    protected Stream<Function<FuelInfoSpecification, String>> findElementTableTitleTemplateArgumentExtractors() {
        return Stream.of(
                FuelInfoSpecificationUtil::extractMachinery,
                FuelInfoSpecificationUtil::extractTractor
        );
    }
}
