package by.aurorasoft.fuelsearcher.it.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;

import java.util.stream.Stream;

public final class SixthTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments() {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Кировец К-744Р4")
                                .machinery("Доминанта Д-880")
                                .workingWidth("8.8")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 6...8 см")
                                .build())
                        .expected(new Fuel(22.9, 12.5))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Кировец К-744Р4")
                                .machinery("АДС-6")
                                .workingWidth("6")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 6...8 см")
                                .build())
                        .expected(new Fuel(15.5, 12.8))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6")
                                .workingWidth("6")
                                .routingLength("150-200")
                                .processingDepth("Глубина обработки 10...14 см")
                                .build())
                        .expected(new Fuel(15.4, 7.7))
                        .build(),
                //not existing tractor
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ")
                                .tractor("not existing")
                                .machinery("АКЧ-6")
                                .workingWidth("6")
                                .routingLength("150-200")
                                .processingDepth("Глубина обработки 10...14 см")
                                .build())
                        .build(),
                //not existing machinery
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Беларус 2022")
                                .machinery("not existing")
                                .workingWidth("6")
                                .routingLength("150-200")
                                .processingDepth("Глубина обработки 10...14 см")
                                .build())
                        .build(),
                //not existing working width
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6")
                                .workingWidth("not existing")
                                .routingLength("150-200")
                                .processingDepth("Глубина обработки 10...14 см")
                                .build())
                        .build(),
                //not existing routing length
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6")
                                .workingWidth("6")
                                .routingLength("not existing")
                                .processingDepth("Глубина обработки 10...14 см")
                                .build())
                        .build(),
                //not existing processing depth
                FuelSearchingArguments.builder()
                        .specification(FuelSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6")
                                .workingWidth("6")
                                .routingLength("150-200")
                                .processingDepth("not existing")
                                .build())
                        .build()
        );
    }

}
