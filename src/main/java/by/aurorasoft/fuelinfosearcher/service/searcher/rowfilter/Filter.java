package by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter;

import by.aurorasoft.fuelinfosearcher.functionalinterface.filteringfunction.FilteringFunction;
import by.aurorasoft.fuelinfosearcher.model.Specification;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

@RequiredArgsConstructor
@Getter
public abstract class Filter<R, F extends FilteringFunction<R>> {
    private final int filtrationCellIndex;
    private final Class<F> filteringFunctionType;

    public final FilteringFunction<R> mapToFilteringFunction(final Specification specification) {
        final FilteringFunction<R> function = rows -> this.filter(rows, specification);
        return this.filteringFunctionType.cast(function);
    }

    protected abstract R filter(final List<XWPFTableRow> rows,
                                final String filtrationValue,
                                final int filtrationCellIndex);

    protected abstract String extractFiltrationValue(final Specification specification);

    private R filter(final List<XWPFTableRow> rows, final Specification specification) {
        final String filtrationValue = this.extractFiltrationValue(specification);
        return this.filter(rows, filtrationValue, this.filtrationCellIndex);
    }
}
