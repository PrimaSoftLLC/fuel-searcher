package by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.notacceptable;

import by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model.NotAcceptableFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public final class TenthNotAcceptableTableFuelSearchingArgumentsProvider extends NotAcceptableTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА";

    public TenthNotAcceptableTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotAcceptableFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                NotAcceptableFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 3522")
                                .machinery("Амкодор \"Veras\" 12000")
                                .sowingNorm("Норма высева семян 3 кг/га")
                                .build())
                        .failedPropertyNames(Set.of("ширина захвата", "длина гона"))
                        .build()
        );
    }
}
