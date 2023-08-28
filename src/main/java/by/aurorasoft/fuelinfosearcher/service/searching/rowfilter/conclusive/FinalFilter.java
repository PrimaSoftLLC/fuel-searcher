package by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.conclusive;

import by.aurorasoft.fuelinfosearcher.service.searching.rowfilter.Filter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;

import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.findFirstRowByContent;

//TODO: remove FuelInfoSpecificationUtil
public abstract class FinalFilter extends Filter<Optional<XWPFTableRow>> {

    public FinalFilter(final int filteringCellIndex) {
        super(filteringCellIndex);
    }

    @Override
    protected final Optional<XWPFTableRow> filter(final List<XWPFTableRow> rows,
                                                  final String filteringValue,
                                                  final int filteringCellIndex) {
        return findFirstRowByContent(
                rows,
                filteringCellIndex,
                filteringValue
        );
    }
}
