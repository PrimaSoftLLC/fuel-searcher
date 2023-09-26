package by.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
public final class NotAcceptableFuelSearchingArguments extends FuelSearchingArguments {
    private final Set<String> failedPropertyNames;

    @Builder
    public NotAcceptableFuelSearchingArguments(final FuelSpecification specification,
                                               final Set<String> failedPropertyNames) {
        super(specification);
        this.failedPropertyNames = failedPropertyNames;
    }
}
