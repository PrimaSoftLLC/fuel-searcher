package by.aurorasoft.fuelsearcher.it.argumentsprovider.notfound;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.NotAcceptableFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.it.argumentsprovider.notacceptable.NotAcceptableTableFuelSearchingArgumentsProvider;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public final class NotExistingTableFuelSearchingArgumentsProvider extends NotAcceptableTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "НЕСУЩЕСТВУЮЩАЯ ТАБЛИЦА";

    public NotExistingTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotAcceptableFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                NotAcceptableFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("К 744Р3")
                                .machinery("ППУ-13")
                                .corpusCount("13")
                                .ploughingDepth("25-27")
                                .routingLength("201-300")
                                .specificResistance("Удельное сопротивление плуга 60...65 кПа")
                                .build())
                        .failedPropertyNames(Set.of("имя таблицы"))
                        .build()
        );
    }
}
