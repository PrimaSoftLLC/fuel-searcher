package by.aurorasoft.fuelsearcher.it.temp.argumentsprovider;

import by.aurorasoft.fuelsearcher.it.temp.argumentsprovider.model.FuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class ThirdTableFuelSearchingArgumentsProvider extends TableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ";

    public ThirdTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<FuelSearchingArguments> createFuelSearchingArguments(
            final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier
    ) {
        return Stream.of(
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус-3522")
                                .machinery("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("20-22")
                                .routingLength("Менее 150")
                                .soilType("Минеральные почвы")
                                .build())
                        .expected(new Fuel(8.5, 23.7))
                        .build(),
                FuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус-3022")
                                .machinery("ПБН-6-50А")
                                .corpusCount("6")
                                .ploughingDepth("25-27")
                                .routingLength("Более 1000")
                                .soilType("Торфяные почвы")
                                .build())
                        .expected(new Fuel(11.4, 20.5))
                        .build(),
                //not existing tractor
                FuelSearchingArguments.builder()
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
                FuelSearchingArguments.builder()
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
                FuelSearchingArguments.builder()
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
                FuelSearchingArguments.builder()
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
                FuelSearchingArguments.builder()
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
                FuelSearchingArguments.builder()
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
