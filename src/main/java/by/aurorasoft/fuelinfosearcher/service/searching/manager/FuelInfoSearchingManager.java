package by.aurorasoft.fuelinfosearcher.service.searching.manager;

import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.AbstractTableFuelInfoSearchingService;
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
    private final Map<String, AbstractTableFuelInfoSearchingService> searchingServicesByTableNames;

    public FuelInfoSearchingManager(final List<AbstractTableFuelInfoSearchingService> searchingServices) {
        this.searchingServicesByTableNames = findSearchingServicesByTableNames(searchingServices);
    }

    public Optional<FuelInfo> find(final FuelInfoSpecification specification) {
        final AbstractTableFuelInfoSearchingService searchingService = this.findSearchingService(specification);
        return searchingService.find(specification);
    }

    private static Map<String, AbstractTableFuelInfoSearchingService> findSearchingServicesByTableNames(
            final List<AbstractTableFuelInfoSearchingService> searchingServices) {
        return searchingServices.stream()
                .collect(
                        toMap(
                                AbstractTableFuelInfoSearchingService::findTableName,
                                identity()
                        )
                );
    }

    private AbstractTableFuelInfoSearchingService findSearchingService(final FuelInfoSpecification specification) {
        final String tableName = extractTableName(specification);
        return this.searchingServicesByTableNames.computeIfAbsent(
                tableName,
                FuelInfoSearchingManager::throwNotExistServiceException
        );
    }

    private static AbstractTableFuelInfoSearchingService throwNotExistServiceException(final String tableName) {
        throw new FuelInfoSearchingServiceNotExistException(
                "There is no searching service for table '%s'".formatted(tableName)
        );
    }
}
