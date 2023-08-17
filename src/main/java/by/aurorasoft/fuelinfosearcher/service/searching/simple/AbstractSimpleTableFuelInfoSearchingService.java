package by.aurorasoft.fuelinfosearcher.service.searching.simple;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.service.searching.AbstractTableFuelSearchingService;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.util.List;
import java.util.Optional;

public abstract class AbstractSimpleTableFuelInfoSearchingService extends AbstractTableFuelSearchingService {

    public AbstractSimpleTableFuelInfoSearchingService(final FuelDocument fuelDocument,
                                                       final String fuelTableName,
                                                       final String[] fuelInfoHeaders) {
        super(fuelDocument, fuelTableName, fuelInfoHeaders);
    }

    @Override
    protected final Optional<XWPFTable> findElementTable(final FuelTable fuelTable,
                                                         final FuelSpecification specification) {
        final List<IBodyElement> elements = fuelTable.getElements();
        final IBodyElement firstElement = elements.get(0);
        final XWPFTable elementTable = (XWPFTable) firstElement;
        return Optional.of(elementTable);
    }

}
