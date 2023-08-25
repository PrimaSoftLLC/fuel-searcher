package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.fueltablesearcher;

import by.aurorasoft.fuelinfosearcher.model.FuelDocument;
import by.aurorasoft.fuelinfosearcher.model.FuelTable;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.fueltablesearcher.exception.FuelTableNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public final class FuelTableSearcher {
    private final FuelDocument fuelDocument;

    public FuelTable findFuelTable(final String tableName) {
        return this.fuelDocument.getTables()
                .stream()
                .filter(table -> Objects.equals(table.getName(), tableName))
                .findFirst()
                .orElseThrow(
                        () -> new FuelTableNotExistException(
                                "Table '%s' doesn't exist".formatted(tableName)
                        )
                );
    }
}
