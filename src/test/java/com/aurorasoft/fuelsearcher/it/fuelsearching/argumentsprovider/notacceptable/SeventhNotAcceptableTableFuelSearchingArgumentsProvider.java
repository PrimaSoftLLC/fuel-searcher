package com.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.notacceptable;

import com.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model.NotAcceptableFuelSearchingArguments;
import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public final class SeventhNotAcceptableTableFuelSearchingArgumentsProvider extends NotAcceptableTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ПОСЕВОМ СЕЛЬСКОХОЗЯЙСТВЕННЫХ КУЛЬТУР: ПШЕНИЦЫ, РЖИ, ЯЧМЕНЯ, ОВСА, ГОРОХА, ЛЮПИНА, ВИКИ, ВИКООВСЯНОЙ СМЕСИ, ЛЬНА";

    public SeventhNotAcceptableTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<NotAcceptableFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                NotAcceptableFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Кировец К-744 Р3")
                                .machinery("Horsch Serto 12 SC")
                                .workingWidth("12")
                                .build())
                        .failedPropertyNames(Set.of("норма высева", "длина гона"))
                        .build()
        );
    }
}
