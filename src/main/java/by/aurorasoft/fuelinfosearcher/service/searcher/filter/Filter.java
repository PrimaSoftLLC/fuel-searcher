package by.aurorasoft.fuelinfosearcher.service.searcher.filter;

import by.aurorasoft.fuelinfosearcher.model.Specification;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

@RequiredArgsConstructor
@Getter
public abstract class Filter<R> {
    private final int filtrationCellIndex;

    public final R filter(final List<XWPFTableRow> rows, final Specification specification) {
        final String filtrationValue = this.extractFiltrationValue(specification);
        return this.filter(rows, filtrationValue, this.filtrationCellIndex);
    }

    protected abstract R filter(final List<XWPFTableRow> rows,
                                final String filtrationValue,
                                final int filtrationCellIndex);

    protected abstract String extractFiltrationValue(final Specification specification);
}
