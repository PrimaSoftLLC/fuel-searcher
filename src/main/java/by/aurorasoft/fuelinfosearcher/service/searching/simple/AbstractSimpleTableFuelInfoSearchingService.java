package by.aurorasoft.fuelinfosearcher.service.searching.simple;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.service.searching.AbstractTableFuelInfoSearchingService;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.util.List;
import java.util.Optional;

public abstract class AbstractSimpleTableFuelInfoSearchingService extends AbstractTableFuelInfoSearchingService {


    public AbstractSimpleTableFuelInfoSearchingService(final FuelDocument fuelDocument,
                                                       final String fuelTableName,
                                                       final String[] routingLengths,
                                                       final int firstFuelInfoOffset) {
        super(fuelDocument, fuelTableName, routingLengths, firstFuelInfoOffset);
    }

    @Override
    protected final Optional<XWPFTable> findElementTable(final FuelTable fuelTable,
                                                         final FuelInfoSpecification specification) {
        final List<IBodyElement> elements = fuelTable.getElements();
        final IBodyElement firstElement = elements.get(0);
        final XWPFTable elementTable = (XWPFTable) firstElement;
        return Optional.of(elementTable);
    }

}
