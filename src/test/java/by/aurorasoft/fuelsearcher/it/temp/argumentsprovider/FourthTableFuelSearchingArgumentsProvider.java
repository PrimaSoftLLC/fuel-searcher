package by.aurorasoft.fuelsearcher.it.temp.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.temp.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class FourthTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ";

    public FourthTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments(
            final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier
    ) {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Clas Xerion 5000")
                                .machinery("Köckerling Allrounder 1200")
                                .workingWidth("12")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 6...8 см")
                                .build())
                        .expected(new Fuel(26.4, 14.1))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Clas Xerion 5000")
                                .machinery("Köckerling Allrounder 1200")
                                .workingWidth("12")
                                .routingLength("Более 1000")
                                .processingDepth("Глубина обработки 6...8 см")
                                .build())
                        .expected(new Fuel(58.2, 7.5))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 3522")
                                .machinery("АПМ-8")
                                .workingWidth("8")
                                .routingLength("150-200")
                                .processingDepth("Глубина обработки 6...8 см")
                                .build())
                        .expected(new Fuel(24.0, 11.1))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 3022")
                                .machinery("КФУ-7.3")
                                .workingWidth("7.3")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 6...8 см")
                                .build())
                        .expected(new Fuel(17.3, 12.7))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1522")
                                .machinery("Horsch Terrano 4FX")
                                .workingWidth("4")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 14...20 см")
                                .build())
                        .expected(new Fuel(10.5, 8.7))
                        .build(),
                //not existing tractor
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing")
                                .machinery("Horsch Terrano 4FX")
                                .workingWidth("4")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 14…20 см")
                                .build())
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1522")
                                .machinery("not existing")
                                .workingWidth("4")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 14...20 см")
                                .build())
                        .build(),
                //not existing working width
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1522")
                                .machinery("Horsch Terrano 4FX")
                                .workingWidth("not existing")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 14...20 см")
                                .build())
                        .build(),
                //not existing routing length
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1522")
                                .machinery("Horsch Terrano 4FX")
                                .workingWidth("4")
                                .routingLength("not existing")
                                .processingDepth("Глубина обработки 14...20 см")
                                .build())
                        .build(),
                //not existing processing depth
                FuelSearchingArguments.builder()
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
