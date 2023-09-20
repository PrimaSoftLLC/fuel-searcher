package by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher.filter;

import by.aurorasoft.fuelsearcher.model.filter.Filter;
import by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher.PropertyMetadataSearcher;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.extractCellText;

public abstract class FilterPropertyMetadataSearcher<F extends Filter<?>> extends PropertyMetadataSearcher<F> {
    private static final int LAST_HEADER_ROW_INDEX = 3;

    public FilterPropertyMetadataSearcher(final Class<F> filterType) {
        super(filterType);
    }

    @Override
    protected final String findPropertyName(final F filter) {
        return filter.findPropertyName();
    }

    @Override
    protected final String[] findAllowableValues(final List<IBodyElement> tableElements, final F filter) {
        return tableElements.stream()
                .filter(XWPFTable.class::isInstance)
                .map(XWPFTable.class::cast)
                .flatMap(subTable -> this.findPropertyValuesInSubTable(subTable, filter))
                .distinct()
                .toArray(String[]::new);
    }

    protected abstract Stream<XWPFTableRow> findRowsWithPropertyValues(final List<XWPFTableRow> subTableDataRows,
                                                                       final F filter);

    private Stream<String> findPropertyValuesInSubTable(final XWPFTable subTable, final F filter) {
        final List<XWPFTableRow> subTableDataRows = findSubTableDataRows(subTable);
        final int filtrationCellIndex = filter.getFiltrationCellIndex();
        return this.findRowsWithPropertyValues(
                subTableDataRows,
                filter
        ).map(row -> extractCellText(row, filtrationCellIndex));
    }

    private static List<XWPFTableRow> findSubTableDataRows(final XWPFTable subTable) {
        final List<XWPFTableRow> subTableRows = subTable.getRows();
        final int firstDataRowIndex = LAST_HEADER_ROW_INDEX + 1;
        final int lastDataRowIndex = subTableRows.size();
        return subTableRows.subList(firstDataRowIndex, lastDataRowIndex);
    }
}
