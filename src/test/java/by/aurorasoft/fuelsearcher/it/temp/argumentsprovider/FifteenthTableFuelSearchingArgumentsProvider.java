package by.aurorasoft.fuelsearcher.it.temp.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.temp.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class FifteenthTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "КОШЕНИЕ СЕЯНЫХ И ЕСТЕСТВЕННЫХ ТРАВ";

    public FifteenthTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments(
            final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier
    ) {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 2522")
                                .machinery("KDT 941")
                                .workingWidth("9.14")
                                .yield("до 10")
                                .routingLength("Менее 150")
                                .build())
                        .expected(new Fuel(28.1, 4.7))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1523")
                                .machinery("Novocat Alfa Motion 351")
                                .workingWidth("3.46")
                                .yield("свыше 35")
                                .routingLength("201-300")
                                .build())
                        .expected(new Fuel(11.4, 4.9))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("КДЛ-3.14")
                                .workingWidth("3.14")
                                .yield("20-25")
                                .routingLength("201-300")
                                .build())
                        .expected(new Fuel(11.7, 4.4))
                        .build(),
                //not existing tractor
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing")
                                .machinery("КДЛ-3.14")
                                .workingWidth("3.14")
                                .yield("20-25")
                                .routingLength("201-300")
                                .build())
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("not existing")
                                .workingWidth("3.14")
                                .yield("20-25")
                                .routingLength("201-300")
                                .build())
                        .build(),
                //not existing working width
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("КДЛ-3.14")
                                .workingWidth("not existing")
                                .yield("20-25")
                                .routingLength("201-300")
                                .build())
                        .build(),
                //not existing yield
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("КДЛ-3.14")
                                .workingWidth("3.14")
                                .yield("not existing")
                                .routingLength("201-300")
                                .build())
                        .build(),
                //not existing routing length
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("КДЛ-3.14")
                                .workingWidth("3.14")
                                .yield("20-25")
                                .routingLength("not existing")
                                .build())
                        .build()
        );
    }

}
