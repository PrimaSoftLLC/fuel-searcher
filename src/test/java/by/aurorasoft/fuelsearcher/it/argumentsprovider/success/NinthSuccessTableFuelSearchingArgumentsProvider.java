package by.aurorasoft.fuelsearcher.it.argumentsprovider.success;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.SuccessFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class NinthSuccessTableFuelSearchingArgumentsProvider extends SuccessTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ПОСЕВ САХАРНОЙ СВЕКЛЫ";

    public NinthSuccessTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<SuccessFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("John Deere 8400")
                                .machinery("Tempo V-18")
                                .workingWidth("8.1")
                                .routingLength("Менее 150")
                                .sowingNorm("Норма высева семян 15 кг/га")
                                .build())
                        .expected(new Fuel(17.9, 3.45))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("Meca V-4")
                                .workingWidth("5.4")
                                .routingLength("601-1000")
                                .sowingNorm("Норма высева семян 30 кг/га")
                                .build())
                        .expected(new Fuel(15.6, 2.72))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 80/82")
                                .machinery("Tehnic NC")
                                .workingWidth("3.6")
                                .routingLength("601-1000")
                                .sowingNorm("Норма высева семян 45 кг/га")
                                .build())
                        .expected(new Fuel(10.3, 2.64))
                        .build()
        );
    }
}
