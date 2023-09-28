package by.aurorasoft.fuelsearcher.service.derivingsearcherfactory.refreshedtablesmetadata.metadatasearcher.filter;

import by.aurorasoft.fuelsearcher.model.filter.conclusive.FinalFilter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil.findRowsWithNotNullAndNotEmptyCell;

@Component
public final class FinalFilterPropertyMetadataSearcher extends FilterPropertyMetadataSearcher<FinalFilter> {

    public FinalFilterPropertyMetadataSearcher() {
        super(FinalFilter.class);
    }

    @Override
    protected Stream<XWPFTableRow> findRowsWithAllowableValues(final List<XWPFTableRow> subTableDataRows,
                                                               final FinalFilter filter) {
        final int filtrationCellIndex = filter.getFiltrationCellIndex();
        return findRowsWithNotNullAndNotEmptyCell(
                subTableDataRows,
                filtrationCellIndex
        );
    }
}
