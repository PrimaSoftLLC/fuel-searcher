package by.aurorasoft.fuelinfosearcher.functionalinterface;

import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;

import java.util.Optional;
import java.util.function.Function;

@FunctionalInterface
public interface FuelSpecificationPropertyExtractor extends Function<FuelSpecification, String> {

}
