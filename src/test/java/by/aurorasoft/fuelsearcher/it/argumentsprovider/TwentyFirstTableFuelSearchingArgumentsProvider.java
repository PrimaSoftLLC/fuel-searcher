package by.aurorasoft.fuelsearcher.it.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;

import java.util.stream.Stream;

public final class TwentyFirstTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments() {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ПРОВЯЛЕННОЙ МАССЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 1221")
                                .machinery("KRONE CF Ultima 155 XC")
                                .workingWidth("5")
                                .yield("до 5")
                                .routingLength("Менее 150")
                                .build())
                        .expected(new Fuel(46.1, 1.87))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ПРОВЯЛЕННОЙ МАССЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 1221")
                                .machinery("KRONE CF Ultima 155 XC")
                                .workingWidth("5")
                                .yield("свыше 15.5")
                                .routingLength("Более 1000")
                                .build())
                        .expected(new Fuel(155.6, 0.74))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ПРОВЯЛЕННОЙ МАССЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 1221")
                                .machinery("KRONE CF Ultima 155 XC")
                                .workingWidth("5")
                                .yield("14.5-15.5")
                                .routingLength("401-600")
                                .build())
                        .expected(new Fuel(140.9, 0.78))
                        .build(),
                //not existing tractor
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ПРОВЯЛЕННОЙ МАССЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("not existing")
                                .machinery("KRONE CF Ultima 155 XC")
                                .workingWidth("5")
                                .yield("14.5-15.5")
                                .routingLength("401-600")
                                .build())
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ПРОВЯЛЕННОЙ МАССЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 1221")
                                .machinery("not existing")
                                .workingWidth("5")
                                .yield("14.5-15.5")
                                .routingLength("401-600")
                                .build())
                        .build(),
                //not existing working width
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ПРОВЯЛЕННОЙ МАССЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 1221")
                                .machinery("KRONE CF Ultima 155 XC")
                                .workingWidth("not existing")
                                .yield("14.5-15.5")
                                .routingLength("401-600")
                                .build())
                        .build(),
                //not existing yield
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ПРОВЯЛЕННОЙ МАССЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 1221")
                                .machinery("KRONE CF Ultima 155 XC")
                                .workingWidth("5")
                                .yield("not existing")
                                .routingLength("401-600")
                                .build())
                        .build(),
                //not existing routing length
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПРЕССОВАНИЕ ПРОВЯЛЕННОЙ МАССЫ ПОСЛЕ КОМБАЙНА")
                                .tractor("Беларус 1221")
                                .machinery("KRONE CF Ultima 155 XC")
                                .workingWidth("5")
                                .yield("14.5-15.5")
                                .routingLength("not existing")
                                .build())
                        .build()
        );
    }

}
