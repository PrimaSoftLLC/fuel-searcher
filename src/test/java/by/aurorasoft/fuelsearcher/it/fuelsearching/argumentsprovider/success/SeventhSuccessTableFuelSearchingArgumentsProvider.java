package by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.success;

import by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model.SuccessFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class SeventhSuccessTableFuelSearchingArgumentsProvider extends SuccessTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ПОСЕВОМ СЕЛЬСКОХОЗЯЙСТВЕННЫХ КУЛЬТУР: ПШЕНИЦЫ, РЖИ, ЯЧМЕНЯ, ОВСА, ГОРОХА, ЛЮПИНА, ВИКИ, ВИКООВСЯНОЙ СМЕСИ, ЛЬНА";

    public SeventhSuccessTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<SuccessFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Кировец К-744 Р3")
                                .machinery("Horsch Serto 12 SC")
                                .workingWidth("12")
                                .routingLength("Менее 150")
                                .sowingNorm("Норма высева 120-180 кг/га")
                                .build())
                        .expected(new Fuel(28.9, 8.2))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 3022")
                                .machinery("Horsch Pronto 6 DS")
                                .workingWidth("6")
                                .routingLength("150-200")
                                .sowingNorm("Норма высева 240-280 кг/га")
                                .build())
                        .expected(new Fuel(18.4, 9.3))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("Horsch Sprinter 4 ST")
                                .workingWidth("4")
                                .routingLength("Более 1000")
                                .sowingNorm("Норма высева 240-280 кг/га")
                                .build())
                        .expected(new Fuel(15.9, 7.0))
                        .build()
        );
    }
}
