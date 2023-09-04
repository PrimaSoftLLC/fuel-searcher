package by.aurorasoft.fuelsearcher.service.searcher.it.argumentprovider;

import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class ThirdTableFuelSearchingArgumentsProvider extends AbstractTableFuelSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<Fuel>> optionalFuelFactory) {
        return Stream.of(
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ")
                                .tractor("Беларус-3522")
                                .machinery("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("20-22")
                                .routingLength("Менее 150")
                                .soilType("Минеральные почвы")
                                .build(),
                        optionalFuelFactory.apply(8.5, 23.7)
                ),
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ")
                                .tractor("Беларус-3022")
                                .machinery("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("25-27")
                                .routingLength("Более 1000")
                                .soilType("Торфяные почвы")
                                .build(),
                        optionalFuelFactory.apply(11.4, 20.5)
                ),
                //not existing tractor
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ")
                                .tractor("not existing")
                                .machinery("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("25-27")
                                .routingLength("Более 1000")
                                .soilType("Торфяные почвы")
                                .build(),
                        empty()
                ),
                //not existing plough mark
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ")
                                .tractor("Беларус-3022")
                                .machinery("not existing")
                                .corpusCount("6")
                                .ploughingDepth("25-27")
                                .routingLength("Более 1000")
                                .soilType("Торфяные почвы")
                                .build(),
                        empty()
                ),
                //not existing corpus count
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ")
                                .tractor("Беларус-3022")
                                .machinery("ПБН-6-50А")
                                .corpusCount("not existing")
                                .ploughingDepth("25-27")
                                .routingLength("Более 1000")
                                .soilType("Торфяные почвы")
                                .build(),
                        empty()
                ),
                //not existing ploughing depth
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ")
                                .tractor("Беларус-3022")
                                .machinery("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("not existing")
                                .routingLength("Более 1000")
                                .soilType("Торфяные почвы")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ")
                                .tractor("Беларус-3022")
                                .machinery("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("25-27")
                                .routingLength("not existing")
                                .soilType("Торфяные почвы")
                                .build(),
                        empty()
                ),
                //not existing soil type
                Arguments.of(
                        FuelSpecification.builder()
                                .tableName("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ")
                                .tractor("Беларус-3022")
                                .machinery("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("25-27")
                                .routingLength("Более 1000")
                                .soilType("not existing")
                                .build(),
                        empty()
                )
        );
    }

}
