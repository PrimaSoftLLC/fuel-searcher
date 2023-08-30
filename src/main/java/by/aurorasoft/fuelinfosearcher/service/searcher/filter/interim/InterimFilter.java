package by.aurorasoft.fuelinfosearcher.service.searcher.filter.interim;

import by.aurorasoft.fuelinfosearcher.service.searcher.filter.Filter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

public abstract class InterimFilter extends Filter<List<XWPFTableRow>> {

    public InterimFilter(final int filtrationCellIndex) {
        super(filtrationCellIndex);
    }

}
