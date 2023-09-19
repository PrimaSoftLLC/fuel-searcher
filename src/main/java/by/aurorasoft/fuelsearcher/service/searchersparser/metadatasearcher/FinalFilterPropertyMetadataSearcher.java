package by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher;

import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.filter.conclusive.FinalFilter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.stream.Stream;

public final class FinalFilterPropertyMetadataSearcher extends FilterPropertyMetadataSearcher<FinalFilter> {

    public FinalFilterPropertyMetadataSearcher(final FuelTable fuelTable, final FinalFilter filter) {
        super(fuelTable, filter);
    }

    @Override
    protected Stream<XWPFTableRow> findRowsWithPropertyValues(final List<XWPFTableRow> subTableDataRows,
                                                              final FinalFilter filter) {
        return subTableDataRows.stream();
    }
}
