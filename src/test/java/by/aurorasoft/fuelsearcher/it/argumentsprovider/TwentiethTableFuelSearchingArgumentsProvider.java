package by.aurorasoft.fuelsearcher.it.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;

import java.util.stream.Stream;

public final class TwentiethTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments() {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 2022")
                                .machinery("CAMPRIMA CF 155 XC")
                                .workingWidth("5.7")
                                .yield("До 1")
                                .routingLength("Менее 150")
                                .build())
                        .expected(new Fuel(13.9, 6.24))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 1221")
                                .machinery("Krone CAMPRIMA CF 155 XC")
                                .workingWidth("5.7")
                                .yield("4.6-5")
                                .routingLength("150-200")
                                .build())
                        .expected(new Fuel(45, 1.37))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 82")
                                .machinery("Krone CAMPRIMA CF 125")
                                .workingWidth("5.5")
                                .yield("3.6-4")
                                .routingLength("150-200")
                                .build())
                        .expected(new Fuel(29.3, 1.51))
                        .build(),
                //not existing tractor
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("not existing")
                                .machinery("Krone CAMPRIMA CF 125")
                                .workingWidth("5.5")
                                .yield("3.6-4")
                                .routingLength("150-200")
                                .build())
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 82")
                                .machinery("not existing")
                                .workingWidth("5.5")
                                .yield("3.6–4")
                                .routingLength("150-200")
                                .build())
                        .build(),
                //not existing working width
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 82")
                                .machinery("Krone CAMPRIMA CF 125")
                                .workingWidth("not existing")
                                .yield("3.6–4")
                                .routingLength("150-200")
                                .build())
                        .build(),
                //not existing yield
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 82")
                                .machinery("Krone CAMPRIMA CF 125")
                                .workingWidth("5.5")
                                .yield("not exiting")
                                .routingLength("150-200")
                                .build())
                        .build(),
                //not existing routing length
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 82")
                                .machinery("Krone CAMPRIMA CF 125")
                                .workingWidth("5.5")
                                .yield("3.6–4")
                                .routingLength("not existing")
                                .build())
                        .build()
        );
    }

}
