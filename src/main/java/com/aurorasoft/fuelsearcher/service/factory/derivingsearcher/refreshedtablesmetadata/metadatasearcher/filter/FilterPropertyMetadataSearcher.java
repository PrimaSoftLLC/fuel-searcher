package com.aurorasoft.fuelsearcher.service.factory.derivingsearcher.refreshedtablesmetadata.metadatasearcher.filter;

import com.aurorasoft.fuelsearcher.service.filter.Filter;
import com.aurorasoft.fuelsearcher.service.factory.derivingsearcher.refreshedtablesmetadata.metadatasearcher.PropertyMetadataSearcher;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.stream.Stream;

import static com.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.extractCellText;

public abstract class FilterPropertyMetadataSearcher<F extends Filter<?>> extends PropertyMetadataSearcher<F> {
    private static final int LAST_HEADER_ROW_INDEX = 3;

    public FilterPropertyMetadataSearcher(final Class<F> filterType) {
        super(filterType);
    }

    @Override
    protected final Stream<String> findAllowableValues(final List<IBodyElement> tableElements, final F filter) {
        return tableElements.stream()
                .filter(XWPFTable.class::isInstance)
                .map(XWPFTable.class::cast)
                .flatMap(subTable -> this.findAllowableValuesInSubTable(subTable, filter));
    }

    @Override
    protected final boolean isAllowableValuesDuplicated() {
        return true;
    }

    protected abstract Stream<XWPFTableRow> findRowsWithAllowableValues(final List<XWPFTableRow> subTableDataRows,
                                                                        final F filter);

    private Stream<String> findAllowableValuesInSubTable(final XWPFTable subTable, final F filter) {
        final List<XWPFTableRow> subTableDataRows = findSubTableDataRows(subTable);
        final int filtrationCellIndex = filter.getFiltrationCellIndex();
        return this.findRowsWithAllowableValues(
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
