package by.aurorasoft.fuelsearcher.it.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;

import java.util.stream.Stream;

public final class NinthTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments() {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("John Deere 8400")
                                .machinery("Tempo V-18")
                                .workingWidth("8.1")
                                .routingLength("Менее 150")
                                .sowingNorm("Норма высева семян 15 кг/га")
                                .build())
                        .expected(new Fuel(17.9, 3.45))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("Беларус 1221")
                                .machinery("Meca V-4")
                                .workingWidth("5.4")
                                .routingLength("601-1000")
                                .sowingNorm("Норма высева семян 30 кг/га")
                                .build())
                        .expected(new Fuel(15.6, 2.72))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("Беларус 80/82")
                                .machinery("Tehnic NC")
                                .workingWidth("3.6")
                                .routingLength("601-1000")
                                .sowingNorm("Норма высева семян 45 кг/га")
                                .build())
                        .expected(new Fuel(10.3, 2.64))
                        .build(),
                //not existing tractor
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("not existing")
                                .machinery("Tehnic NC")
                                .workingWidth("3.6")
                                .routingLength("601-1000")
                                .sowingNorm("Норма высева семян 45 кг/га")
                                .build())
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("Беларус 80/82")
                                .machinery("not existing")
                                .workingWidth("3.6")
                                .routingLength("601-1000")
                                .sowingNorm("Норма высева семян 45 кг/га")
                                .build())
                        .build(),
                //not existing working width
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("Беларус 80/82")
                                .machinery("Tehnic NC")
                                .workingWidth("not existing")
                                .routingLength("601-1000")
                                .sowingNorm("Норма высева семян 45 кг/га")
                                .build())
                        .build(),
                //not existing routing length
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("Беларус 80/82")
                                .machinery("Tehnic NC")
                                .workingWidth("3.6")
                                .routingLength("not existing")
                                .sowingNorm("Норма высева семян 45 кг/га")
                                .build())
                        .build(),
                //not existing sowing norm
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПОСЕВ САХАРНОЙ СВЕКЛЫ")
                                .tractor("Беларус 80/82")
                                .machinery("Tehnic NC")
                                .workingWidth("3.6")
                                .routingLength("601-1000")
                                .sowingNorm("not existing")
                                .build())
                        .build()
        );
    }

}