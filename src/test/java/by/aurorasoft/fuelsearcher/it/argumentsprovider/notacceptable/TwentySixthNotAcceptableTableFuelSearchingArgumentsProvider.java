package by.aurorasoft.fuelsearcher.it.argumentsprovider.notacceptable;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.NotAcceptableFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class TwentySixthNotAcceptableTableFuelSearchingArgumentsProvider extends NotAcceptableTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "УБОРКА КУКУРУЗЫ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА";

    public TwentySixthNotAcceptableTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotAcceptableFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                NotAcceptableFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .workingWidth("9")
                                .yield("до 10")
                                .routingLength("Менее 150")
                                .build())
                        .failedPropertyNames(new String[]{"комбайн"})
                        .build()
        );
    }
}
