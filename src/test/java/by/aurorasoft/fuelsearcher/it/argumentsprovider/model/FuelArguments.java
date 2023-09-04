package by.aurorasoft.fuelsearcher.it.argumentsprovider.model;

import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.util.Optional;

import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.NONE;

@Value
@AllArgsConstructor
@Builder
public class FuelArguments {
    FuelSpecification specification;

    @Getter(value = NONE)
    Fuel expected;

    public Optional<Fuel> findExpected() {
        return ofNullable(this.expected);
    }
}
