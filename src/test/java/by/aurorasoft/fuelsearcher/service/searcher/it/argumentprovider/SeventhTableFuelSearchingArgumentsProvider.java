package by.aurorasoft.fuelsearcher.service.searcher.it.argumentprovider;

import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.Specification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class SeventhTableFuelSearchingArgumentsProvider extends AbstractTableFuelSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<Fuel>> optionalFuelFactory) {
        return Stream.of(
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ПОСЕВОМ СЕЛЬСКОХОЗЯЙСТВЕННЫХ КУЛЬТУР: ПШЕНИЦЫ, РЖИ, ЯЧМЕНЯ, ОВСА, ГОРОХА, ЛЮПИНА, ВИКИ, ВИКООВСЯНОЙ СМЕСИ, ЛЬНА")
                                .tractor("Кировец К-744 Р3")
                                .machinery("Horsch Serto 12 SC")
                                .workingWidth("12")
                                .routingLength("Менее 150")
                                .sowingNorm("Норма высева 120-180 кг/га")
                                .build(),
                        optionalFuelFactory.apply(28.9, 8.2)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ПОСЕВОМ СЕЛЬСКОХОЗЯЙСТВЕННЫХ КУЛЬТУР: ПШЕНИЦЫ, РЖИ, ЯЧМЕНЯ, ОВСА, ГОРОХА, ЛЮПИНА, ВИКИ, ВИКООВСЯНОЙ СМЕСИ, ЛЬНА")
                                .tractor("Беларус 3022")
                                .machinery("Horsch Pronto 6 DS")
                                .workingWidth("6")
                                .routingLength("150-200")
                                .sowingNorm("Норма высева 240-280 кг/га")
                                .build(),
                        optionalFuelFactory.apply(18.4, 9.3)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ПОСЕВОМ СЕЛЬСКОХОЗЯЙСТВЕННЫХ КУЛЬТУР: ПШЕНИЦЫ, РЖИ, ЯЧМЕНЯ, ОВСА, ГОРОХА, ЛЮПИНА, ВИКИ, ВИКООВСЯНОЙ СМЕСИ, ЛЬНА")
                                .tractor("Беларус 1221")
                                .machinery("Horsch Sprinter 4 ST")
                                .workingWidth("4")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 240-280 кг/га")
                                .build(),
                        optionalFuelFactory.apply(15.9, 7.0)
                ),
                //not existing tractor
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ПОСЕВОМ СЕЛЬСКОХОЗЯЙСТВЕННЫХ КУЛЬТУР: ПШЕНИЦЫ, РЖИ, ЯЧМЕНЯ, ОВСА, ГОРОХА, ЛЮПИНА, ВИКИ, ВИКООВСЯНОЙ СМЕСИ, ЛЬНА")
                                .tractor("not existing")
                                .machinery("Horsch Sprinter 4 ST")
                                .workingWidth("4")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 240-280 кг/га")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ПОСЕВОМ СЕЛЬСКОХОЗЯЙСТВЕННЫХ КУЛЬТУР: ПШЕНИЦЫ, РЖИ, ЯЧМЕНЯ, ОВСА, ГОРОХА, ЛЮПИНА, ВИКИ, ВИКООВСЯНОЙ СМЕСИ, ЛЬНА")
                                .tractor("Беларус 1221")
                                .machinery("not existing")
                                .workingWidth("4")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 240-280 кг/га")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ПОСЕВОМ СЕЛЬСКОХОЗЯЙСТВЕННЫХ КУЛЬТУР: ПШЕНИЦЫ, РЖИ, ЯЧМЕНЯ, ОВСА, ГОРОХА, ЛЮПИНА, ВИКИ, ВИКООВСЯНОЙ СМЕСИ, ЛЬНА")
                                .tractor("Беларус 1221")
                                .machinery("Horsch Sprinter 4 ST")
                                .workingWidth("not existing")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 240-280 кг/га")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ПОСЕВОМ СЕЛЬСКОХОЗЯЙСТВЕННЫХ КУЛЬТУР: ПШЕНИЦЫ, РЖИ, ЯЧМЕНЯ, ОВСА, ГОРОХА, ЛЮПИНА, ВИКИ, ВИКООВСЯНОЙ СМЕСИ, ЛЬНА")
                                .tractor("Беларус 1221")
                                .machinery("Horsch Sprinter 4 ST")
                                .workingWidth("4")
                                .routingLength("not existing")
                                .sowingNorm("Норма высева 240-280 кг/га")
                                .build(),
                        empty()
                ),
                //not existing sowing norm
                Arguments.of(
                        Specification.builder()
                                .tableName("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ПОСЕВОМ СЕЛЬСКОХОЗЯЙСТВЕННЫХ КУЛЬТУР: ПШЕНИЦЫ, РЖИ, ЯЧМЕНЯ, ОВСА, ГОРОХА, ЛЮПИНА, ВИКИ, ВИКООВСЯНОЙ СМЕСИ, ЛЬНА")
                                .tractor("Беларус 1221")
                                .machinery("Horsch Sprinter 4 ST")
                                .workingWidth("4")
                                .routingLength("Более 1000")
                                .sowingNorm("not existing")
                                .build(),
                        empty()
                )
        );
    }

}
