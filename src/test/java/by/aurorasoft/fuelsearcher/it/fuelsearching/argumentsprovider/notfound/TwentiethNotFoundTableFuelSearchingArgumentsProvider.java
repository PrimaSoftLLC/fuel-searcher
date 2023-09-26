package by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.notfound;

import by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model.NotFoundFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class TwentiethNotFoundTableFuelSearchingArgumentsProvider extends NotFoundTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА";

    public TwentiethNotFoundTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotFoundFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                //not existing tractor
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing")
                                .machinery("Krone CAMPRIMA CF 125")
                                .workingWidth("5.5")
                                .yield("3.6-4")
                                .routingLength("150-200")
                                .build())
                        .build(),
                //not existing machinery
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 82")
                                .machinery("not existing")
                                .workingWidth("5.5")
                                .yield("3.6–4")
                                .routingLength("150-200")
                                .build())
                        .build(),
                //not existing working width
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 82")
                                .machinery("Krone CAMPRIMA CF 125")
                                .workingWidth("not existing")
                                .yield("3.6–4")
                                .routingLength("150-200")
                                .build())
                        .build(),
                //not existing yield
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 82")
                                .machinery("Krone CAMPRIMA CF 125")
                                .workingWidth("5.5")
                                .yield("not exiting")
                                .routingLength("150-200")
                                .build())
                        .build(),
                //not existing routing length
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 82")
                                .machinery("Krone CAMPRIMA CF 125")
                                .workingWidth("5.5")
                                .yield("3.6–4")
                                .routingLength("not existing")
                                .build())
                        .build()
        );
    }
}
