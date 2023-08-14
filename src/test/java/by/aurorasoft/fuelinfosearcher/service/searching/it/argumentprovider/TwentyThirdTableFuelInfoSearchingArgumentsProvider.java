package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class TwentyThirdTableFuelInfoSearchingArgumentsProvider extends AbstractTableFuelInfoSeacrhingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<FuelInfo>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .machinery("BIG X 770")
                                .workingWidth("3,8")
                                .yield("5,0–7,5")
                                .routingLength("Менее 150")
                                .build(),
                        optionalFuelInfoFactory.apply(55.6, 1.97)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .machinery("BIG X 700")
                                .workingWidth("3,0")
                                .yield("16,5–17,5")
                                .routingLength("151…200")
                                .build(),
                        optionalFuelInfoFactory.apply(108.1, 0.98)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .machinery("КВК 800")
                                .workingWidth("3,0")
                                .yield("16,5–17,5")
                                .routingLength("151…200")
                                .build(),
                        optionalFuelInfoFactory.apply(84.6, 1.17)
                ),
                //not existing machinery
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .machinery("not existing")
                                .workingWidth("3,0")
                                .yield("16,5–17,5")
                                .routingLength("151…200")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .machinery("КВК 800")
                                .workingWidth("not existing")
                                .yield("16,5–17,5")
                                .routingLength("151…200")
                                .build(),
                        empty()
                ),
                //not existing yield
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .machinery("КВК 800")
                                .workingWidth("3,0")
                                .yield("not existing")
                                .routingLength("151…200")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА")
                                .machinery("КВК 800")
                                .workingWidth("3,0")
                                .yield("16,5–17,5")
                                .routingLength("not existing")
                                .build(),
                        empty()
                )
        );
    }

}