package by.aurorasoft.fuelsearcher.it.argumentsprovider.model;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import lombok.Getter;

@Getter
public final class NotAcceptableFuelSearchingArguments extends FuelSearchingArguments {
    private final String[] failedPropertyNames;

    public NotAcceptableFuelSearchingArguments(final FuelSpecification specification,
                                               final String[] failedPropertyNames) {
        super(specification);
        this.failedPropertyNames = failedPropertyNames;
    }
}
