package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.united;

import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate.AbstractInterimFilter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.findUnitedRowsByContent;

public abstract class AbstractUnitedRowFilter extends AbstractInterimFilter {

    public AbstractUnitedRowFilter(final int filteringCellIndex) {
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
