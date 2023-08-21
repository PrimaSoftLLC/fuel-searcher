package by.aurorasoft.fuelinfosearcher.service.searching.manager;

import by.aurorasoft.fuelinfosearcher.model.Fuel;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.AbstractTableFuelSearchingService;
import by.aurorasoft.fuelinfosearcher.service.searching.exception.FuelInfoSearchingServiceNotExistException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.aurorasoft.fuelinfosearcher.util.FuelInfoSpecificationUtil.extractTableName;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Service
public final class FuelInfoSearchingManager {
    private final Map<String, AbstractTableFuelSearchingService> searchingServicesByTableNames;

    public FuelInfoSearchingManager(final List<AbstractTableFuelSearchingService> searchingServices) {
        this.searchingServicesByTableNames = findSearchingServicesByTableNames(searchingServices);
    }

    public Optional<Fuel> find(final FuelInfoSpecification specification) {
        final AbstractTableFuelSearchingService searchingService = this.findSearchingService(specification);
        return searchingService.find(specification);
    }

    private static Map<String, AbstractTableFuelSearchingService> findSearchingServicesByTableNames(
            final List<AbstractTableFuelSearchingService> searchingServices) {
        return searchingServices.stream()
                .collect(
                        toMap(
                                AbstractTableFuelSearchingService::findTableName,
                                identity()
                        )
                );
    }

    private AbstractTableFuelSearchingService findSearchingService(final FuelInfoSpecification specification) {
        final String tableName = extractTableName(specification);
        return this.searchingServicesByTableNames.computeIfAbsent(
                tableName,
                FuelInfoSearchingManager::throwNotExistServiceException
        );
    }

    private static AbstractTableFuelSearchingService throwNotExistServiceException(final String tableName) {
        throw new FuelInfoSearchingServiceNotExistException(
                "There is no searching service for table '%s'".formatted(tableName)
        );
    }
}
