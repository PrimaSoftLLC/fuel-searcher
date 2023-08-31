package by.aurorasoft.fuelinfosearcher.model.filter.factory;

import by.aurorasoft.fuelinfosearcher.dictionary.Translatable;
import by.aurorasoft.fuelinfosearcher.model.filter.Filter;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class FilterFactory<F extends Filter<?>, E extends SpecificationPropertyExtractor>
        implements Translatable {
    private final E filtrationValueExtractor;

    @Override
    public final String findAlias() {
        return this.filtrationValueExtractor.findAlias();
    }

    public final F create(final int filtrationCellIndex) {
        return this.create(this.filtrationValueExtractor, filtrationCellIndex);
    }

    protected abstract F create(final E filtrationValueExtractor, final int filtrationCellIndex);
}
