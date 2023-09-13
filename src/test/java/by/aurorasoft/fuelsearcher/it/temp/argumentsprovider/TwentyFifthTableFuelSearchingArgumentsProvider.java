package by.aurorasoft.fuelsearcher.it.temp.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.temp.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class TwentyFifthTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "УБОРКА КАРТОФЕЛЯ";

    public TwentyFifthTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments(
            final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier
    ) {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .soilType("Легкие почвы")
                                .tractor("Беларус 1221")
                                .machinery("КПБ-260-2")
                                .rowWidth("90")
                                .yield("До 15")
                                .routingLength("Менее 150")
                                .build())
                        .expected(new Fuel(24.7, 2.33))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .soilType("Легкие почвы")
                                .tractor("New Holland TL 80")
                                .machinery("Grimme 1500 dr")
                                .rowWidth("70")
                                .yield("25-30")
                                .routingLength("150-200")
                                .build())
                        .expected(new Fuel(64.7, 0.76))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .soilType("Средние почвы")
                                .tractor("New Holland TL 80")
                                .machinery("Grimme 1500 dr")
                                .rowWidth("70")
                                .yield("30-35")
                                .routingLength("150-200")
                                .build())
                        .expected(new Fuel(69.8, 0.7))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .soilType("Тяжелые почвы")
                                .tractor("New Holland TL 80")
                                .machinery("Grimme 1500 dr")
                                .rowWidth("70")
                                .yield("Свыше 35")
                                .routingLength("150-200")
                                .build())
                        .expected(new Fuel(72.8, 0.68))
                        .build(),
                //not existing soil type
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .soilType("not existing")
                                .tractor("New Holland TL 80")
                                .machinery("Grimme 1500 dr")
                                .rowWidth("70")
                                .yield("Свыше 35")
                                .routingLength("150-200")
                                .build())
                        .build(),
                //not existing tractor
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .soilType("Тяжелые почвы")
                                .tractor("not existing")
                                .machinery("Grimme 1500 dr")
                                .rowWidth("70")
                                .yield("Свыше 35")
                                .routingLength("150-200")
                                .build())
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .soilType("Тяжелые почвы")
                                .tractor("New Holland TL 80")
                                .machinery("not existing")
                                .rowWidth("70")
                                .yield("Свыше 35")
                                .routingLength("150-200")
                                .build())
                        .build(),
                //not existing row width
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .soilType("Тяжелые почвы")
                                .tractor("New Holland TL 80")
                                .machinery("Grimme 1500 dr")
                                .rowWidth("not existing")
                                .yield("Свыше 35")
                                .routingLength("150-200")
                                .build())
                        .build(),
                //not existing yield
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .soilType("Тяжелые почвы")
                                .tractor("New Holland TL 80")
                                .machinery("Grimme 1500 dr")
                                .rowWidth("70")
                                .yield("not existing")
                                .routingLength("150-200")
                                .build())
                        .build(),
                //not existing routing length
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .soilType("Тяжелые почвы")
                                .tractor("New Holland TL 80")
                                .machinery("Grimme 1500 dr")
                                .rowWidth("70")
                                .yield("Свыше 35")
                                .routingLength("not existing")
                                .build())
                        .build()
        );
    }

}