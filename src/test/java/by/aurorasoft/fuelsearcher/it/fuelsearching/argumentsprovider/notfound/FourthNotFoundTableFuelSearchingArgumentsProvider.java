package by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.notfound;

import by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model.NotFoundFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class FourthNotFoundTableFuelSearchingArgumentsProvider extends NotFoundTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ";

    public FourthNotFoundTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotFoundFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                //not existing tractor
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing")
                                .machinery("Horsch Terrano 4FX")
                                .workingWidth("4")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 14…20 см")
                                .build())
                        .build(),
                //not existing machinery
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1522")
                                .machinery("not existing")
                                .workingWidth("4")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 14...20 см")
                                .build())
                        .build(),
                //not existing working width
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1522")
                                .machinery("Horsch Terrano 4FX")
                                .workingWidth("not existing")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 14...20 см")
                                .build())
                        .build(),
                //not existing routing length
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1522")
                                .machinery("Horsch Terrano 4FX")
                                .workingWidth("4")
                                .routingLength("not existing")
                                .processingDepth("Глубина обработки 14...20 см")
                                .build())
                        .build(),
                //not existing processing depth
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1522")
                                .machinery("Horsch Terrano 4FX")
                                .workingWidth("4")
                                .routingLength("Менее 150")
                                .processingDepth("not existing")
                                .build())
                        .build()
        );
    }
}
