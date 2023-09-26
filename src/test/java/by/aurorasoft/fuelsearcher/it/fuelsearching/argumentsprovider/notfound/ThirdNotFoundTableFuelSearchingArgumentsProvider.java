package by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.notfound;

import by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model.NotFoundFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class ThirdNotFoundTableFuelSearchingArgumentsProvider extends NotFoundTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ";

    public ThirdNotFoundTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotFoundFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                //not existing tractor
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("not existing")
                                .machinery("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("25-27")
                                .routingLength("Более 1000")
                                .soilType("Торфяные почвы")
                                .build())
                        .build(),
                //not existing machinery
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус-3022")
                                .machinery("not existing")
                                .corpusCount("6")
                                .ploughingDepth("25-27")
                                .routingLength("Более 1000")
                                .soilType("Торфяные почвы")
                                .build())
                        .build(),
                //not existing corpus count
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус-3022")
                                .machinery("ПБН-6-50А")
                                .corpusCount("not existing")
                                .ploughingDepth("25-27")
                                .routingLength("Более 1000")
                                .soilType("Торфяные почвы")
                                .build())
                        .build(),
                //not existing ploughing depth
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус-3022")
                                .machinery("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("not existing")
                                .routingLength("Более 1000")
                                .soilType("Торфяные почвы")
                                .build())
                        .build(),
                //not existing routing length
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус-3022")
                                .machinery("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("25-27")
                                .routingLength("not existing")
                                .soilType("Торфяные почвы")
                                .build())
                        .build(),
                //not existing soil type
                NotFoundFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус-3022")
                                .machinery("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("25-27")
                                .routingLength("Более 1000")
                                .soilType("not existing")
                                .build())
                        .build()
        );
    }
}
