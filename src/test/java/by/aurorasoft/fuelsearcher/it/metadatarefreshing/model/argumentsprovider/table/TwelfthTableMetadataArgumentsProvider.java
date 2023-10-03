package by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider.table;

import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataArguments;

import java.util.stream.Stream;

public final class TwelfthTableMetadataArgumentsProvider extends TableMetadataArgumentsProvider {
    private static final String TABLE_NAME = "ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ";

    public TwelfthTableMetadataArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<PropertyMetadataArguments> createPropertyMetadataArguments(
            final PropertyMetadataArgumentsFactory factory
    ) {
        throw new RuntimeException();
    }
}
