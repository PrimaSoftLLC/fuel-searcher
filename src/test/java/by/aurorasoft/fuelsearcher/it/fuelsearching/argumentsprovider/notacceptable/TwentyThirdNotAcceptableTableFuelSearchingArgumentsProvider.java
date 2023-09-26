package by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.notacceptable;

import by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model.NotAcceptableFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public final class TwentyThirdNotAcceptableTableFuelSearchingArgumentsProvider extends NotAcceptableTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА";

    public TwentyThirdNotAcceptableTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotAcceptableFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                NotAcceptableFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .workingWidth("3.8")
                                .yield("5-7.5")
                                .routingLength("Менее 150")
                                .build())
                        .failedPropertyNames(Set.of("механизм"))
                        .build()
        );
    }
}
