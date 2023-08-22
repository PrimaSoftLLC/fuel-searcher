package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.Fuel;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class FourthTableFuelInfoSearchingArgumentsProvider extends AbstractTableFuelInfoSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<Fuel>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Clas Xerion 5000")
                                .machinery("Köckerling Allrounder 1200")
                                .workingWidth("12")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 6...8 см")
                                .build(),
                        optionalFuelInfoFactory.apply(26.4, 14.1)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Clas Xerion 5000")
                                .machinery("Köckerling Allrounder 1200")
                                .workingWidth("12")
                                .routingLength("Более 1000")
                                .processingDepth("Глубина обработки 6...8 см")
                                .build(),
                        optionalFuelInfoFactory.apply(58.2, 7.5)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Беларус 3522")
                                .machinery("АПМ-8")
                                .workingWidth("8")
                                .routingLength("150-200")
                                .processingDepth("Глубина обработки 6...8 см")
                                .build(),
                        optionalFuelInfoFactory.apply(24.0, 11.1)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Беларус 3022")
                                .machinery("КФУ-7.3")
                                .workingWidth("7.3")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 6...8 см")
                                .build(),
                        optionalFuelInfoFactory.apply(17.3, 12.7)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Беларус 1522")
                                .machinery("Horsch Terrano 4FX")
                                .workingWidth("4")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 14...20 см")
                                .build(),
                        optionalFuelInfoFactory.apply(10.5, 8.7)
                ),
                //not existing tractor
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("not existing")
                                .machinery("Horsch Terrano 4FX")
                                .workingWidth("4")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 14…20 см")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Беларус 1522")
                                .machinery("not existing")
                                .workingWidth("4")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 14...20 см")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Беларус 1522")
                                .machinery("Horsch Terrano 4FX")
                                .workingWidth("not existing")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 14...20 см")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Беларус 1522")
                                .machinery("Horsch Terrano 4FX")
                                .workingWidth("4")
                                .routingLength("not existing")
                                .processingDepth("Глубина обработки 14...20 см")
                                .build(),
                        empty()
                ),
                //not existing processing depth
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ")
                                .tractor("Беларус 1522")
                                .machinery("Horsch Terrano 4FX")
                                .workingWidth("4")
                                .routingLength("Менее 150")
                                .processingDepth("not existing")
                                .build(),
                        empty()
                )
        );
    }

}
