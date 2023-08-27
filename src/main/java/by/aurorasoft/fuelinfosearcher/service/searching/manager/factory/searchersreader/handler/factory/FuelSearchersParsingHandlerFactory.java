package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.factory;

import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.FuelSearchersParsingHandler;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.FuelSpecificationPropertyExtractorDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter.ConclusiveRowFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.dictionary.rowfilter.IntermediateRowFilterFactoryDictionary;
import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.fueltablesearcher.FuelTableSearcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class FuelSearchersParsingHandlerFactory {
    private final FuelTableSearcher fuelTableSearcher;
    private final IntermediateRowFilterFactoryDictionary intermediateRowFilterFactoryDictionary;
    private final ConclusiveRowFilterFactoryDictionary conclusiveRowFilterFactoryDictionary;
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
