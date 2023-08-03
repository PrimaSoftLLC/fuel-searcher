package by.aurorasoft.fuelinfosearcher.service.searching.manager;

import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import by.aurorasoft.fuelinfosearcher.service.searching.AbstractFuelInfoSearchingService;
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
    private final Map<String, AbstractFuelInfoSearchingService> searchingServicesByTableNames;

    public FuelInfoSearchingManager(final List<AbstractFuelInfoSearchingService> searchingServices) {
        this.searchingServicesByTableNames = findSearchingServicesByTableNames(searchingServices);
    }

    public Optional<FuelInfo> find(final FuelInfoSpecification specification) {
        final AbstractFuelInfoSearchingService searchingService = this.findSearchingService(specification);
        return searchingService.find(specification);
    }

    private static Map<String, AbstractFuelInfoSearchingService> findSearchingServicesByTableNames(
            final List<AbstractFuelInfoSearchingService> searchingServices) {
        return searchingServices.stream()
                .collect(
                        toMap(
                                AbstractFuelInfoSearchingService::findTableName,
                                identity()
                        )
                );
    }

    private AbstractFuelInfoSearchingService findSearchingService(final FuelInfoSpecification specification) {
        final String tableName = extractTableName(specification);
        return this.searchingServicesByTableNames.computeIfAbsent(
                tableName,
                FuelInfoSearchingManager::throwNotExistServiceException
        );
    }

    private static AbstractFuelInfoSearchingService throwNotExistServiceException(final String tableName) {
        throw new FuelInfoSearchingServiceNotExistException(
                "There is no searching service for table '%s'".formatted(tableName)
        );
    }
}
