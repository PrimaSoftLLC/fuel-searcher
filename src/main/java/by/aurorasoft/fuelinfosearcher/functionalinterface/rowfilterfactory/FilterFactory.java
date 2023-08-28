package by.aurorasoft.fuelinfosearcher.functionalinterface.rowfilterfactory;

import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.AbstractRowFilter;

import java.util.function.IntFunction;

public interface FilterFactory<F extends AbstractRowFilter<?>>
        extends IntFunction<F> {

}
