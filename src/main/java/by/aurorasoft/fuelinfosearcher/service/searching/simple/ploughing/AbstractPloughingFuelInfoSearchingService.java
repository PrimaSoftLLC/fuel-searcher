package by.aurorasoft.fuelinfosearcher.service.searching.simple.ploughing;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.simple.AbstractSimpleTableFuelInfoSearchingService;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;

import static by.aurorasoft.fuelinfosearcher.util.FuelDocumentRowFilterUtil.*;

public abstract class AbstractPloughingFuelInfoSearchingService extends AbstractSimpleTableFuelInfoSearchingService {

    public AbstractPloughingFuelInfoSearchingService(final FuelDocument fuelDocument,
                                                     final String fuelTableName,
                                                     final String[] routingLengths,
                                                     final int firstFuelInfoOffset) {
        super(fuelDocument, fuelTableName, routingLengths, firstFuelInfoOffset);
    }

    @Override
    protected final Optional<XWPFTableRow> findAppropriateRow(final List<XWPFTableRow> elementTableRows,
                                                              final FuelInfoSpecification specification) {
        final String groupValue = this.extractGroupValue(specification);
        final String groupValueRegex = this.findGroupValueRegex();
        return findRowsByGroupValue(elementTableRows, groupValue, groupValueRegex)
                .flatMap(rows -> findRowsByTractor(rows, specification))
                .flatMap(rows -> findRowsByMachinery(rows, specification))
                .flatMap(rows -> findRowsByCorpusCount(rows, specification))
                .flatMap(rows -> findRowByPloughingDepth(rows, specification));
    }

    protected abstract String extractGroupValue(final FuelInfoSpecification specification);

    protected abstract String findGroupValueRegex();
}
