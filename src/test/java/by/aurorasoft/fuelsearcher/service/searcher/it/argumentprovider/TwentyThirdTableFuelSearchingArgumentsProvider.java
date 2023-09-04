package by.aurorasoft.fuelsearcher.service.searcher.it.argumentprovider;

import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class TwentyThirdTableFuelSearchingArgumentsProvider extends AbstractTableFuelSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<Fuel>> optionalFuelFactory) {
        return Stream.of(
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .machinery("BIG X 770")
                                .workingWidth("3.8")
                                .yield("5-7.5")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelFactory.apply(55.6, 1.97)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .machinery("BIG X 700")
                                .workingWidth("3")
                                .yield("16.5-17.5")
                                .routingLength("150-200")
                                .build(),
                        optionalFuelFactory.apply(108.1, 0.98)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .machinery("КВК 800")
                                .workingWidth("3")
                                .yield("16.5-17.5")
                                .routingLength("150-200")
                                .build(),
                        optionalFuelFactory.apply(84.6, 1.17)
                ),
                //not existing machinery
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .machinery("not existing")
                                .workingWidth("3")
                                .yield("16.5-17.5")
                                .routingLength("150-200")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .machinery("КВК 800")
                                .workingWidth("not existing")
                                .yield("16.5-17.5")
                                .routingLength("150-200")
                                .build(),
                        empty()
                ),
                //not existing yield
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .machinery("КВК 800")
                                .workingWidth("3")
                                .yield("not existing")
                                .routingLength("150-200")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .machinery("КВК 800")
                                .workingWidth("3")
                                .yield("16.5-17.5")
                                .routingLength("not existing")
                                .build(),
                        empty()
                )
        );
    }

}
