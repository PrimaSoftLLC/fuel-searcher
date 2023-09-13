package by.aurorasoft.fuelsearcher.it.argumentsprovider.notfound;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.NotFoundFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class NotExistingTableFuelSearchingArgumentsProvider extends NotFoundTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "НЕСУЩЕСТВУЮЩАЯ ТАБЛИЦА";

    public NotExistingTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotFoundFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("К 744Р3")
                                .machinery("ППУ-13")
                                .corpusCount("13")
                                .ploughingDepth("25-27")
                                .routingLength("201-300")
                                .specificResistance("Удельное сопротивление плуга 60...65 кПа")
                                .build())
                        .build()
        );
    }
}
