package by.aurorasoft.fuelsearcher.service.searcher.it.argumentprovider;

import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.Specification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class TwentyFourthTableFuelSearchingArgumentsProvider extends AbstractTableFuelSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<Fuel>> optionalFuelFactory) {
        return Stream.of(
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:1")
                                .yield("До 1.4")
                                .workingWidth("10.5")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelFactory.apply(24.1, 9.5)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:1")
                                .yield("4.9...5.3")
                                .workingWidth("12")
                                .routingLength("401-600")
                                .build(),
                        optionalFuelFactory.apply(94.7, 3.3)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:1.5")
                                .yield("Св. 6")
                                .workingWidth("10.5")
                                .routingLength("201-300")
                                .build(),
                        optionalFuelFactory.apply(87.8, 3.)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:1.5")
                                .yield("5.4...5.7")
                                .workingWidth("12")
                                .routingLength("150-200")
                                .build(),
                        optionalFuelFactory.apply(74.1, 3.2)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("3...3.3")
                                .workingWidth("10.5")
                                .routingLength("Более 1000")
                                .build(),
                        optionalFuelFactory.apply(99.6, 3.4)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("4...4.2")
                                .workingWidth("12")
                                .routingLength("201-300")
                                .build(),
                        optionalFuelFactory.apply(84.4, 3.2)
                ),

                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("КЗС-1624-1 \"ПАЛЕССЕ GS-1624-1\" - 490 л.с.")
                                .weightRatioGrainToStraw("1:1")
                                .yield("До 1.4")
                                .workingWidth("9.2")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelFactory.apply(21.7, 16.)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("КЗС-1624-1 \"ПАЛЕССЕ GS-1624-1\" - 490 л.с.")
                                .weightRatioGrainToStraw("1:1")
                                .yield("7.1...7.5")
                                .workingWidth("9.2")
                                .routingLength("601-1000")
                                .build(),
                        optionalFuelFactory.apply(96.9, 4.8)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("КЗС-1624-1 \"ПАЛЕССЕ GS-1624-1\" - 490 л.с.")
                                .weightRatioGrainToStraw("1:1.5")
                                .yield("3.3...3.6")
                                .workingWidth("9.2")
                                .routingLength("301-400")
                                .build(),
                        optionalFuelFactory.apply(73.4, 6.3)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("КЗС-1624-1 \"ПАЛЕССЕ GS-1624-1\" - 490 л.с.")
                                .weightRatioGrainToStraw("1:1.5")
                                .yield("5.7...6")
                                .workingWidth("9.2")
                                .routingLength("150-200")
                                .build(),
                        optionalFuelFactory.apply(61.8, 5.7)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("КЗС-1624-1 \"ПАЛЕССЕ GS-1624-1\" - 490 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("2.5...2.7")
                                .workingWidth("9.2")
                                .routingLength("301-400")
                                .build(),
                        optionalFuelFactory.apply(70.5, 6.5)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("КЗС-1624-1 \"ПАЛЕССЕ GS-1624-1\" - 490 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("4.7...5")
                                .workingWidth("9.2")
                                .routingLength("201-300")
                                .build(),
                        optionalFuelFactory.apply(73.4, 5.3)
                ),

                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-760 - 461 л.с.")
                                .weightRatioGrainToStraw("1:1")
                                .yield("До 1.4")
                                .workingWidth("9")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelFactory.apply(21.5, 9.4)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-760 - 461 л.с.")
                                .weightRatioGrainToStraw("1:1")
                                .yield("Св. 7.5")
                                .workingWidth("9")
                                .routingLength("Более 1000")
                                .build(),
                        optionalFuelFactory.apply(119.2, 2.4)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-760 - 461 л.с.")
                                .weightRatioGrainToStraw("1:1.5")
                                .yield("2.7...3")
                                .workingWidth("9")
                                .routingLength("201-300")
                                .build(),
                        optionalFuelFactory.apply(53.6, 4.6)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-760 - 461 л.с.")
                                .weightRatioGrainToStraw("1:1.5")
                                .yield("5.4...5.7")
                                .workingWidth("9")
                                .routingLength("150-200")
                                .build(),
                        optionalFuelFactory.apply(65.7, 3.2)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-760 - 461 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("4.2...4.5")
                                .workingWidth("9")
                                .routingLength("601-1000")
                                .build(),
                        optionalFuelFactory.apply(105.5, 2.7)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-760 - 461 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("3.3...3.5")
                                .workingWidth("9")
                                .routingLength("601-1000")
                                .build(),
                        optionalFuelFactory.apply(89.5, 3.2)
                ),

                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("КЗС-3219 КР \"ПАЛЕССЕ GS-3219 КР\" - 390 л.с.")
                                .weightRatioGrainToStraw("1:1")
                                .yield("До 1.4")
                                .workingWidth("7")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelFactory.apply(18.7, 13.)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("КЗС-3219 КР \"ПАЛЕССЕ GS-3219 КР\" - 390 л.с.")
                                .weightRatioGrainToStraw("1:1")
                                .yield("4.1...4.5")
                                .workingWidth("7")
                                .routingLength("401-600")
                                .build(),
                        optionalFuelFactory.apply(65.3, 4.9)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("КЗС-3219 КР \"ПАЛЕССЕ GS-3219 КР\" - 390 л.с.")
                                .weightRatioGrainToStraw("1:1.5")
                                .yield("4.8...5.1")
                                .workingWidth("7")
                                .routingLength("201-300")
                                .build(),
                        optionalFuelFactory.apply(60.8, 4.7)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("КЗС-3219 КР \"ПАЛЕССЕ GS-3219 КР\" - 390 л.с.")
                                .weightRatioGrainToStraw("1:1.5")
                                .yield("1.8...2.1")
                                .workingWidth("7")
                                .routingLength("401-600")
                                .build(),
                        optionalFuelFactory.apply(46.7, 6.9)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("КЗС-3219 КР \"ПАЛЕССЕ GS-3219 КР\" - 390 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("1.8...2")
                                .workingWidth("7")
                                .routingLength("201-300")
                                .build(),
                        optionalFuelFactory.apply(41.2, 7.)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("КЗС-3219 КР \"ПАЛЕССЕ GS-3219 КР\" - 390 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("3.5...3.7")
                                .workingWidth("7")
                                .routingLength("601-1000")
                                .build(),
                        optionalFuelFactory.apply(79.1, 4.2)
                ),

                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-560 - 360 л.с.")
                                .weightRatioGrainToStraw("1:1")
                                .yield("До 1.4")
                                .workingWidth("6.6")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelFactory.apply(16.8, 11.7)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-560 - 360 л.с.")
                                .weightRatioGrainToStraw("1:1")
                                .yield("4.9...5.3")
                                .workingWidth("6.6")
                                .routingLength("301-400")
                                .build(),
                        optionalFuelFactory.apply(64., 3.9)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-560 - 360 л.с.")
                                .weightRatioGrainToStraw("1:1.5")
                                .yield("3.6...3.9")
                                .workingWidth("6.6")
                                .routingLength("150-200")
                                .build(),
                        optionalFuelFactory.apply(42.8, 4.9)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-560 - 360 л.с.")
                                .weightRatioGrainToStraw("1:1.5")
                                .yield("4.8...5.1")
                                .workingWidth("6.6")
                                .routingLength("Более 1000")
                                .build(),
                        optionalFuelFactory.apply(81.1, 3.3)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-560 - 360 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("4...4.2")
                                .workingWidth("6.6")
                                .routingLength("150-200")
                                .build(),
                        optionalFuelFactory.apply(50.1, 4.1)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-560 - 360 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("4.7...5")
                                .workingWidth("6.6")
                                .routingLength("301-400")
                                .build(),
                        optionalFuelFactory.apply(82.2, 3.)
                ),

                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("JOHN DEERE 9640WTS-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:1")
                                .yield("До 1.4")
                                .workingWidth("6")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelFactory.apply(13.8, 12.)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("JOHN DEERE 9640WTS-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:1")
                                .yield("4.5...4.9")
                                .workingWidth("6")
                                .routingLength("601-1000")
                                .build(),
                        optionalFuelFactory.apply(48.6, 3.8)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("JOHN DEERE 9640WTS-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:1.5")
                                .yield("1.8...2.1")
                                .workingWidth("6")
                                .routingLength("301-400")
                                .build(),
                        optionalFuelFactory.apply(29.2, 6.3)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("JOHN DEERE 9640WTS-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:1.5")
                                .yield("5.7...6")
                                .workingWidth("6")
                                .routingLength("150-200")
                                .build(),
                        optionalFuelFactory.apply(45.6, 3.6)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("JOHN DEERE 9640WTS-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("0.9...1.2")
                                .workingWidth("6")
                                .routingLength("401-600")
                                .build(),
                        optionalFuelFactory.apply(23.9, 7.7)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("JOHN DEERE 9640WTS-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("4.5...4.7")
                                .workingWidth("6")
                                .routingLength("150-200")
                                .build(),
                        optionalFuelFactory.apply(43.8, 3.8)
                ),
                //not existing combine
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("not existing")
                                .weightRatioGrainToStraw("1:2")
                                .yield("4.5...4.7")
                                .workingWidth("6")
                                .routingLength("150-200")
                                .build(),
                        empty()
                ),
                //not existing weight ratio grain to straw
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("JOHN DEERE 9640WTS-770 - 524 л.с.")
                                .weightRatioGrainToStraw("not existing")
                                .yield("4.5...4.7")
                                .workingWidth("6")
                                .routingLength("150-200")
                                .build(),
                        empty()
                ),
                //not existing yield
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("JOHN DEERE 9640WTS-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("not existing")
                                .workingWidth("6")
                                .routingLength("150-200")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("JOHN DEERE 9640WTS-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("4.5...4.7")
                                .workingWidth("not existing")
                                .routingLength("150-200")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("JOHN DEERE 9640WTS-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("4.5...4.7")
                                .workingWidth("6")
                                .routingLength("not existing")
                                .build(),
                        empty()
                )
        );
    }
}