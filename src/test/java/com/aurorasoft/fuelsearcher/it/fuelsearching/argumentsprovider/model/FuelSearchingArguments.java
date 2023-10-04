package com.aurorasoft.fuelsearcher.it.fuelsearching.argumentsprovider.model;

import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class FuelSearchingArguments {
    private final FuelSpecification specification;
}
