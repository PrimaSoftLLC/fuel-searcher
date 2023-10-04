package com.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model;

import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import lombok.Builder;

public final class NotFoundFuelSearchingArguments extends FuelSearchingArguments {

    @Builder
    public NotFoundFuelSearchingArguments(final FuelSpecification specification) {
        super(specification);
    }

}
