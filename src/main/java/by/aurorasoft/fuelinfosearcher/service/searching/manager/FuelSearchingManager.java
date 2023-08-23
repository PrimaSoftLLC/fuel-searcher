package by.aurorasoft.fuelinfosearcher.service.searching.manager;

import by.aurorasoft.fuelinfosearcher.model.Fuel;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.AbstractTableFuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searching.exception.FuelSearcherNotExistException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractTableName;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public final class FuelSearchingManager {
    private final Map<String, AbstractTableFuelSearcher> searchersByTableNames;

    public FuelSearchingManager(final List<AbstractTableFuelSearcher> searchers) {
        this.searchersByTableNames = findSearchersByTableNames(searchers);
    }

    public Optional<Fuel> find(final FuelSpecification specification) {
        final AbstractTableFuelSearcher searcher = this.findSearcher(specification);
        return searcher.find(specification);
    }

    private static Map<String, AbstractTableFuelSearcher> findSearchersByTableNames(
            final List<AbstractTableFuelSearcher> searchers) {
        return searchers.stream()
                .collect(
                        toMap(
                                AbstractTableFuelSearcher::findTableName,
                                identity()
                        )
                );
    }

    private AbstractTableFuelSearcher findSearcher(final FuelSpecification specification) {
        final String tableName = extractTableName(specification);
        return this.searchersByTableNames.computeIfAbsent(
                tableName,
                FuelSearchingManager::throwSearcherNotExistException
        );
    }

    private static AbstractTableFuelSearcher throwSearcherNotExistException(final String tableName) {
        throw new FuelSearcherNotExistException(
                "There is no searcher for table '%s'".formatted(tableName)
        );
    }
}
