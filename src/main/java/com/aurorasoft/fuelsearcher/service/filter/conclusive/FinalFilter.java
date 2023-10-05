package com.aurorasoft.fuelsearcher.service.filter.conclusive;

import com.aurorasoft.fuelsearcher.service.filter.Filter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Optional;

import static com.aurorasoft.fuelsearcher.util.XWPFTableRowFilteringUtil.findFirstRowByContent;

public abstract class FinalFilter extends Filter<Optional<XWPFTableRow>> {

    public FinalFilter(final SpecificationPropertyExtractor filtrationValueExtractor, final int filtrationCellIndex) {
        super(filtrationValueExtractor, filtrationCellIndex);
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
