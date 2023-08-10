package by.aurorasoft.fuelinfosearcher.service.searching.simple.ploughing.byspecificresistance;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.ploughing.AbstractPloughingFuelInfoSearchingService;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;

import static by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil.findRowsBySpecificResistance;

public abstract class AbstractPloughingFuelInfoSearchingBySpecificResistanceService
        extends AbstractPloughingFuelInfoSearchingService {

    public AbstractPloughingFuelInfoSearchingBySpecificResistanceService(final FuelDocument fuelDocument,
                                                                         final String fuelTableName,
                                                                         final String[] routingLengths,
                                                                         final int firstFuelInfoOffset) {
        super(fuelDocument, fuelTableName, routingLengths, firstFuelInfoOffset);
    }

    @Override
    protected final Optional<List<XWPFTableRow>> findRowsByGroupValue(final List<XWPFTableRow> elementTableRows,
                                                                      final FuelInfoSpecification specification) {
        return findRowsBySpecificResistance(elementTableRows, specification);
    }
}
