package by.aurorasoft.fuelsearcher.it.argumentsprovider;

import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import lombok.Value;

@Value
public class FuelArguments {
    FuelSpecification specification;
    Fuel expected;
}
