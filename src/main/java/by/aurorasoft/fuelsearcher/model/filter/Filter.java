package by.aurorasoft.fuelsearcher.model.filter;

import by.aurorasoft.fuelsearcher.model.PropertyMetadataSource;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

@RequiredArgsConstructor
@Getter
public abstract class Filter<R> implements PropertyMetadataSource {
    private final SpecificationPropertyExtractor filtrationValueExtractor;
    private final int filtrationCellIndex;

    @Override
    public final String findPropertyName() {
        return this.filtrationValueExtractor.getPropertyName();
    }

    public final R filter(final List<XWPFTableRow> rows, final FuelSpecification specification) {
        final String filtrationValue = this.filtrationValueExtractor.extract(specification);
        return this.filter(rows, filtrationValue, this.filtrationCellIndex);
    }

    protected abstract R filter(final List<XWPFTableRow> rows,
                                final String filtrationValue,
                                final int filtrationCellIndex);
}
