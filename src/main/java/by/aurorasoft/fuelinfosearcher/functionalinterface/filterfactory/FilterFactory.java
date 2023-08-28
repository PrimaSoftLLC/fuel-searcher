package by.aurorasoft.fuelinfosearcher.functionalinterface.filterfactory;

import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.Filter;

import java.util.function.IntFunction;

public interface FilterFactory<F extends Filter<?>>
        extends IntFunction<F> {

}
