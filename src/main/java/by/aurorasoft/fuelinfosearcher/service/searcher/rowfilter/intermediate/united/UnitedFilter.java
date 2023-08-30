package by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.intermediate.united;

import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.intermediate.InterimFilter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.findUnitedRowsByContent;

public abstract class UnitedFilter extends InterimFilter {

    public UnitedFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected final List<XWPFTableRow> filter(final List<XWPFTableRow> rows,
                                              final String filteringValue,
                                              final int filteringCellIndex) {
        return findUnitedRowsByContent(
                rows,
                filteringCellIndex,
                filteringValue
        );
    }

}
