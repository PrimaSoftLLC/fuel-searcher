package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.factory;

import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.FuelSearchersParsingHandler;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.fueltablesearcher.FuelTableSearcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class FuelSearchersParsingHandlerFactory {
    private final FuelTableSearcher fuelTableSearcher;

    public FuelSearchersParsingHandler create() {

    }

}
