package by.aurorasoft.fuelsearcher.it.argumentsprovider.success;

import by.aurorasoft.fuelsearcher.it.argumentsprovider.model.SuccessFuelSearchingArguments;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification.FuelSpecificationBuilder;

import java.util.function.Supplier;
import java.util.stream.Stream;

public final class EleventhSuccessTableFuelSearchingArgumentsProvider extends SuccessTableFuelSearchingArgumentsProvider {
    private static final String TABLE_NAME = "ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ";

    public EleventhSuccessTableFuelSearchingArgumentsProvider() {
        super(TABLE_NAME);
    }

    @Override
    protected Stream<SuccessFuelSearchingArguments> createArguments(final Supplier<FuelSpecificationBuilder> specificationBuilderSupplier) {
        return Stream.of(
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("CASE IN Puma 210")
                                .machinery("AMAZONE ZG B 8200 SUPER")
                                .chargingMethodAndTransportDistance("Механизированный с загрузкой в конце гона")
                                .spreadRate("до 2")
                                .routingLength("Менее 150")
                                .fertilizerType("Гранулированные удобрения")
                                .build())
                        .expected(new Fuel(71.2, 1.42))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("CASE IN Puma 210")
                                .machinery("Amazone ZA M 3000")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 0.5...1.5")
                                .spreadRate("6.1...8")
                                .routingLength("Менее 150")
                                .fertilizerType("Кристаллические удобрения")
                                .build())
                        .expected(new Fuel(40.3, 1.95))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1523")
                                .machinery("УРМ 10")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 1.51...3.5")
                                .spreadRate("8.1...10")
                                .routingLength("Более 1000")
                                .fertilizerType("Гранулированные удобрения")
                                .build())
                        .expected(new Fuel(76.6, 1.19))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1523")
                                .machinery("REWO 8200")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 3.51...5")
                                .spreadRate("6.1...8")
                                .routingLength("150-200")
                                .fertilizerType("Пылевидные удобрения")
                                .build())
                        .expected(new Fuel(37, 2.11))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1523")
                                .machinery("SULKY XT 160")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 1.51...3.5")
                                .spreadRate("2.1...4")
                                .routingLength("150-200")
                                .fertilizerType("Гранулированные удобрения")
                                .build())
                        .expected(new Fuel(73.8, 1.4))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("RCW 10000")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 11.1...13")
                                .spreadRate("8.1...10")
                                .routingLength("Более 1000")
                                .fertilizerType("Пылевидные удобрения")
                                .build())
                        .expected(new Fuel(29.3, 2.24))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 1221")
                                .machinery("Amazone ZA-M-1200")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 1.51...3.5")
                                .spreadRate("8.1...10")
                                .routingLength("150-200")
                                .fertilizerType("Гранулированные удобрения")
                                .build())
                        .expected(new Fuel(27.4, 1.99))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("New Holland TD 5.110")
                                .machinery("Amazone ZA-M-1001 с надставкой бункера 500 л")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 5.1...7")
                                .spreadRate("до 2")
                                .routingLength("201-300")
                                .fertilizerType("Кристаллические удобрения")
                                .build())
                        .expected(new Fuel(40.8, 1.25))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 82")
                                .machinery("Amazone ZA-M-900")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 11.1...13")
                                .spreadRate("8.1...10")
                                .routingLength("201-300")
                                .fertilizerType("Кристаллические удобрения")
                                .build())
                        .expected(new Fuel(5.1, 9.05))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 82")
                                .machinery("Tornado 1300")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 5.1...7")
                                .spreadRate("6.1...8")
                                .routingLength("150-200")
                                .fertilizerType("Гранулированные удобрения")
                                .build())
                        .expected(new Fuel(13.1, 3.91))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 82")
                                .machinery("SULKY XT 100 Polivrac")
                                .chargingMethodAndTransportDistance("Механизированный с загрузкой в конце гона")
                                .spreadRate("до 2")
                                .routingLength("201-300")
                                .fertilizerType("Гранулированные удобрения")
                                .build())
                        .expected(new Fuel(75.5, 0.9))
                        .build(),
                SuccessFuelSearchingArguments.builder()
                        .specification(specificationBuilderSupplier.get()
                                .tractor("Беларус 82")
                                .machinery("SULKY DX 20 c")
                                .chargingMethodAndTransportDistance("Механизированный с подъездом 3.51...5")
                                .spreadRate("6.1...8")
                                .routingLength("150-200")
                                .fertilizerType("Гранулированные удобрения")
                                .build())
                        .expected(new Fuel(20, 2.85))
                        .build()
        );
    }
}
