package by.aurorasoft.fuelsearcher.service.searcher.it.argumentprovider;

import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.Specification;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import static java.util.Optional.empty;

public final class EighthTableFuelSearchingArgumentsProvider extends AbstractTableFuelSearchingArgumentsProvider {

    @Override
    protected Stream<Arguments> provide(final BiFunction<Double, Double, Optional<Fuel>> optionalFuelFactory) {
        return Stream.of(
                Arguments.of(
                        Specification.builder()
                                .tableName("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА")
                                .tractor("Беларус 3522")
                                .machinery("Horsch Maestro 24 S")
                                .workingWidth("16.8")
                                .routingLength("Менее 150")
                                .sowingNorm("Норма высева 15 кг/га")
                                .build(),
                        optionalFuelFactory.apply(32.9, 8.2)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА")
                                .tractor("Беларус 1221")
                                .machinery("Ферабокс Футура Макси 8")
                                .workingWidth("5.6")
                                .routingLength("150-200")
                                .sowingNorm("Норма высева 30 кг/га")
                                .build(),
                        optionalFuelFactory.apply(13.6, 3.6)
                ),
                Arguments.of(
                        Specification.builder()
                                .tableName("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА")
                                .tractor("Беларус 1221")
                                .machinery("NC Tehnik Mojnjsem")
                                .workingWidth("5.6")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 45 кг/га")
                                .build(),
                        optionalFuelFactory.apply(18.8, 3.1)
                ),
                //not existing tractor
                Arguments.of(
                        Specification.builder()
                                .tableName("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА")
                                .tractor("not existing")
                                .machinery("NC Tehnik Mojnjsem")
                                .workingWidth("5.6")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 45 кг/га")
                                .build(),
                        empty()
                ),
                //not existing machinery
                Arguments.of(
                        Specification.builder()
                                .tableName("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА")
                                .tractor("Беларус 1221")
                                .machinery("not existing")
                                .workingWidth("5.6")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 45 кг/га")
                                .build(),
                        empty()
                ),
                //not existing working width
                Arguments.of(
                        Specification.builder()
                                .tableName("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА")
                                .tractor("Беларус 1221")
                                .machinery("NC Tehnik Mojnjsem")
                                .workingWidth("not existing")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 45 кг/га")
                                .build(),
                        empty()
                ),
                //not existing routing length
                Arguments.of(
                        Specification.builder()
                                .tableName("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА")
                                .tractor("Беларус 1221")
                                .machinery("NC Tehnik Mojnjsem")
                                .workingWidth("5.6")
                                .routingLength("not existing")
                                .sowingNorm("Норма высева 45 кг/га")
                                .build(),
                        empty()
                ),
                //not existing sowing norm
                Arguments.of(
                        Specification.builder()
                                .tableName("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА")
                                .tractor("Беларус 1221")
                                .machinery("NC Tehnik Mojnjsem")
                                .workingWidth("5.6")
                                .routingLength("Более 1000")
                                .sowingNorm("not existing")
                                .build(),
                        empty()
                )
        );
    }

}
