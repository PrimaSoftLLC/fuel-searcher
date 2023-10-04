package by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider.table;

import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataView;

import java.util.stream.Stream;

//TODO
public final class EighteenthTablePropertyMetadataViewsProvider extends TablePropertyMetadataViewsProvider {
    private static final String TABLE_NAME = "СГРЕБАНИЕ СЕНА В ВАЛКИ";

    public EighteenthTablePropertyMetadataViewsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<PropertyMetadataView> createViews(final PropertyMetadataViewFactory factory) {
        return Stream.of(

        );
    }
}
