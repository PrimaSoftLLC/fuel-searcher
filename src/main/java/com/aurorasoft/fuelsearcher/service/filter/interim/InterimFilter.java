package com.aurorasoft.fuelsearcher.service.filter.interim;

import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import com.aurorasoft.fuelsearcher.service.filter.Filter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

public abstract class InterimFilter extends Filter<List<XWPFTableRow>> {

    public InterimFilter(final SpecificationPropertyExtractor filtrationValueExtractor, final int filtrationCellIndex) {
        super(filtrationValueExtractor, filtrationCellIndex);
    }

}
