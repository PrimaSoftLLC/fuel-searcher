package by.aurorasoft.fuelinfosearcher.functionalinterface;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;

import java.util.function.Function;

@FunctionalInterface
public interface SpecificationPropertyExtractor extends Function<Specification, String> {

}
