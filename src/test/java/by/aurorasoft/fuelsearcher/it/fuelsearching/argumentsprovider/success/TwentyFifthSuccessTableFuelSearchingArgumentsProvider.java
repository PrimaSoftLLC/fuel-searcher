package by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.success;

import by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model.SuccessFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class TwentyFifthSuccessTableFuelSearchingArgumentsProvider extends SuccessTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "УБОРКА КАРТОФЕЛЯ";

    public TwentyFifthSuccessTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<SuccessFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                SuccessFuelSearchingArguments.builder()
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
                SuccessFuelSearchingArguments.builder()
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
                SuccessFuelSearchingArguments.builder()
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
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .soilType("Тяжелые почвы")
                                .tractor("New Holland TL 80")
                                .machinery("Grimme 1500 dr")
                                .rowWidth("70")
                                .yield("Свыше 35")
                                .routingLength("150-200")
                                .build())
                        .expected(new Fuel(72.8, 0.68))
                        .build()
        );
    }
}
