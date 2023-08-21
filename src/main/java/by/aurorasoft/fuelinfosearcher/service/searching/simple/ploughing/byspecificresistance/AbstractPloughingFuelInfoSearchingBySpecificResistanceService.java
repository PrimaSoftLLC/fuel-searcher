package by.aurorasoft.fuelinfosearcher.service.searching.simple.ploughing.byspecificresistance;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.ploughing.AbstractPloughingFuelInfoSearchingService;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

import static by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil.findRowsBySpecificResistance;

public abstract class AbstractPloughingFuelInfoSearchingBySpecificResistanceService
        extends AbstractPloughingFuelInfoSearchingService {

    public AbstractPloughingFuelInfoSearchingBySpecificResistanceService(final FuelDocument fuelDocument,
                                                                         final String fuelTableName) {
        super(fuelDocument, fuelTableName);
    }

    @Override
    protected final List<XWPFTableRow> findRowsByGroupValue(final List<XWPFTableRow> rows,
                                                            final FuelSpecification specification) {
        return findRowsBySpecificResistance(rows, specification);
    }
}
