package com.aurorasoft.fuelsearcher.service.factory.derivingsearcher.refreshedtablesmetadata.metadatasearcher.filter;

import com.aurorasoft.fuelsearcher.model.filter.interim.unit.UnitFilter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

import static com.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil.findRowsWithNotNullAndNotEmptyCell;

@Component
public final class UnitFilterPropertyMetadataSearcher extends FilterPropertyMetadataSearcher<UnitFilter> {

    public UnitFilterPropertyMetadataSearcher() {
        super(UnitFilter.class);
    }

    @Override
    protected Stream<XWPFTableRow> findRowsWithAllowableValues(final List<XWPFTableRow> subTableDataRows,
                                                               final UnitFilter filter) {
        final int filtrationCellIndex = filter.getFiltrationCellIndex();
        return findRowsWithNotNullAndNotEmptyCell(
                subTableDataRows,
                filtrationCellIndex
        );
    }
}
