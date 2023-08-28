package by.aurorasoft.fuelinfosearcher.service.searcher.manager;

import by.aurorasoft.fuelinfosearcher.model.Fuel;
import by.aurorasoft.fuelinfosearcher.model.Specification;
import by.aurorasoft.fuelinfosearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searcher.exception.FuelSearcherNotExistException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.aurorasoft.fuelinfosearcher.util.FuelSpecificationExtractingPropertyUtil.extractTableName;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public final class FuelSearchingManager {
    private final Map<String, FuelSearcher> searchersByTableNames;

    public FuelSearchingManager(final List<FuelSearcher> searchers) {
        this.searchersByTableNames = findSearchersByTableNames(searchers);
    }

    public Optional<Fuel> find(final Specification specification) {
        final FuelSearcher searcher = this.findSearcher(specification);
        return searcher.find(specification);
    }

    private static Map<String, FuelSearcher> findSearchersByTableNames(
            final List<FuelSearcher> searchers) {
        return searchers.stream()
                .collect(
                        toMap(
                                FuelSearcher::findTableName,
                                identity()
                        )
                );
    }

    private FuelSearcher findSearcher(final Specification specification) {
        final String tableName = extractTableName(specification);
        return this.searchersByTableNames.computeIfAbsent(
                tableName,
                FuelSearchingManager::throwSearcherNotExistException
        );
    }

    private static FuelSearcher throwSearcherNotExistException(final String tableName) {
        throw new FuelSearcherNotExistException(
                "There is no searcher for table '%s'".formatted(tableName)
        );
    }
}
