package by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.notfound;

import by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model.NotFoundFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class TwentySecondNotFoundTableFuelSearchingArgumentsProvider extends NotFoundTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ПРЕССОВАНИЕ ЛЕНТ ЛЬНА";

    public TwentySecondNotFoundTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotFoundFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                //not existing tractor
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing tractor")
                                .machinery("ППЛ-1")
                                .workingWidth("1")
                                .yield("15-17")
                                .routingLength("201-300")
                                .build())
                        .build(),
                //not existing machinery
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82")
                                .machinery("not existing")
                                .workingWidth("1")
                                .yield("15-17")
                                .routingLength("201-300")
                                .build())
                        .build(),
                //not existing working width
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82")
                                .machinery("ППЛ-1")
                                .workingWidth("not existing")
                                .yield("15-17")
                                .routingLength("201-300")
                                .build())
                        .build(),
                //not existing yield
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82")
                                .machinery("ППЛ-1")
                                .workingWidth("1")
                                .yield("not existing")
                                .routingLength("201-300")
                                .build())
                        .build(),
                //not existing routing length
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82")
                                .machinery("ППЛ-1")
                                .workingWidth("1")
                                .yield("15-17")
                                .routingLength("not existing")
                                .build())
                        .build()
        );
    }
}
