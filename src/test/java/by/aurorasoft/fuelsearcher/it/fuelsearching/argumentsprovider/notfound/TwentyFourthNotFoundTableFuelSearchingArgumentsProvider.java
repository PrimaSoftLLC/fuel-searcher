package by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.notfound;

import by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model.NotFoundFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class TwentyFourthNotFoundTableFuelSearchingArgumentsProvider extends NotFoundTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ";

    public TwentyFourthNotFoundTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotFoundFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                //not existing combine
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .combine("not existing")
                                .weightRatioGrainToStraw("1:2")
                                .yield("4.5...4.7")
                                .workingWidth("6")
                                .routingLength("150-200")
                                .build())
                        .build(),
                //not existing weight ratio grain to straw
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .combine("JOHN DEERE 9640WTS-770 - 524 л.с.")
                                .weightRatioGrainToStraw("not existing")
                                .yield("4.5...4.7")
                                .workingWidth("6")
                                .routingLength("150-200")
                                .build())
                        .build(),
                //not existing yield
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .combine("JOHN DEERE 9640WTS-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("not existing")
                                .workingWidth("6")
                                .routingLength("150-200")
                                .build())
                        .build(),
                //not existing working width
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .combine("JOHN DEERE 9640WTS-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("4.5...4.7")
                                .workingWidth("not existing")
                                .routingLength("150-200")
                                .build())
                        .build(),
                //not existing routing length
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .combine("JOHN DEERE 9640WTS-770 - 524 л.с.")
                                .weightRatioGrainToStraw("1:2")
                                .yield("4.5...4.7")
                                .workingWidth("6")
                                .routingLength("not existing")
                                .build())
                        .build()
        );
    }
}
