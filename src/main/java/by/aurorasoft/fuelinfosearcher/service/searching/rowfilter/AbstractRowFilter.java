package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter;

import by.aurorasoft.fuelinfosearcher.model.FuelSpecification;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
@Getter
public abstract class AbstractRowFilter<R> {
    private final int filteringCellIndex;

    public final Function<List<XWPFTableRow>, R> mapToFilteringFunction(final FuelSpecification specification) {
        return rows -> this.filter(rows, specification);
    }

    protected abstract R filter(final List<XWPFTableRow> rows,
                                final String filteringValue,
                                final int filteringCellIndex);

    protected abstract String extractFilteringValue(final FuelSpecification specification);

    private R filter(final List<XWPFTableRow> rows, final FuelSpecification specification) {
        final String filteringValue = this.extractFilteringValue(specification);
        return this.filter(rows, filteringValue, this.filteringCellIndex);
    }
}
