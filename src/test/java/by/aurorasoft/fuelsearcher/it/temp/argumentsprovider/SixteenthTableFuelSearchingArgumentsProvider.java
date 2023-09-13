package by.aurorasoft.fuelsearcher.it.temp.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.temp.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class SixteenthTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "КОШЕНИЕ ТРАВ С ПЛЮЩЕНИЕМ";

    public SixteenthTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments(
            final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier
    ) {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Fendt-933")
                                .machinery("Krone-B 1000 CV Collet")
                                .workingWidth("9.7")
                                .yield("до 10")
                                .routingLength("Менее 150")
                                .build())
                        .expected(new Fuel(31.5, 4.7))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 3022")
                                .machinery("КМР-9П")
                                .workingWidth("9")
                                .yield("свыше 35")
                                .routingLength("150-200")
                                .build())
                        .expected(new Fuel(27.9, 7.4))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 920.2")
                                .machinery("KRONE EC-280")
                                .workingWidth("2.8")
                                .yield("свыше 35")
                                .routingLength("Менее 150")
                                .build())
                        .expected(new Fuel(7.5, 5.6))
                        .build(),
                //not existing tractor
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing")
                                .machinery("KRONE EC-280")
                                .workingWidth("2.8")
                                .yield("свыше 35")
                                .routingLength("Менее 150")
                                .build())
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 920.2")
                                .machinery("not existing")
                                .workingWidth("2.8")
                                .yield("свыше 35")
                                .routingLength("Менее 150")
                                .build())
                        .build(),
                //not existing working width
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 920.2")
                                .machinery("KRONE EC-280")
                                .workingWidth("not exsting")
                                .yield("свыше 35")
                                .routingLength("Менее 150")
                                .build())
                        .build(),
                //not existing yield
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 920.2")
                                .machinery("KRONE EC-280")
                                .workingWidth("2.8")
                                .yield("not existing")
                                .routingLength("Менее 150")
                                .build())
                        .build(),
                //not existing routing length
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 920.2")
                                .machinery("KRONE EC-280")
                                .workingWidth("2.8")
                                .yield("свыше 35")
                                .routingLength("not existing")
                                .build())
                        .build()
        );
    }

}
