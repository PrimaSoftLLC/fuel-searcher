package by.aurorasoft.fuelsearcher.it.temp.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.temp.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class TenthTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА";

    public TenthTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments(
            final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier
    ) {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 3522")
                                .machinery("Амкодор \"Veras\" 12000")
                                .workingWidth("12")
                                .routingLength("Менее 150")
                                .sowingNorm("Норма высева семян 3 кг/га")
                                .build())
                        .expected(new Fuel(24.5, 9.3))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 3522")
                                .machinery("Amazone Avant 6001-2")
                                .workingWidth("6")
                                .routingLength("601-1000")
                                .sowingNorm("Норма высева семян 8 кг/га")
                                .build())
                        .expected(new Fuel(28.1, 7.9))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("Horsch Maestro DV")
                                .workingWidth("6")
                                .routingLength("601-1000")
                                .sowingNorm("Норма высева семян 8 кг/га")
                                .build())
                        .expected(new Fuel(21.3, 5.5))
                        .build(),
                //not existing tractor
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing")
                                .machinery("Horsch Maestro DV")
                                .workingWidth("6")
                                .routingLength("601-1000")
                                .sowingNorm("Норма высева семян 8 кг/га")
                                .build())
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("not existing")
                                .workingWidth("6")
                                .routingLength("601-1000")
                                .sowingNorm("Норма высева семян 8 кг/га")
                                .build())
                        .build(),
                //not existing working width
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("Horsch Maestro DV")
                                .workingWidth("not existing")
                                .routingLength("601-1000")
                                .sowingNorm("Норма высева семян 8 кг/га")
                                .build())
                        .build(),
                //not existing routing length
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("Horsch Maestro DV")
                                .workingWidth("6")
                                .routingLength("not existing")
                                .sowingNorm("Норма высева семян 8 кг/га")
                                .build())
                        .build(),
                //not existing sowing norm
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("Horsch Maestro DV")
                                .workingWidth("6")
                                .routingLength("601-1000")
                                .sowingNorm("not existing")
                                .build())
                        .build()
        );
    }

}
