package by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider;


import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataView;
import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider.table.*;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public final class DocumentPropertyMetadataViewsProvider {
    private final List<TablePropertyMetadataViewsProvider> tablePropertyMetadataViewsProviders = List.of(
            new FirstTablePropertyMetadataViewsProvider(),
            new SecondTablePropertyMetadataViewsProvider(),
            new ThirdTablePropertyMetadataViewsProvider(),
            new FourthTablePropertyMetadataViewsProvider(),
            new FifthTablePropertyMetadataViewsProvider(),
            new SixthTablePropertyMetadataViewsProvider(),
            new SeventhTablePropertyMetadataViewsProvider(),
            new EighthTablePropertyMetadataViewsProvider(),
            new NinthTablePropertyMetadataViewsProvider(),
            new TenthTablePropertyMetadataViewsProvider(),
            new EleventhTablePropertyMetadataViewsProvider(),
            new TwelfthTablePropertyMetadataViewsProvider(),
            new ThirteenthTablePropertyMetadataViewsProvider(),
            new FourteenthTablePropertyMetadataViewsProvider(),
            new FifteenthTablePropertyMetadataViewsProvider(),
            new SixteenthTablePropertyMetadataViewsProvider(),
            new SeventeenthTablePropertyMetadataViewsProvider()
    );

    public Set<PropertyMetadataView> provide() {
        return this.tablePropertyMetadataViewsProviders
                .stream()
                .flatMap(TablePropertyMetadataViewsProvider::provide)
                .collect(toSet());
    }
}
