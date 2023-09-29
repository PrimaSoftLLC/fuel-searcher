package by.aurorasoft.fuelsearcher.model.filter.interim.group;

import by.aurorasoft.fuelsearcher.model.filter.interim.InterimFilter;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import lombok.Getter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

import static by.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil.findRowsByGroup;

public abstract class GroupFilter extends InterimFilter {

    @Getter
    private final String groupValueRegex;

    public GroupFilter(final SpecificationPropertyExtractor filtrationValueExtractor,
                       final int groupValueCellIndex,
                       final String groupValueRegex) {
        super(filtrationValueExtractor, groupValueCellIndex);
        this.groupValueRegex = groupValueRegex;
    }

    @Override
    protected final List<XWPFTableRow> filter(final List<XWPFTableRow> rows,
                                              final String groupValue,
                                              final int groupValueCellIndex) {
        return findRowsByGroup(
                rows,
                groupValue,
                this.groupValueRegex,
                groupValueCellIndex
        );
    }
}
