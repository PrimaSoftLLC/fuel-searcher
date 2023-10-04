package com.aurorasoft.fuelsearcher.service.factory.derivingsearcher.refreshedtablesmetadata.metadatasearcher.filter;

import com.aurorasoft.fuelsearcher.model.filter.interim.group.GroupFilter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

import static com.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil.findRowsWithCellMatchingRegex;

@Component
public final class GroupFilterPropertyMetadataSearcher extends FilterPropertyMetadataSearcher<GroupFilter> {

    public GroupFilterPropertyMetadataSearcher() {
        super(GroupFilter.class);
    }

    @Override
    protected Stream<XWPFTableRow> findRowsWithAllowableValues(final List<XWPFTableRow> subTableDataRows,
                                                               final GroupFilter filter) {
        final int filtrationCellIndex = filter.getFiltrationCellIndex();
        final String groupValueRegex = filter.getGroupValueRegex();
        return findRowsWithCellMatchingRegex(
                subTableDataRows,
                filtrationCellIndex,
                groupValueRegex
        );
    }
}
