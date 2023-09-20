package by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher.filter;

import by.aurorasoft.fuelsearcher.model.filter.interim.group.GroupFilter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.isCellTextMatchRegex;

public final class GroupFilterPropertyMetadataSearcher extends FilterPropertyMetadataSearcher<GroupFilter> {

    @Override
    protected Stream<XWPFTableRow> findRowsWithPropertyValues(final List<XWPFTableRow> subTableDataRows,
                                                              final GroupFilter filter) {
        final int filtrationCellIndex = filter.getFiltrationCellIndex();
        final String groupValueRegex = filter.findGroupValueRegex();
        return subTableDataRows.stream()
                .filter(
                        row -> isCellTextMatchRegex(
                                row,
                                filtrationCellIndex,
                                groupValueRegex
                        )
                );
    }
}
