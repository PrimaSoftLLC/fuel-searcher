package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class SixthTableFuelInfoArgumentsProvider extends AbstractTableFuelInfoArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<FuelInfo>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Кировец К-744Р4")
                                .machinery("Доминанта Д-880")
                                .workingWidth("8,8")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 6…8 см")
                                .build(),
                        optionalFuelInfoFactory.apply(22.9, 12.5)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Кировец К-744Р4")
                                .machinery("АДС-6,0")
                                .workingWidth("6,0")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 6…8 см")
                                .build(),
                        optionalFuelInfoFactory.apply(15.5, 12.8)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6,0")
                                .workingWidth("6,0")
                                .routingLength("150–200")
                                .processingDepth("Глубина обработки 10…14 см")
                                .build(),
                        optionalFuelInfoFactory.apply(15.4, 7.7)
                ),
                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ")
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
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ")
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
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ")
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
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6,0")
                                .workingWidth("6,0")
                                .routingLength("10–20")
                                .processingDepth("Глубина обработки 10…14 см")
                                .build(),
                        empty()
                ),
                //not existing processing depth
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ")
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6,0")
                                .workingWidth("6,0")
                                .routingLength("150–200")
                                .processingDepth("Глубина обработки 666666666666666…766666666666666 см")
                                .build(),
                        empty()
                )
        );
    }

}
