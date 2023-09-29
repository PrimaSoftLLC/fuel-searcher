package by.aurorasoft.fuelsearcher.model.filter;

import by.aurorasoft.fuelsearcher.model.PropertyMetadataSource;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import lombok.Getter;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

@Getter
public abstract class Filter<R> extends PropertyMetadataSource {
    private final int filtrationCellIndex;

    public Filter(final SpecificationPropertyExtractor filtrationValueExtractor, final int filtrationCellIndex) {
        super(filtrationValueExtractor);
        this.filtrationCellIndex = filtrationCellIndex;
    }

    public final R filter(final List<XWPFTableRow> rows, final FuelSpecification specification) {
        final String filtrationValue = this.extractFiltrationValue(specification);
        return this.filter(rows, filtrationValue, this.filtrationCellIndex);
    }

    protected abstract R filter(final List<XWPFTableRow> rows,
                                final String filtrationValue,
                                final int filtrationCellIndex);

    private String extractFiltrationValue(final FuelSpecification specification) {
        final SpecificationPropertyExtractor filtrationValueExtractor = super.getPropertyExtractor();
        return filtrationValueExtractor.extract(specification);
    }
}
