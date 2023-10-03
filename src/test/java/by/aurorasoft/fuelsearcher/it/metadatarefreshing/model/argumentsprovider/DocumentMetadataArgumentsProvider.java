package by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider;


import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider.table.*;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.stream.Stream;

public final class DocumentMetadataArgumentsProvider implements ArgumentsProvider {
    private final List<TableMetadataArgumentsProvider> tableMetadataArgumentsProviders = List.of(
            new FirstTableMetadataArgumentsProvider(),
            new SecondTableMetadataArgumentsProvider(),
            new ThirdTableMetadataArgumentsProvider(),
            new FourthTableMetadataArgumentsProvider(),
            new FifthTableMetadataArgumentsProvider(),
            new SixthTableMetadataArgumentsProvider(),
            new SeventhTableMetadataArgumentsProvider(),
            new EighthTableMetadataArgumentsProvider()
    );

    @Override
    public Stream<? extends Arguments> provideArguments(final ExtensionContext extensionContext) {
        return this.tableMetadataArgumentsProviders
                .stream()
                .flatMap(TableMetadataArgumentsProvider::provide);
    }
}
