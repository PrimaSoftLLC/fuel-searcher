package by.aurorasoft.fuelsearcher.it.argumentsprovider.model;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import lombok.Builder;

public final class NotFoundFuelSearchingArguments extends FuelSearchingArguments {

    @Builder
    public NotFoundFuelSearchingArguments(final FuelSpecification specification) {
        super(specification);
    }

}
