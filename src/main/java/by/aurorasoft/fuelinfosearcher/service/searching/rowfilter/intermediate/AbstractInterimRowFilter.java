package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermediate;

import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.AbstractRowFilter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

public abstract class AbstractInterimRowFilter extends AbstractRowFilter<List<XWPFTableRow>> {

    public AbstractInterimRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

}
