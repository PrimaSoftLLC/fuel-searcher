package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory;

import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.TableNameExtractor;
import by.aurorasoft.fuelinfosearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.FuelSearchingManager;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.FuelSearchersReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public final class FuelSearchingManagerFactory {
    private final FuelSearchersReader fuelSearchersReader;
    private final TableNameExtractor tableNameExtractor;

    public FuelSearchingManager create(final String filePath) {
        final List<FuelSearcher> searchers = this.fuelSearchersReader.read(filePath);
        return new FuelSearchingManager(searchers, this.tableNameExtractor);
    }
}
