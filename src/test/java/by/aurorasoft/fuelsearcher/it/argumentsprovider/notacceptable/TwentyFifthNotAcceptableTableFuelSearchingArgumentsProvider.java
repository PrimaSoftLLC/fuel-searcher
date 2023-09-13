package by.aurorasoft.fuelsearcher.it.argumentsprovider.notacceptable;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.NotAcceptableFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public final class TwentyFifthNotAcceptableTableFuelSearchingArgumentsProvider extends NotAcceptableTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "УБОРКА КАРТОФЕЛЯ";

    public TwentyFifthNotAcceptableTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotAcceptableFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                NotAcceptableFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .soilType("Легкие почвы")
                                .tractor("Беларус 1221")
                                .machinery("КПБ-260-2")
                                .routingLength("Менее 150")
                                .build())
                        .failedPropertyNames(List.of("ширина междурядий", "урожайность"))
                        .build()
        );
    }
}
