package by.aurorasoft.fuelinfosearcher.service.searcher.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.Fuel;
import by.aurorasoft.fuelinfosearcher.model.Specification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class TwentyFifthTableFuelInfoSearchingArgumentsProvider extends AbstractTableFuelInfoSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<Fuel>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        Specification.builder()
                                .tableName("УБОРКА КАРТОФЕЛЯ")
                                .soilType("Легкие почвы")
                                .tractor("Беларус 1221")
                                .machinery("КПБ-260-2")
                                .rowWidth("90")
                                .yield("До 15")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelInfoFactory.apply(24.7, 2.33)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("УБОРКА КАРТОФЕЛЯ")
                                .soilType("Легкие почвы")
                                .tractor("New Holland TL 80")
                                .machinery("Grimme 1500 dr")
                                .rowWidth("70")
                                .yield("25-30")
                                .routingLength("150-200")
                                .build(),
                        optionalFuelInfoFactory.apply(64.7, 0.76)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("УБОРКА КАРТОФЕЛЯ")
                                .soilType("Средние почвы")
                                .tractor("New Holland TL 80")
                                .machinery("Grimme 1500 dr")
                                .rowWidth("70")
                                .yield("30-35")
                                .routingLength("150-200")
                                .build(),
                        optionalFuelInfoFactory.apply(69.8, 0.7)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("УБОРКА КАРТОФЕЛЯ")
                                .soilType("Тяжелые почвы")
                                .tractor("New Holland TL 80")
                                .machinery("Grimme 1500 dr")
                                .rowWidth("70")
                                .yield("Свыше 35")
                                .routingLength("150-200")
                                .build(),
                        optionalFuelInfoFactory.apply(72.8, 0.68)
                ),
                //not existing soil type
                Arguments.of(
                        Specification.builder()
                                .tableName("УБОРКА КАРТОФЕЛЯ")
                                .soilType("not existing")
                                .tractor("New Holland TL 80")
                                .machinery("Grimme 1500 dr")
                                .rowWidth("70")
                                .yield("Свыше 35")
                                .routingLength("150-200")
                                .build(),
                        empty()
                ),
                //not existing tractor
                Arguments.of(
                        Specification.builder()
                                .tableName("УБОРКА КАРТОФЕЛЯ")
                                .soilType("Тяжелые почвы")
                                .tractor("not existing")
                                .machinery("Grimme 1500 dr")
                                .rowWidth("70")
                                .yield("Свыше 35")
                                .routingLength("150-200")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        Specification.builder()
                                .tableName("УБОРКА КАРТОФЕЛЯ")
                                .soilType("Тяжелые почвы")
                                .tractor("New Holland TL 80")
                                .machinery("not existing")
                                .rowWidth("70")
                                .yield("Свыше 35")
                                .routingLength("150-200")
                                .build(),
                        empty()
                ),
                //not existing row width
                Arguments.of(
                        Specification.builder()
                                .tableName("УБОРКА КАРТОФЕЛЯ")
                                .soilType("Тяжелые почвы")
                                .tractor("New Holland TL 80")
                                .machinery("Grimme 1500 dr")
                                .rowWidth("not existing")
                                .yield("Свыше 35")
                                .routingLength("150-200")
                                .build(),
                        empty()
                ),
                //not existing yield
                Arguments.of(
                        Specification.builder()
                                .tableName("УБОРКА КАРТОФЕЛЯ")
                                .soilType("Тяжелые почвы")
                                .tractor("New Holland TL 80")
                                .machinery("Grimme 1500 dr")
                                .rowWidth("70")
                                .yield("not existing")
                                .routingLength("150-200")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        Specification.builder()
                                .tableName("УБОРКА КАРТОФЕЛЯ")
                                .soilType("Тяжелые почвы")
                                .tractor("New Holland TL 80")
                                .machinery("Grimme 1500 dr")
                                .rowWidth("70")
                                .yield("Свыше 35")
                                .routingLength("not existing")
                                .build(),
                        empty()
                )
        );
    }

}