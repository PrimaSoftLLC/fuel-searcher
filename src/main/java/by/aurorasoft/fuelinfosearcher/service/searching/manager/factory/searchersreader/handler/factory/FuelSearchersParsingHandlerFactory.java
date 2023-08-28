package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.factory;

import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.FuelSearchersParsingHandler;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.FuelSpecificationPropertyExtractorDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter.FinalFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter.interim.InterimFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.fueltablesearcher.FuelTableSearcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class FuelSearchersParsingHandlerFactory {
    private final FuelTableSearcher fuelTableSearcher;
    private final InterimFilterFactoryDictionary intermediateRowFilterFactoryDictionary;
    private final FinalFilterFactoryDictionary conclusiveRowFilterFactoryDictionary;
    private final FuelSpecificationPropertyExtractorDictionary fuelSpecificationPropertyExtractorDictionary;

    public FuelSearchersParsingHandler create() {
        return FuelSearchersParsingHandler.builder()
                .fuelTableSearcher(this.fuelTableSearcher)
                .intermediateRowFilterFactoryDictionary(this.intermediateRowFilterFactoryDictionary)
                .conclusiveRowFilterFactoryDictionary(this.conclusiveRowFilterFactoryDictionary)
                .fuelSpecificationPropertyExtractorDictionary(this.fuelSpecificationPropertyExtractorDictionary)
                .build();
    }
}
