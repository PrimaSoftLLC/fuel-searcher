package by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher.filter;

import by.aurorasoft.fuelsearcher.model.filter.interim.unit.UnitFilter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.isChildUnitedRow;

public final class UnitFilterPropertyMetadataSearcher extends FilterPropertyMetadataSearcher<UnitFilter> {

    @Override
    protected Stream<XWPFTableRow> findRowsWithPropertyValues(final List<XWPFTableRow> subTableDataRows,
                                                              final UnitFilter filter) {
        final int filtrationCellIndex = filter.getFiltrationCellIndex();
        return subTableDataRows.stream()
                .filter(
                        row -> !isChildUnitedRow(
                                row,
                                filtrationCellIndex
                        )
                );
    }
}
