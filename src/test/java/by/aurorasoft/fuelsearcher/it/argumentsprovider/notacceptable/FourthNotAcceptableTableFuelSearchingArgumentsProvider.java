package by.aurorasoft.fuelsearcher.it.argumentsprovider.notacceptable;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.NotAcceptableFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public final class FourthNotAcceptableTableFuelSearchingArgumentsProvider extends NotAcceptableTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ";

    public FourthNotAcceptableTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotAcceptableFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                NotAcceptableFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .machinery("Köckerling Allrounder 1200")
                                .workingWidth("12")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 6...8 см")
                                .build())
                        .failedPropertyNames(List.of("трактор"))
                        .build()
        );
    }
}
