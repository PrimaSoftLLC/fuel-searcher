package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.Fuel;
import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

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
                        optionalFuelInfoFactory.apply(25.4, 13.3)
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
                        optionalFuelInfoFactory.apply(110.3, 3.)
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
                        optionalFuelInfoFactory.apply(87.8, 4.3)
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
                        optionalFuelInfoFactory.apply(80.9, 3.1)
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
                        optionalFuelInfoFactory.apply(106.2, 4.6)
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
                        optionalFuelInfoFactory.apply(95.2, 3.)
                )
        );
    }
}
