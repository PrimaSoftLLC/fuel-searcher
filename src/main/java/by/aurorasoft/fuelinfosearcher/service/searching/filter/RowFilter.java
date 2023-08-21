package by.aurorasoft.fuelinfosearcher.service.searching.filter;

import by.aurorasoft.fuelinfosearcher.model.FuelInfoSpecification;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@FunctionalInterface
public interface RowFilter<R> extends BiFunction<List<XWPFTableRow>, FuelInfoSpecification, R> {

    default Function<List<XWPFTableRow>, R> createFunctionRowFilter(final FuelInfoSpecification specification) {
        return rows -> this.apply(rows, specification);
    }

}
