package by.aurorasoft.fuelsearcher.it.argumentsprovider.notfound;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.NotFoundFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class NinthNotFoundTableFuelSearchingArgumentsProvider extends NotFoundTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ПОСЕВ САХАРНОЙ СВЕКЛЫ";

    public NinthNotFoundTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotFoundFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                //not existing tractor
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing")
                                .machinery("Tehnic NC")
                                .workingWidth("3.6")
                                .routingLength("601-1000")
                                .sowingNorm("Норма высева семян 45 кг/га")
                                .build())
                        .build(),
                //not existing machinery
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82")
                                .machinery("not existing")
                                .workingWidth("3.6")
                                .routingLength("601-1000")
                                .sowingNorm("Норма высева семян 45 кг/га")
                                .build())
                        .build(),
                //not existing working width
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82")
                                .machinery("Tehnic NC")
                                .workingWidth("not existing")
                                .routingLength("601-1000")
                                .sowingNorm("Норма высева семян 45 кг/га")
                                .build())
                        .build(),
                //not existing routing length
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82")
                                .machinery("Tehnic NC")
                                .workingWidth("3.6")
                                .routingLength("not existing")
                                .sowingNorm("Норма высева семян 45 кг/га")
                                .build())
                        .build(),
                //not existing sowing norm
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82")
                                .machinery("Tehnic NC")
                                .workingWidth("3.6")
                                .routingLength("601-1000")
                                .sowingNorm("not existing")
                                .build())
                        .build()
        );
    }
}
