package by.aurorasoft.fuelsearcher.it.argumentsprovider.notfound;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.NotFoundFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class FourteenthNotFoundTableFuelSearchingArgumentsProvider extends NotFoundTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ";

    public FourteenthNotFoundTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotFoundFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                //not existing tractor
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing")
                                .machinery("МЖТ-16")
                                .transportDistance("24.1...28")
                                .spreadRate("30-50")
                                .roadGroup("Первая группа дорог")
                                .build())
                        .build(),
                //not existing machinery
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("JOHN DEERE 6930")
                                .machinery("not existing")
                                .transportDistance("24.1...28")
                                .spreadRate("30-50")
                                .roadGroup("Первая группа дорог")
                                .build())
                        .build(),
                //not existing transport distance
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("JOHN DEERE 6930")
                                .machinery("МЖТ-16")
                                .transportDistance("not existing")
                                .spreadRate("30-50")
                                .roadGroup("Первая группа дорог")
                                .build())
                        .build(),
                //not existing spread rate
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("JOHN DEERE 6930")
                                .machinery("МЖТ-16")
                                .transportDistance("24.1...28")
                                .spreadRate("not existing")
                                .roadGroup("Первая группа дорог")
                                .build())
                        .build(),
                //not existing road group
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("JOHN DEERE 6930")
                                .machinery("МЖТ-16")
                                .transportDistance("24.1...28")
                                .spreadRate("30-50")
                                .roadGroup("not existing")
                                .build())
                        .build()
        );
    }
}
