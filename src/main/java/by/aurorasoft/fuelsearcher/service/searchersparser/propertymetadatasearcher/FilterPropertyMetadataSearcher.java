package by.aurorasoft.fuelsearcher.service.searchersparser.propertymetadatasearcher;

import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.filter.Filter;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.stream.Stream;

//TODO: test
@RequiredArgsConstructor
public abstract class FilterPropertyMetadataSearcher {
    private static final int LAST_HEADER_ROW_INDEX = 3;

    private final FuelTable fuelTable;
    private final Filter<?> filter;

    public final PropertyMetadata find() {
        final String propertyName = this.filter.findPropertyName();
        final String[] allowableValues = this.findPropertyValuesInSubTables();
        return PropertyMetadata.builder()
                .propertyName(propertyName)
                .allowableValues(allowableValues)
                .build();
    }

    protected abstract Stream<String> findPropertyValuesInSubTable(final List<XWPFTableRow> subTableRows,
                                                                   final int cellIndex);

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
        final List<XWPFTableRow> subTableRows = subTable.getRows();
        final int cellIndex = this.filter.getFiltrationCellIndex();
        return this.findPropertyValuesInSubTable(subTableRows, cellIndex);
    }

    private static List<XWPFTableRow> findDataRow(final XWPFTable subTable) {
        final List<XWPFTableRow> subTableRows = subTable.getRows();
        final int firstDataRowIndex = LAST_HEADER_ROW_INDEX + 1;
        final int lastDataRowIndex = subTableRows.size();
        return subTableRows.subList(firstDataRowIndex, lastDataRowIndex);
    }
}
