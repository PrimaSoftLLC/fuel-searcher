package by.aurorasoft.fuelsearcher.it.argumentsprovider.model;

import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import lombok.Getter;

@Getter
public final class SuccessFuelSearchingArguments extends FuelSearchingArguments {
    private final Fuel expected;

    public SuccessFuelSearchingArguments(final FuelSpecification specification, final Fuel expected) {
        super(specification);
        this.expected = expected;
    }

}
