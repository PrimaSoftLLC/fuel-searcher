package by.aurorasoft.fuelsearcher.it.argumentsprovider.success;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.SuccessFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class FourthSuccessTableFuelSearchingArgumentsProvider extends SuccessTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ";

    public FourthSuccessTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<SuccessFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Clas Xerion 5000")
                                .machinery("Köckerling Allrounder 1200")
                                .workingWidth("12")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 6...8 см")
                                .build())
                        .expected(new Fuel(26.4, 14.1))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Clas Xerion 5000")
                                .machinery("Köckerling Allrounder 1200")
                                .workingWidth("12")
                                .routingLength("Более 1000")
                                .processingDepth("Глубина обработки 6...8 см")
                                .build())
                        .expected(new Fuel(58.2, 7.5))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 3522")
                                .machinery("АПМ-8")
                                .workingWidth("8")
                                .routingLength("150-200")
                                .processingDepth("Глубина обработки 6...8 см")
                                .build())
                        .expected(new Fuel(24.0, 11.1))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 3022")
                                .machinery("КФУ-7.3")
                                .workingWidth("7.3")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 6...8 см")
                                .build())
                        .expected(new Fuel(17.3, 12.7))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1522")
                                .machinery("Horsch Terrano 4FX")
                                .workingWidth("4")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 14...20 см")
                                .build())
                        .expected(new Fuel(10.5, 8.7))
                        .build()
        );
    }
}
