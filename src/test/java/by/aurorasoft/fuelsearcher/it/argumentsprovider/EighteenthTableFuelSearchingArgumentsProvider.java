package by.aurorasoft.fuelsearcher.it.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;

import java.util.stream.Stream;

public final class EighteenthTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments() {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("СГРЕБАНИЕ СЕНА В ВАЛКИ")
                                .tractor("Беларус 920.2")
                                .machinery("Krone Swadro 807")
                                .workingWidth("6.2")
                                .routingLength("Менее 150")
                                .build())
                        .expected(new Fuel(18.4, 2.46))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("СГРЕБАНИЕ СЕНА В ВАЛКИ")
                                .tractor("Беларус 80/82.1+")
                                .machinery("MILLENNIUM V18-7GW")
                                .workingWidth("10.5")
                                .routingLength("401-600")
                                .build())
                        .expected(new Fuel(39.6, 1.28))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("СГРЕБАНИЕ СЕНА В ВАЛКИ")
                                .tractor("Беларус 80/82.1")
                                .machinery("Claas Liner 1650 Twin")
                                .workingWidth("6.8")
                                .routingLength("Более 1000")
                                .build())
                        .expected(new Fuel(29.1, 1.78))
                        .build(),
                //not existing tractor
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("СГРЕБАНИЕ СЕНА В ВАЛКИ")
                                .tractor("not existing")
                                .machinery("Claas Liner 1650 Twin")
                                .workingWidth("6.8")
                                .routingLength("Более 1000")
                                .build())
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("СГРЕБАНИЕ СЕНА В ВАЛКИ")
                                .tractor("Беларус 80/82.1")
                                .machinery("not existing")
                                .workingWidth("6.8")
                                .routingLength("Более 1000")
                                .build())
                        .build(),
                //not existing working width
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("СГРЕБАНИЕ СЕНА В ВАЛКИ")
                                .tractor("Беларус 80/82.1")
                                .machinery("Claas Liner 1650 Twin")
                                .workingWidth("not existing")
                                .routingLength("Более 1000")
                                .build())
                        .build(),
                //not existing routing length
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("СГРЕБАНИЕ СЕНА В ВАЛКИ")
                                .tractor("Беларус 80/82.1")
                                .machinery("Claas Liner 1650 Twin")
                                .workingWidth("6.8")
                                .routingLength("not existing")
                                .build())
                        .build()
        );
    }

}
