package by.aurorasoft.fuelsearcher.it.temp.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.temp.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class TwentySeventhTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ТРАНСПОРТИРОВКА ГРУЗОВ ТРАКТОРАМИ С ОДНИМ ПРИЦЕПОМ";

    public TwentySeventhTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments(
            final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier
    ) {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("БЕЛАРУС-3522")
                                .machinery("ПСС-25")
                                .cargoClass("Грузы I класса")
                                .transportDistance("0.25-0.75")
                                .roadGroup("I")
                                .build())
                        .expected(new Fuel(341.7, 0.19))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("БЕЛАРУС-3522")
                                .machinery("ПСС-20")
                                .cargoClass("Грузы II класса")
                                .transportDistance("3.26-4")
                                .roadGroup("II")
                                .build())
                        .expected(new Fuel(146.9, 0.83))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("БЕЛАРУС-3522")
                                .machinery("ПСТБ-17")
                                .cargoClass("Грузы III класса")
                                .transportDistance("0.25-0.75")
                                .roadGroup("III")
                                .build())
                        .expected(new Fuel(131.9, 0.48))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("БЕЛАРУС-3022")
                                .machinery("ПСС-25")
                                .cargoClass("Грузы IV класса")
                                .transportDistance("0.76-1.25")
                                .roadGroup("III")
                                .build())
                        .expected(new Fuel(91.9, 0.7))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус-3022")
                                .machinery("ПСС-20")
                                .cargoClass("Грузы II класса")
                                .transportDistance("1.26-1.75")
                                .roadGroup("II")
                                .build())
                        .expected(new Fuel(201, 0.38))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус-3022")
                                .machinery("BAASTRUP 18")
                                .cargoClass("Грузы III класса")
                                .transportDistance("5.1-6")
                                .roadGroup("II")
                                .build())
                        .expected(new Fuel(54.2, 1.66))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("БЕЛАРУС-1221")
                                .machinery("СТС-12")
                                .cargoClass("Грузы II класса")
                                .transportDistance("3.26-4")
                                .roadGroup("I")
                                .build())
                        .expected(new Fuel(69.3, 0.8))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 920")
                                .machinery("ПС-30")
                                .cargoClass("Грузы I класса")
                                .transportDistance("45.1-50")
                                .roadGroup("III")
                                .build())
                        .expected(new Fuel(8.7, 5.65))
                        .build(),
                //not existing tractor
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing")
                                .machinery("ПС-30")
                                .cargoClass("Грузы I класса")
                                .transportDistance("45.1-50")
                                .roadGroup("III")
                                .build())
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 920")
                                .machinery("not existing")
                                .cargoClass("Грузы I класса")
                                .transportDistance("45.1-50")
                                .roadGroup("III")
                                .build())
                        .build(),
                //not existing cargo class
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 920")
                                .machinery("ПС-30")
                                .cargoClass("not existing")
                                .transportDistance("45.1-50")
                                .roadGroup("III")
                                .build())
                        .build(),
                //not existing transport distance
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 920")
                                .machinery("ПС-30")
                                .cargoClass("Грузы I класса")
                                .transportDistance("not existing")
                                .roadGroup("III")
                                .build())
                        .build(),
                //not existing road group
                FuelSearchingArguments.builder()
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