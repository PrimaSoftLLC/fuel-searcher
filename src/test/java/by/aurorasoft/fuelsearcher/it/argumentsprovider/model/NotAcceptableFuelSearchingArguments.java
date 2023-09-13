package by.aurorasoft.fuelsearcher.it.argumentsprovider.model;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public final class NotAcceptableFuelSearchingArguments extends FuelSearchingArguments {
    private final List<String> failedPropertyNames;

    @Builder
    public NotAcceptableFuelSearchingArguments(final FuelSpecification specification,
                                               final List<String> failedPropertyNames) {
        super(specification);
        this.failedPropertyNames = failedPropertyNames;
    }
}
