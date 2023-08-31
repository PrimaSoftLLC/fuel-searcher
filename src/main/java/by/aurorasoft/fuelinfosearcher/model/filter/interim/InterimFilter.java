package by.aurorasoft.fuelinfosearcher.model.filter.interim;

import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelinfosearcher.model.filter.Filter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

public abstract class InterimFilter extends Filter<List<XWPFTableRow>> {

    public InterimFilter(final SpecificationPropertyExtractor filtrationValueExtractor, final int filtrationCellIndex) {
        super(filtrationValueExtractor, filtrationCellIndex);
    }

}
