package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class FifthTableFuelInfoSearchingArgumentsProvider extends AbstractTableFuelInfoSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<FuelInfo>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ")
                                .tractor("Кировец К-744Р4")
                                .machinery("Доминанта Д-880")
                                .workingWidth("8,8")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 6…8 см")
                                .build(),
                        optionalFuelInfoFactory.apply(23.7, 12.0)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ")
                                .tractor("Беларус 3022")
                                .machinery("АПД-7,5")
                                .workingWidth("7,5")
                                .routingLength("150–200")
                                .processingDepth("Глубина обработки 6…8 см")
                                .build(),
                        optionalFuelInfoFactory.apply(20.4, 8.6)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ")
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6,0")
                                .workingWidth("6,0")
                                .routingLength("150–200")
                                .processingDepth("Глубина обработки 10…14 см")
                                .build(),
                        optionalFuelInfoFactory.apply(16.2, 7.3)
                ),
                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ")
                                .tractor("not existing")
                                .machinery("АКЧ-6,0")
                                .workingWidth("6,0")
                                .routingLength("150–200")
                                .processingDepth("Глубина обработки 10…14 см")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ")
                                .tractor("Беларус 2022")
                                .machinery("not existing")
                                .workingWidth("6,0")
                                .routingLength("150–200")
                                .processingDepth("Глубина обработки 10…14 см")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ")
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6,0")
                                .workingWidth("-1,0")
                                .routingLength("150–200")
                                .processingDepth("Глубина обработки 10…14 см")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ")
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6,0")
                                .workingWidth("6,0")
                                .routingLength("20-21")
                                .processingDepth("Глубина обработки 10…14 см")
                                .build(),
                        empty()
                ),
                //not existing processing depth
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ")
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6,0")
                                .workingWidth("6,0")
                                .routingLength("150–200")
                                .processingDepth("Глубина обработки 66666666666666666666…76666666666666666666 см")
                                .build(),
                        empty()
                )
        );
    }

}
