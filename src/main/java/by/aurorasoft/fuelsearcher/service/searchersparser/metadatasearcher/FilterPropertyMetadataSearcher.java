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

    private final FuelTable fuelTable;
    private final F filter;

    public final PropertyMetadata find() {
        final String propertyName = this.filter.findPropertyName();
        final String[] allowableValues = this.findPropertyValuesInSubTables();
        return PropertyMetadata.builder()
                .propertyName(propertyName)
                .allowableValues(allowableValues)
                .build();
    }

    protected abstract Stream<XWPFTableRow> findFiltrationRows(final List<XWPFTableRow> subTableDataRows,
                                                               final F filter);

    private String[] findPropertyValuesInSubTables() {
        return this.fuelTable.elements()
                .stream()
                .filter(XWPFTable.class::isInstance)
                .map(XWPFTable.class::cast)
                .flatMap(this::findPropertyValuesInSubTable)
                .distinct()
                .toArray(String[]::new);
    }

    private Stream<String> findPropertyValuesInSubTable(final XWPFTable subTable) {
        final List<XWPFTableRow> subTableDataRows = findSubTableDataRows(subTable);
        final int filtrationCellIndex = this.filter.getFiltrationCellIndex();
        return this.findFiltrationRows(
                subTableDataRows,
                this.filter
        ).map(row -> extractCellText(row, filtrationCellIndex));
    }

    private static List<XWPFTableRow> findSubTableDataRows(final XWPFTable subTable) {
        final List<XWPFTableRow> subTableRows = subTable.getRows();
        final int firstDataRowIndex = LAST_HEADER_ROW_INDEX + 1;
        final int lastDataRowIndex = subTableRows.size();
        return subTableRows.subList(firstDataRowIndex, lastDataRowIndex);
    }
}
