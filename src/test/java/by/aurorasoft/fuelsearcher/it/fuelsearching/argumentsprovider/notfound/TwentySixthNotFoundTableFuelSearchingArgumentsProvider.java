package by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.notfound;

import by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model.NotFoundFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class TwentySixthNotFoundTableFuelSearchingArgumentsProvider extends NotFoundTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "УБОРКА КУКУРУЗЫ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА";

    public TwentySixthNotFoundTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotFoundFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                //not existing combine
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .combine("not existing")
                                .workingWidth("6")
                                .yield("25-30")
                                .routingLength("Менее 150")
                                .build())
                        .build(),
                //not existing working width
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .combine("JOHN DEERE 8400")
                                .workingWidth("not existing")
                                .yield("25-30")
                                .routingLength("Менее 150")
                                .build())
                        .build(),
                //not existing yield
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .combine("JOHN DEERE 8400")
                                .workingWidth("6")
                                .yield("not existing")
                                .routingLength("Менее 150")
                                .build())
                        .build(),
                //not existing routing length
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .combine("JOHN DEERE 8400")
                                .workingWidth("6")
                                .yield("25-30")
                                .routingLength("not existing")
                                .build())
                        .build()
        );
    }
}
