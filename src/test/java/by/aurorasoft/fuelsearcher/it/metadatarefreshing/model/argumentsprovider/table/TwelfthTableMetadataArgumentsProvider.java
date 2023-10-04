package by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider.table;

import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataView;

import java.util.stream.Stream;

public final class TwelfthTableMetadataArgumentsProvider extends TablePropertyMetadataViewsProvider {
    private static final String TABLE_NAME = "ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ";

    public TwelfthTableMetadataArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<PropertyMetadataView> createViews(
            final PropertyMetadataViewFactory factory
    ) {
        return Stream.of(
                factory.create(
                        "длина гона",
                        new String[]{"Менее 150", "150-200", "201-300", "301-400", "401-600", "601-1000", "Более 1000"}
                ),
                factory.create(
                        "механизм",
                        new String[]{"Berthoud Raptor 4200, 36 м", "Mecosan Technoma Lazer 4240, 24 м", "Amazone Pantera 4502, 24 м", "Mazzotti IBIS 3180 LP, 24 м"}
                ),
                factory.create(
                        "тип удобрения",
                        new String[]{"Гранулированные удобрения"}
                ),
                factory.create(
                        "способ загрузки удобрений и расстояние транспортировки",
                        new String[]{"Механизированный с загрузкой в конце гона", "Механизированный с подъездом до 500 м", "Механизированный с подъездом 501...1000 м", "Механизированный с подъездом 1001...2000 м", "Механизированный с подъездом 2001...3000 м"}
                ),
                factory.create(
                        "норма внесения",
                        new String[]{"До 100", "100...150", "150...200", "200...250", "250...300", "300...350", "350...400", "400...450", "450...500", "500...550", "550...600"}
                )
        );
    }
}
