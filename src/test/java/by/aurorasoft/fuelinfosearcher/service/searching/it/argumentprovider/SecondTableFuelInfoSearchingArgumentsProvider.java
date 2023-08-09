package by.aurorasoft.fuelinfosearcher.service.searching.it.argumentprovider;

import by.aurorasoft.fuelinfosearcher.model.FuelInfo;
import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class SecondTableFuelInfoSearchingArgumentsProvider extends AbstractTableFuelInfoSeacrhingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<FuelInfo>> optionalFuelInfoFactory) {
        return Stream.of(
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА СТЕРНИ")
                                .tractor("CASE II Steiger 550")
                                //TODO: do without nbsp symbol
                                .machinery("Lemken EuroTitan 10\u00A08+3+1")
                                .corpusCount("12")
                                .ploughingDepth("18–20")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build(),
                        optionalFuelInfoFactory.apply(13.8, 20.9)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА СТЕРНИ")
                                .tractor("Кировец К-744 Р4")
                                .machinery("Lemken EuroTitan 10")
                                .corpusCount("10")
                                .ploughingDepth("21–22")
                                .routingLength("601–1000")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build(),
                        optionalFuelInfoFactory.apply(25.6, 15.0)
                ),
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА СТЕРНИ")
                                .tractor("Кировец К-744 Р4")
                                .machinery("Lemken EuroTitan 10")
                                .corpusCount("10")
                                .ploughingDepth("21–22")
                                .routingLength("Менее 150")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build(),
                        empty()
                ),
                //not existing tractor
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА СТЕРНИ")
                                .tractor("not exist")
                                .machinery("Lemken EuroTitan 10")
                                .corpusCount("10")
                                .ploughingDepth("21–22")
                                .routingLength("601–1000")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build(),
                        empty()
                ),
                //not exist plough mark
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА СТЕРНИ")
                                .tractor("Кировец К-744 Р4")
                                .machinery("not exist")
                                .corpusCount("10")
                                .ploughingDepth("21–22")
                                .routingLength("601–1000")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build(),
                        empty()
                ),
                //not exist corpus count
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА СТЕРНИ")
                                .tractor("Кировец К-744 Р4")
                                .machinery("Lemken EuroTitan 10")
                                .corpusCount("-1")
                                .ploughingDepth("21–22")
                                .routingLength("601–1000")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build(),
                        empty()
                ),
                //not exist ploughing depth
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА СТЕРНИ")
                                .tractor("Кировец К-744 Р4")
                                .machinery("Lemken EuroTitan 10")
                                .corpusCount("10")
                                .ploughingDepth("100000000-200000000")
                                .routingLength("601–1000")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build(),
                        empty()
                ),
                //not exist routing length
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА СТЕРНИ")
                                .tractor("Кировец К-744 Р4")
                                .machinery("Lemken EuroTitan 10")
                                .corpusCount("10")
                                .ploughingDepth("21–22")
                                .routingLength("10000000-20000000")
                                .specificResistance("Удельное сопротивление 36...41 кПа")
                                .build(),
                        empty()
                ),
                //not exist specific resistance
                Arguments.of(
                        FuelInfoSpecification.builder()
                                .tableName("ВСПАШКА СТЕРНИ")
                                .tractor("Кировец К-744 Р4")
                                .machinery("Lemken EuroTitan 10")
                                .corpusCount("10")
                                .ploughingDepth("21–22")
                                .routingLength("601–1000")
                                .specificResistance("Удельное сопротивление -5...-1 кПа")
                                .build(),
                        empty()
                )
        );
    }
}
