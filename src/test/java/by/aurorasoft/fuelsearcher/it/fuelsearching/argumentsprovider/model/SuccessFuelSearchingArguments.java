package by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model;

import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import lombok.Builder;
import lombok.Getter;

@Getter
public final class SuccessFuelSearchingArguments extends FuelSearchingArguments {
    private final Fuel expected;

    @Builder
    public SuccessFuelSearchingArguments(final FuelSpecification specification, final Fuel expected) {
        super(specification);
        this.expected = expected;
    }

}