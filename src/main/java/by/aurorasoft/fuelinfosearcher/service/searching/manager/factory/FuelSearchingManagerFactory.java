package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory;

import by.aurorasoft.fuelinfosearcher.service.searching.AbstractTableFuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.FuelSearchingManager;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.FuelSearchersReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public final class FuelSearchingManagerFactory {
    private final FuelSearchersReader fuelSearchersReader;

    public FuelSearchingManager create(final String filePath) {
        final List<AbstractTableFuelSearcher> searchers = this.fuelSearchersReader.read(filePath);
        return new FuelSearchingManager(searchers);
    }
}
