package by.aurorasoft.fuelsearcher.model.filter.interim.group;

import by.aurorasoft.fuelsearcher.model.filter.interim.InterimFilter;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

import static by.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil.findRowsByGroup;

public abstract class GroupFilter extends InterimFilter {

    public GroupFilter(final SpecificationPropertyExtractor filtrationValueExtractor, final int filtrationCellIndex) {
        super(filtrationValueExtractor, filtrationCellIndex);
    }

    @Override
    protected List<XWPFTableRow> filter(final List<XWPFTableRow> rows,
                                        final String groupValue,
                                        final int groupValueCellIndex) {
        final String groupValueRegex = this.findGroupValueRegex();
        return findRowsByGroup(
                rows,
                groupValue,
                groupValueRegex,
                groupValueCellIndex
        );
    }

    protected abstract String findGroupValueRegex();
}
