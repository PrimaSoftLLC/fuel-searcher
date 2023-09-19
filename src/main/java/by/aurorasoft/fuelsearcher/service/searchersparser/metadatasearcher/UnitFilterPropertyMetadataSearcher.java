package by.aurorasoft.fuelsearcher.service.searchersparser.metadatasearcher;

import by.aurorasoft.fuelsearcher.model.FuelTable;
import by.aurorasoft.fuelsearcher.model.filter.interim.unit.UnitFilter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.XWPFTableRowUtil.isCellEmpty;

public final class UnitFilterPropertyMetadataSearcher extends FilterPropertyMetadataSearcher<UnitFilter> {

    public UnitFilterPropertyMetadataSearcher(final FuelTable fuelTable, final UnitFilter filter) {
        super(fuelTable, filter);
    }

    @Override
    protected Stream<XWPFTableRow> findRowsWithPropertyValues(final List<XWPFTableRow> subTableDataRows,
                                                              final UnitFilter filter) {
        final int filtrationCellIndex = filter.getFiltrationCellIndex();
        return subTableDataRows.stream()
                .filter(
                        row -> !isCellEmpty(
                                row,
                                filtrationCellIndex
                        )
                );
    }
}
