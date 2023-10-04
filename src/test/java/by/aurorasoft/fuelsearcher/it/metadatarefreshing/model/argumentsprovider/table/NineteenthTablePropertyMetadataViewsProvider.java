package by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider.table;

import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataView;

import java.util.stream.Stream;

public final class NineteenthTablePropertyMetadataViewsProvider extends TablePropertyMetadataViewsProvider {
    private static final String TABLE_NAME = "ПРЕССОВАНИЕ СЕНА ПОСЛЕ КОМБАЙНА";

    public NineteenthTablePropertyMetadataViewsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<PropertyMetadataView> createViews(final PropertyMetadataViewFactory factory) {
        return Stream.of(
                factory.create(
                        "длина гона",
                        new String[]{"Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"}
                ),
                factory.create(
                        "трактор",
                        new String[]{"Fendt 515c"}
                ),
                factory.create(
                        "механизм",
                        new String[]{"KRONE Comprima CV 150 XC"}
                ),
                factory.create(
                        "ширина захвата",
                        new String[]{"5.5"}
                ),
                factory.create(
                        "урожайность",
                        new String[]{"До 1", "1.1-1.5", "1.6-2", "2.1-2.5", "2.6-3", "3.1-3.5", "3.6-4", "4.1-4.5", "4.6-5", "5.1-5.5"}
                )
        );
    }
}
