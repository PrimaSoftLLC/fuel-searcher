package by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher;

import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.filter.Filter;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.extractCellText;

//TODO: test
@RequiredArgsConstructor
public abstract class FilterPropertyMetadataSearcher<F extends Filter<?>> {
    private static final int LAST_HEADER_ROW_INDEX = 3;

    public final PropertyMetadata find(final FuelTable fuelTable, final F filter) {
        final String propertyName = filter.findPropertyName();
        final String[] allowableValues = this.findPropertyValuesInSubTables(fuelTable, filter);
        return PropertyMetadata.builder()
                .propertyName(propertyName)
                .allowableValues(allowableValues)
                .build();
    }

    protected abstract Stream<XWPFTableRow> findRowsWithPropertyValues(final List<XWPFTableRow> subTableDataRows,
                                                                       final F filter);

    private String[] findPropertyValuesInSubTables(final FuelTable fuelTable, final F filter) {
        return fuelTable.elements()
                .stream()
                .filter(XWPFTable.class::isInstance)
                .map(XWPFTable.class::cast)
                .flatMap(subTable -> this.findPropertyValuesInSubTable(subTable, filter))
                .distinct()
                .toArray(String[]::new);
    }

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
