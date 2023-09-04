package by.aurorasoft.fuelsearcher.it.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;

import java.util.stream.Stream;

public final class FourthTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments() {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Clas Xerion 5000")
                                .machinery("Köckerling Allrounder 1200")
                                .workingWidth("12")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 6...8 см")
                                .build())
                        .expected(new Fuel(26.4, 14.1))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Clas Xerion 5000")
                                .machinery("Köckerling Allrounder 1200")
                                .workingWidth("12")
                                .routingLength("Более 1000")
                                .processingDepth("Глубина обработки 6...8 см")
                                .build())
                        .expected(new Fuel(58.2, 7.5))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Беларус 3522")
                                .machinery("АПМ-8")
                                .workingWidth("8")
                                .routingLength("150-200")
                                .processingDepth("Глубина обработки 6...8 см")
                                .build())
                        .expected(new Fuel(24.0, 11.1))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Беларус 3022")
                                .machinery("КФУ-7.3")
                                .workingWidth("7.3")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 6...8 см")
                                .build())
                        .expected(new Fuel(17.3, 12.7))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Беларус 1522")
                                .machinery("Horsch Terrano 4FX")
                                .workingWidth("4")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 14...20 см")
                                .build())
                        .expected(new Fuel(10.5, 8.7))
                        .build(),
                //not existing tractor
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("not existing")
                                .machinery("Horsch Terrano 4FX")
                                .workingWidth("4")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 14…20 см")
                                .build())
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Беларус 1522")
                                .machinery("not existing")
                                .workingWidth("4")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 14...20 см")
                                .build())
                        .build(),
                //not existing working width
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Беларус 1522")
                                .machinery("Horsch Terrano 4FX")
                                .workingWidth("not existing")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 14...20 см")
                                .build())
                        .build(),
                //not existing routing length
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Беларус 1522")
                                .machinery("Horsch Terrano 4FX")
                                .workingWidth("4")
                                .routingLength("not existing")
                                .processingDepth("Глубина обработки 14...20 см")
                                .build())
                        .build(),
                //not existing processing depth
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Беларус 1522")
                                .machinery("Horsch Terrano 4FX")
                                .workingWidth("4")
                                .routingLength("Менее 150")
                                .processingDepth("not existing")
                                .build())
                        .build()
        );
    }

}
