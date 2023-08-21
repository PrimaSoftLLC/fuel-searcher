package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.Fuel;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class TwentyFourthTableFuelInfoSearchingArgumentsProvider extends AbstractTableFuelInfoSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<Fuel>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:1")
                                .yield("До 1,4")
                                .workingWidth("10,5")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelInfoFactory.apply(24.1, 9.5)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:1")
                                .yield("4,9...5,3")
                                .workingWidth("12")
                                .routingLength("401...600")
                                .build(),
                        optionalFuelInfoFactory.apply(94.7, 3.3)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:1,5")
                                .yield("Св. 6")
                                .workingWidth("10,5")
                                .routingLength("201...300")
                                .build(),
                        optionalFuelInfoFactory.apply(87.8, 3.)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:1,5")
                                .yield("5,4...5,7")
                                .workingWidth("12")
                                .routingLength("151...200")
                                .build(),
                        optionalFuelInfoFactory.apply(74.1, 3.2)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("3...3,3")
                                .workingWidth("10,5")
                                .routingLength("Более 1000")
                                .build(),
                        optionalFuelInfoFactory.apply(99.6, 3.4)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("4...4,2")
                                .workingWidth("12")
                                .routingLength("201...300")
                                .build(),
                        optionalFuelInfoFactory.apply(84.4, 3.2)
                ),

                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                //TODO: replace « by something
                                .combine("КЗС-1624-1 «ПАЛЕССЕ GS-1624-1» - 490 л.с.")
                                .weightRatioGrainToStraw("1:1")
                                .yield("До 1,4")
                                .workingWidth("9,2")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelInfoFactory.apply(21.7, 16.)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                //TODO: replace « by something
                                .combine("КЗС-1624-1 «ПАЛЕССЕ GS-1624-1» - 490 л.с.")
                                .weightRatioGrainToStraw("1:1")
                                .yield("7,1...7,5")
                                .workingWidth("9,2")
                                .routingLength("601...1000")
                                .build(),
                        optionalFuelInfoFactory.apply(96.9, 4.8)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                //TODO: replace « by something
                                .combine("КЗС-1624-1 «ПАЛЕССЕ GS-1624-1» - 490 л.с.")
                                .weightRatioGrainToStraw("1:1,5")
                                .yield("3,3...3,6")
                                .workingWidth("9,2")
                                .routingLength("301...400")
                                .build(),
                        optionalFuelInfoFactory.apply(73.4, 6.3)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                //TODO: replace « by something
                                .combine("КЗС-1624-1 «ПАЛЕССЕ GS-1624-1» - 490 л.с.")
                                .weightRatioGrainToStraw("1:1,5")
                                .yield("5,7...6")
                                .workingWidth("9,2")
                                .routingLength("151...200")
                                .build(),
                        optionalFuelInfoFactory.apply(61.8, 5.7)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                //TODO: replace « by something
                                .combine("КЗС-1624-1 «ПАЛЕССЕ GS-1624-1» - 490 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("2,5...2,7")
                                .workingWidth("9,2")
                                .routingLength("301...400")
                                .build(),
                        optionalFuelInfoFactory.apply(70.5, 6.5)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                //TODO: replace « by something
                                .combine("КЗС-1624-1 «ПАЛЕССЕ GS-1624-1» - 490 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("4,7...5")
                                .workingWidth("9,2")
                                .routingLength("201...300")
                                .build(),
                        optionalFuelInfoFactory.apply(73.4, 5.3)
                ),

                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-760 - 461 л.с.")
                                .weightRatioGrainToStraw("1:1")
                                .yield("До 1,4")
                                .workingWidth("9")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelInfoFactory.apply(21.5, 9.4)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-760 - 461 л.с.")
                                .weightRatioGrainToStraw("1:1")
                                .yield("Св. 7,5")
                                .workingWidth("9")
                                .routingLength("Более 1000")
                                .build(),
                        optionalFuelInfoFactory.apply(119.2, 2.4)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-760 - 461 л.с.")
                                .weightRatioGrainToStraw("1:1,5")
                                .yield("2,7...3")
                                .workingWidth("9")
                                .routingLength("201...300")
                                .build(),
                        optionalFuelInfoFactory.apply(53.6, 4.6)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-760 - 461 л.с.")
                                .weightRatioGrainToStraw("1:1,5")
                                .yield("5,4...5,7")
                                .workingWidth("9")
                                .routingLength("151...200")
                                .build(),
                        optionalFuelInfoFactory.apply(65.7, 3.2)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-760 - 461 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("4,2...4,5")
                                .workingWidth("9")
                                .routingLength("601...1000")
                                .build(),
                        optionalFuelInfoFactory.apply(105.5, 2.7)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-760 - 461 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("3,3...3,5")
                                .workingWidth("9")
                                .routingLength("601...1000")
                                .build(),
                        optionalFuelInfoFactory.apply(89.5, 3.2)
                ),

                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("КЗС-3219 КР «ПАЛЕССЕ GS-3219 КР» - 390 л.с.")
                                .weightRatioGrainToStraw("1:1")
                                .yield("До 1,4")
                                .workingWidth("7")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelInfoFactory.apply(18.7, 13.)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("КЗС-3219 КР «ПАЛЕССЕ GS-3219 КР» - 390 л.с.")
                                .weightRatioGrainToStraw("1:1")
                                .yield("4,1...4,5")
                                .workingWidth("7")
                                .routingLength("401...600")
                                .build(),
                        optionalFuelInfoFactory.apply(65.3, 4.9)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("КЗС-3219 КР «ПАЛЕССЕ GS-3219 КР» - 390 л.с.")
                                .weightRatioGrainToStraw("1:1,5")
                                .yield("4,8...5,1")
                                .workingWidth("7")
                                .routingLength("201...300")
                                .build(),
                        optionalFuelInfoFactory.apply(60.8, 4.7)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("КЗС-3219 КР «ПАЛЕССЕ GS-3219 КР» - 390 л.с.")
                                .weightRatioGrainToStraw("1:1,5")
                                .yield("1,8...2,1")
                                .workingWidth("7")
                                .routingLength("401...600")
                                .build(),
                        optionalFuelInfoFactory.apply(46.7, 6.9)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("КЗС-3219 КР «ПАЛЕССЕ GS-3219 КР» - 390 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("1,8...2")
                                .workingWidth("7")
                                .routingLength("201...300")
                                .build(),
                        optionalFuelInfoFactory.apply(41.2, 7.)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("КЗС-3219 КР «ПАЛЕССЕ GS-3219 КР» - 390 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("3,5...3,7")
                                .workingWidth("7")
                                .routingLength("601...1000")
                                .build(),
                        optionalFuelInfoFactory.apply(79.1, 4.2)
                ),

                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-560 - 360 л.с.")
                                .weightRatioGrainToStraw("1:1")
                                .yield("До 1,4")
                                .workingWidth("6,6")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelInfoFactory.apply(16.8, 11.7)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-560 - 360 л.с.")
                                .weightRatioGrainToStraw("1:1")
                                .yield("4,9...5,3")
                                .workingWidth("6,6")
                                .routingLength("301...400")
                                .build(),
                        optionalFuelInfoFactory.apply(64., 3.9)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-560 - 360 л.с.")
                                .weightRatioGrainToStraw("1:1,5")
                                .yield("3,6...3,9")
                                .workingWidth("6,6")
                                .routingLength("151...200")
                                .build(),
                        optionalFuelInfoFactory.apply(42.8, 4.9)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-560 - 360 л.с.")
                                .weightRatioGrainToStraw("1:1,5")
                                .yield("4,8...5,1")
                                .workingWidth("6,6")
                                .routingLength("Более 1000")
                                .build(),
                        optionalFuelInfoFactory.apply(81.1, 3.3)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-560 - 360 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("4...4,2")
                                .workingWidth("6,6")
                                .routingLength("151...200")
                                .build(),
                        optionalFuelInfoFactory.apply(50.1, 4.1)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("CLAAS LEXION-560 - 360 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("4,7...5")
                                .workingWidth("6,6")
                                .routingLength("301...400")
                                .build(),
                        optionalFuelInfoFactory.apply(82.2, 3.)
                ),

                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("JOHN DEERE 9640WTS-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:1")
                                .yield("До 1,4")
                                .workingWidth("6")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelInfoFactory.apply(13.8, 12.)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("JOHN DEERE 9640WTS-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:1")
                                .yield("4,5...4,9")
                                .workingWidth("6")
                                .routingLength("601...1000")
                                .build(),
                        optionalFuelInfoFactory.apply(48.6, 3.8)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("JOHN DEERE 9640WTS-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:1,5")
                                .yield("1,8...2,1")
                                .workingWidth("6")
                                .routingLength("301...400")
                                .build(),
                        optionalFuelInfoFactory.apply(29.2, 6.3)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("JOHN DEERE 9640WTS-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:1,5")
                                .yield("5,7...6")
                                .workingWidth("6")
                                .routingLength("151...200")
                                .build(),
                        optionalFuelInfoFactory.apply(45.6, 3.6)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("JOHN DEERE 9640WTS-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("0,9...1,2")
                                .workingWidth("6")
                                .routingLength("401...600")
                                .build(),
                        optionalFuelInfoFactory.apply(23.9, 7.7)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("JOHN DEERE 9640WTS-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("4,5...4,7")
                                .workingWidth("6")
                                .routingLength("151...200")
                                .build(),
                        optionalFuelInfoFactory.apply(43.8, 3.8)
                ),
                //not existing combine
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("not existing")
                                .weightRatioGrainToStraw("1:2")
                                .yield("4,5...4,7")
                                .workingWidth("6")
                                .routingLength("151...200")
                                .build(),
                        empty()
                ),
                //not existing weight ratio grain to straw
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("JOHN DEERE 9640WTS-770 - 524 л.с.")
                                .weightRatioGrainToStraw("not existing")
                                .yield("4,5...4,7")
                                .workingWidth("6")
                                .routingLength("151...200")
                                .build(),
                        empty()
                ),
                //not existing yield
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("JOHN DEERE 9640WTS-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("not existing")
                                .workingWidth("6")
                                .routingLength("151...200")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("JOHN DEERE 9640WTS-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("4,5...4,7")
                                .workingWidth("not existing")
                                .routingLength("151...200")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ")
                                .combine("JOHN DEERE 9640WTS-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("4,5...4,7")
                                .workingWidth("6")
                                .routingLength("not existing")
                                .build(),
                        empty()
                )
        );
    }
}
