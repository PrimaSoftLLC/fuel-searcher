package by.aurorasoft.fuelsearcher.it.temp.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.temp.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class EighthTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА";

    public EighthTableFuelSearchingArgumentsProvider() {
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
                                .machinery("Horsch Maestro 24 S")
                                .workingWidth("16.8")
                                .routingLength("Менее 150")
                                .sowingNorm("Норма высева 15 кг/га")
                                .build())
                        .expected(new Fuel(32.9, 8.2))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("Ферабокс Футура Макси 8")
                                .workingWidth("5.6")
                                .routingLength("150-200")
                                .sowingNorm("Норма высева 30 кг/га")
                                .build())
                        .expected(new Fuel(13.6, 3.6))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("NC Tehnik Mojnjsem")
                                .workingWidth("5.6")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 45 кг/га")
                                .build())
                        .expected(new Fuel(18.8, 3.1))
                        .build(),
                //not existing tractor
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing")
                                .machinery("NC Tehnik Mojnjsem")
                                .workingWidth("5.6")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 45 кг/га")
                                .build())
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("not existing")
                                .workingWidth("5.6")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 45 кг/га")
                                .build())
                        .build(),
                //not existing working width
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("NC Tehnik Mojnjsem")
                                .workingWidth("not existing")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 45 кг/га")
                                .build())
                        .build(),
                //not existing routing length
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("NC Tehnik Mojnjsem")
                                .workingWidth("5.6")
                                .routingLength("not existing")
                                .sowingNorm("Норма высева 45 кг/га")
                                .build())
                        .build(),
                //not existing sowing norm
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("NC Tehnik Mojnjsem")
                                .workingWidth("5.6")
                                .routingLength("Более 1000")
                                .sowingNorm("not existing")
                                .build())
                        .build()
        );
    }

}
