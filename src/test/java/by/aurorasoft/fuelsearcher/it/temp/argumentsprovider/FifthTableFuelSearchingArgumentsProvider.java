package by.aurorasoft.fuelsearcher.it.temp.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.temp.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class FifthTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ";

    public FifthTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments(
            final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier
    ) {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Кировец К-744Р4")
                                .machinery("Доминанта Д-880")
                                .workingWidth("8.8")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 6...8 см")
                                .build())
                        .expected(new Fuel(23.7, 12.0))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 3022")
                                .machinery("АПД-7.5")
                                .workingWidth("7.5")
                                .routingLength("150-200")
                                .processingDepth("Глубина обработки 6...8 см")
                                .build())
                        .expected(new Fuel(20.4, 8.6))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6")
                                .workingWidth("6")
                                .routingLength("150-200")
                                .processingDepth("Глубина обработки 10...14 см")
                                .build())
                        .expected(new Fuel(16.2, 7.3))
                        .build(),
                //not existing tractor
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing")
                                .machinery("АКЧ-6")
                                .workingWidth("6")
                                .routingLength("150-200")
                                .processingDepth("Глубина обработки 10...14 см")
                                .build())
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 2022")
                                .machinery("not existing")
                                .workingWidth("6")
                                .routingLength("150-200")
                                .processingDepth("Глубина обработки 10...14 см")
                                .build())
                        .build(),
                //not existing working width
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6")
                                .workingWidth("not existing")
                                .routingLength("150-200")
                                .processingDepth("Глубина обработки 10...14 см")
                                .build())
                        .build(),
                //not existing routing length
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6")
                                .workingWidth("6")
                                .routingLength("not existing")
                                .processingDepth("Глубина обработки 10...14 см")
                                .build())
                        .build(),
                //not existing processing depth
                FuelSearchingArguments.builder()
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
