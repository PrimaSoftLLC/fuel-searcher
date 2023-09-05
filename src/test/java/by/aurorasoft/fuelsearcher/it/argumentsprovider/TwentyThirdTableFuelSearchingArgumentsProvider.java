package by.aurorasoft.fuelsearcher.it.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;

import java.util.stream.Stream;

public final class TwentyThirdTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments() {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .machinery("BIG X 770")
                                .workingWidth("3.8")
                                .yield("5-7.5")
                                .routingLength("Менее 150")
                                .build())
                        .expected(new Fuel(55.6, 1.97))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .machinery("BIG X 700")
                                .workingWidth("3")
                                .yield("16.5-17.5")
                                .routingLength("150-200")
                                .build())
                        .expected(new Fuel(108.1, 0.98))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .machinery("КВК 800")
                                .workingWidth("3")
                                .yield("16.5-17.5")
                                .routingLength("150-200")
                                .build())
                        .expected(new Fuel(84.6, 1.17))
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .machinery("not existing")
                                .workingWidth("3")
                                .yield("16.5-17.5")
                                .routingLength("150-200")
                                .build())
                        .build(),
                //not existing working width
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .machinery("КВК 800")
                                .workingWidth("not existing")
                                .yield("16.5-17.5")
                                .routingLength("150-200")
                                .build())
                        .build(),
                //not existing yield
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .machinery("КВК 800")
                                .workingWidth("3")
                                .yield("not existing")
                                .routingLength("150-200")
                                .build())
                        .build(),
                //not existing routing length
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .machinery("КВК 800")
                                .workingWidth("3")
                                .yield("16.5-17.5")
                                .routingLength("not existing")
                                .build())
                        .build()
        );
    }

}
