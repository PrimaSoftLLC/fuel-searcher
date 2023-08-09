package by.aurorasoft.fuelinfosearcher.service.searching;

import by.aurorasoft.fuelinfosearcher.model.*;
import by.aurorasoft.fuelinfosearcher.service.searching.exception.FuelTableNotExistException;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractRoutingLength;

public abstract class AbstractTableFuelInfoSearchingService {
    private final FuelInfoOffsetFromRoutingLengthStorage offsetStorage;
    private final FuelTable fuelTable;

    public AbstractTableFuelInfoSearchingService(final FuelInfoOffsetFromRoutingLengthStorage offsetStorage,
                                                 final FuelDocument fuelDocument,
                                                 final String fuelTableName) {
        this.offsetStorage = offsetStorage;
        this.fuelTable = findTableByName(fuelDocument, fuelTableName);
    }

    public final Optional<FuelInfo> find(final FuelInfoSpecification specification) {
        return this.findElementTable(this.fuelTable, specification)
                .map(XWPFTable::getRows)
                .flatMap(elementTableRows -> this.find(elementTableRows, specification));
    }

    public final String findTableName() {
        return this.fuelTable.getName();
    }

    protected abstract Optional<XWPFTable> findElementTable(final FuelTable fuelTable,
                                                            final FuelInfoSpecification specification);
    protected abstract Optional<FuelInfo> find(final List<XWPFTableRow> elementTableRows,
                                               final FuelInfoSpecification specification);

    protected final int findFuelInfoOffset(final FuelInfoSpecification specification) {
        final String tableName = this.fuelTable.getName();
        final String routingLength = extractRoutingLength(specification);
        return this.offsetStorage.findOffset(tableName, routingLength);
    }

    private static FuelTable findTableByName(final FuelDocument fuelDocument, final String fuelTableName) {
        return fuelDocument.getTables()
                .stream()
                .filter(table -> Objects.equals(table.getName(), fuelTableName))
                .findFirst()
                .orElseThrow(
                        () -> new FuelTableNotExistException(
                                "Table '%s' doesn't exist".formatted(fuelTableName)
                        )
                );
    }
}
