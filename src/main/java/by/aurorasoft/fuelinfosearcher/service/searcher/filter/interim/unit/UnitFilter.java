package by.aurorasoft.fuelinfosearcher.service.searcher.filter.interim.unit;

import by.aurorasoft.fuelinfosearcher.service.searcher.filter.interim.InterimFilter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.findUnitedRowsByContent;

public abstract class UnitFilter extends InterimFilter {

    public UnitFilter(final int filtrationCellIndex) {
        super(filtrationCellIndex);
    }

    @Override
    protected final List<XWPFTableRow> filter(final List<XWPFTableRow> rows,
                                              final String filtrationValue,
                                              final int filtrationCellIndex) {
        return findUnitedRowsByContent(
                rows,
                filtrationCellIndex,
                filtrationValue
        );
    }

}
