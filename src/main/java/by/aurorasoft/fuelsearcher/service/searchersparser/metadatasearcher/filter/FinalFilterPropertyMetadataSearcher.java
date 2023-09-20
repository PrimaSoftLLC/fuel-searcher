package by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher.filter;

import by.aurorasoft.fuelsearcher.model.filter.conclusive.FinalFilter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.isCellNullOrEmpty;

public final class FinalFilterPropertyMetadataSearcher extends FilterPropertyMetadataSearcher<FinalFilter> {

    @Override
    protected Stream<XWPFTableRow> findRowsWithPropertyValues(final List<XWPFTableRow> subTableDataRows,
                                                              final FinalFilter filter) {
        final int filtrationCellIndex = filter.getFiltrationCellIndex();
        return subTableDataRows.stream()
                .filter(
                        row -> !isCellNullOrEmpty(
                                row,
                                filtrationCellIndex
                        )
                );
    }
}
