package by.aurorasoft.fuelsearcher.service.searchersparser.propertymetadatasearcher;

import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
public abstract class PropertyMetadataSearcher {
    private final FuelTable fuelTable;
    private final String propertyName;
    private final int cellIndex;

    public PropertyMetadata find() {
        final String[] allowableValues = this.findPropertyValuesInSubTables();
        return PropertyMetadata.builder()
                .propertyName(this.propertyName)
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
                .map(XWPFTable::getRows)
                .flatMap(subTableRows -> this.findPropertyValuesInSubTable(subTableRows, this.cellIndex))
                .distinct()
                .toArray(String[]::new);
    }
}
