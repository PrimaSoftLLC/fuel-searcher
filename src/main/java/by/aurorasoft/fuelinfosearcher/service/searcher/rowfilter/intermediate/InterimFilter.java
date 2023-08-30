package by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.intermediate;

import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.Filter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

public abstract class InterimFilter extends Filter<List<XWPFTableRow>> {

    public InterimFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

}
