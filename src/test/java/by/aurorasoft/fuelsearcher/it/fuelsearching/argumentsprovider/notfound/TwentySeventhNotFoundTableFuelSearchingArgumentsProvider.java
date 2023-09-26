package by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.notfound;

import by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model.NotFoundFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class TwentySeventhNotFoundTableFuelSearchingArgumentsProvider extends NotFoundTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ТРАНСПОРТИРОВКА ГРУЗОВ ТРАКТОРАМИ С ОДНИМ ПРИЦЕПОМ";

    public TwentySeventhNotFoundTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotFoundFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                //not existing tractor
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing")
                                .machinery("ПС-30")
                                .cargoClass("Грузы I класса")
                                .transportDistance("45.1-50")
                                .roadGroup("III")
                                .build())
                        .build(),
                //not existing machinery
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 920")
                                .machinery("not existing")
                                .cargoClass("Грузы I класса")
                                .transportDistance("45.1-50")
                                .roadGroup("III")
                                .build())
                        .build(),
                //not existing cargo class
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 920")
                                .machinery("ПС-30")
                                .cargoClass("not existing")
                                .transportDistance("45.1-50")
                                .roadGroup("III")
                                .build())
                        .build(),
                //not existing transport distance
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 920")
                                .machinery("ПС-30")
                                .cargoClass("Грузы I класса")
                                .transportDistance("not existing")
                                .roadGroup("III")
                                .build())
                        .build(),
                //not existing road group
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 920")
                                .machinery("ПС-30")
                                .cargoClass("Грузы I класса")
                                .transportDistance("45.1-50")
                                .roadGroup("not existing")
                                .build())
                        .build()
        );
    }
}
