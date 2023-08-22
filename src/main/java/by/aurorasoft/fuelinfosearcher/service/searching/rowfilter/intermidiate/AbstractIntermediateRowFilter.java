package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.intermidiate;

import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.AbstractRowFilter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

public abstract class AbstractIntermediateRowFilter extends AbstractRowFilter<List<XWPFTableRow>> {

    public AbstractIntermediateRowFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

}
