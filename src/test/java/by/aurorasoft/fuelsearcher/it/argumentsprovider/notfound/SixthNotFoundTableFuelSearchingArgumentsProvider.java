package by.aurorasoft.fuelsearcher.it.argumentsprovider.notfound;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.NotFoundFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class SixthNotFoundTableFuelSearchingArgumentsProvider extends NotFoundTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ";

    public SixthNotFoundTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotFoundFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                //not existing tractor
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing")
                                .machinery("АКЧ-6")
                                .workingWidth("6")
                                .routingLength("150-200")
                                .processingDepth("Глубина обработки 10...14 см")
                                .build())
                        .build(),
                //not existing machinery
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 2022")
                                .machinery("not existing")
                                .workingWidth("6")
                                .routingLength("150-200")
                                .processingDepth("Глубина обработки 10...14 см")
                                .build())
                        .build(),
                //not existing working width
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6")
                                .workingWidth("not existing")
                                .routingLength("150-200")
                                .processingDepth("Глубина обработки 10...14 см")
                                .build())
                        .build(),
                //not existing routing length
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6")
                                .workingWidth("6")
                                .routingLength("not existing")
                                .processingDepth("Глубина обработки 10...14 см")
                                .build())
                        .build(),
                //not existing processing depth
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6")
                                .workingWidth("6")
                                .routingLength("150-200")
                                .processingDepth("not existing")
                                .build())
                        .build()
        );
    }
}
