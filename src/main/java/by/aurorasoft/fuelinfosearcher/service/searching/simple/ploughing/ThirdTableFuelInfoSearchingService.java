package by.aurorasoft.fuelinfosearcher.service.searching.simple.ploughing;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil.findRowsBySoilType;

@Service
public final class ThirdTableFuelInfoSearchingService extends AbstractPloughingFuelInfoSearchingService {
    private static final String TABLE_NAME = "ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ";
    private static final int FIRST_FUEL_INFO_OFFSET = 2;

    public ThirdTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME, FIRST_FUEL_INFO_OFFSET);
    }

    @Override
    protected Optional<List<XWPFTableRow>> findRowsByGroupValue(final List<XWPFTableRow> elementTableRows,
                                                                final FuelInfoSpecification specification) {
        return findRowsBySoilType(elementTableRows, specification);
    }
}
