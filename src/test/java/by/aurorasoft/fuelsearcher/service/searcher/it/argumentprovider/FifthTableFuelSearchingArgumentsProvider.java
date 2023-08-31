package by.aurorasoft.fuelsearcher.service.searcher.it.argumentprovider;

import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.Specification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class FifthTableFuelSearchingArgumentsProvider extends AbstractTableFuelSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<Fuel>> optionalFuelFactory) {
        return Stream.of(
                Arguments.of(
                        Specification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ")
                                .tractor("Кировец К-744Р4")
                                .machinery("Доминанта Д-880")
                                .workingWidth("8.8")
                                .routingLength("Менее 150")
                                .processingDepth("Глубина обработки 6...8 см")
                                .build(),
                        optionalFuelFactory.apply(23.7, 12.0)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ")
                                .tractor("Беларус 3022")
                                .machinery("АПД-7.5")
                                .workingWidth("7.5")
                                .routingLength("150-200")
                                .processingDepth("Глубина обработки 6...8 см")
                                .build(),
                        optionalFuelFactory.apply(20.4, 8.6)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ")
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6")
                                .workingWidth("6")
                                .routingLength("150-200")
                                .processingDepth("Глубина обработки 10...14 см")
                                .build(),
                        optionalFuelFactory.apply(16.2, 7.3)
                ),
                //not existing tractor
                Arguments.of(
                        Specification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ")
                                .tractor("not existing")
                                .machinery("АКЧ-6")
                                .workingWidth("6")
                                .routingLength("150-200")
                                .processingDepth("Глубина обработки 10...14 см")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        Specification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ")
                                .tractor("Беларус 2022")
                                .machinery("not existing")
                                .workingWidth("6")
                                .routingLength("150-200")
                                .processingDepth("Глубина обработки 10...14 см")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        Specification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ")
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6")
                                .workingWidth("not existing")
                                .routingLength("150-200")
                                .processingDepth("Глубина обработки 10...14 см")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        Specification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ")
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6")
                                .workingWidth("6")
                                .routingLength("not existing")
                                .processingDepth("Глубина обработки 10...14 см")
                                .build(),
                        empty()
                ),
                //not existing processing depth
                Arguments.of(
                        Specification.builder()
                                .tableName("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ")
                                .tractor("Беларус 2022")
                                .machinery("АКЧ-6")
                                .workingWidth("6")
                                .routingLength("150-200")
                                .processingDepth("not existing")
                                .build(),
                        empty()
                )
        );
    }

}
