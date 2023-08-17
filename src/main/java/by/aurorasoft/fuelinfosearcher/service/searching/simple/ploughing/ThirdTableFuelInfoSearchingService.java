package by.aurorasoft.fuelinfosearcher.service.searching.simple.ploughing;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Service;

import java.util.List;

import static by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil.findRowsBySoilType;

@Service
public final class ThirdTableFuelInfoSearchingService extends AbstractPloughingFuelInfoSearchingService {
    private static final String TABLE_NAME = "ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ";

    public ThirdTableFuelInfoSearchingService(final FuelDocument fuelDocument) {
        super(fuelDocument, TABLE_NAME);
    }

    @Override
    protected List<XWPFTableRow> findRowsByGroupValue(final List<XWPFTableRow> rows,
                                                      final FuelSpecification specification) {
        return findRowsBySoilType(rows, specification);
    }
}
