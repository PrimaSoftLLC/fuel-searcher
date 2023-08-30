package by.aurorasoft.fuelinfosearcher.functionalinterface.filterfactory;

import by.aurorasoft.fuelinfosearcher.service.searcher.filter.Filter;

import java.util.function.IntFunction;

@FunctionalInterface
public interface FilterFactory<F extends Filter<?>> extends IntFunction<F> {

}
