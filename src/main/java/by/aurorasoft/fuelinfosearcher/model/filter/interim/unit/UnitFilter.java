package by.aurorasoft.fuelinfosearcher.model.filter.interim.unit;

import by.aurorasoft.fuelinfosearcher.model.filter.interim.InterimFilter;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

import static by.aurorasoft.fuelinfosearcher.util.XWPFUtil.findUnitedRowsByContent;

public abstract class UnitFilter extends InterimFilter {

    public UnitFilter(final SpecificationPropertyExtractor filtrationValueExtractor, final int filtrationCellIndex) {
        super(filtrationValueExtractor, filtrationCellIndex);
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
