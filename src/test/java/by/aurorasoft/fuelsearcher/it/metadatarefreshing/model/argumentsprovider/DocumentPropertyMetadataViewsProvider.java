package by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider;


import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataView;
import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider.table.*;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public final class DocumentPropertyMetadataViewsProvider {
    private final List<TablePropertyMetadataViewsProvider> tablePropertyMetadataViewsProviders = List.of(
            new FirstTableMetadataArgumentsProvider(),
            new SecondTableMetadataArgumentsProvider(),
            new ThirdTableMetadataArgumentsProvider(),
            new FourthTableMetadataArgumentsProvider(),
            new FifthTableMetadataArgumentsProvider(),
            new SixthTableMetadataArgumentsProvider(),
            new SeventhTableMetadataArgumentsProvider(),
            new EighthTableMetadataArgumentsProvider(),
            new NinthTableMetadataArgumentsProvider(),
            new TenthTableMetadataArgumentsProvider(),
            new EleventhTableMetadataArgumentsProvider(),
            new TwelfthTableMetadataArgumentsProvider(),
            new ThirteenthTableMetadataArgumentsProvider(),
            new FourteenthTableMetadataArgumentsProvider(),
            new FifteenthTableMetadataArgumentsProvider(),
            new SixteenthTableMetadataArgumentsProvider(),
            new SeventeenthTableMetadataArgumentsProvider()
    );

    public Set<PropertyMetadataView> provide() {
        return this.tablePropertyMetadataViewsProviders
                .stream()
                .flatMap(TablePropertyMetadataViewsProvider::provide)
                .collect(toSet());
    }
}
