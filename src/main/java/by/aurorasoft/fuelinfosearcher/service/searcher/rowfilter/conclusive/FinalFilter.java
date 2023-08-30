package by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.conclusive;

import by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.Filter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;

import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.findFirstRowByContent;

public abstract class FinalFilter extends Filter<Optional<XWPFTableRow>> {

    public FinalFilter(final int filtrationCellIndex) {
        super(filtrationCellIndex);
    }

    @Override
    protected final Optional<XWPFTableRow> filter(final List<XWPFTableRow> rows,
                                                  final String filtrationValue,
                                                  final int filtrationCellIndex) {
        return findFirstRowByContent(
                rows,
                filtrationCellIndex,
                filtrationValue
        );
    }
}
