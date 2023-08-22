package by.aurorasoft.fuelinfosearcher.service.searching.rowfiltertemp;

import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@FunctionalInterface
public interface RowFilter<R> extends BiFunction<List<XWPFTableRow>, FuelSpecification, R> {

    default Function<List<XWPFTableRow>, R> createFunctionRowFilter(final FuelSpecification specification) {
        return rows -> this.apply(rows, specification);
    }

}
