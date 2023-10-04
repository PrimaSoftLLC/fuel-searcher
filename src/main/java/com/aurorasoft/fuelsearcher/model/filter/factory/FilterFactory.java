package com.aurorasoft.fuelsearcher.model.filter.factory;

import com.aurorasoft.fuelsearcher.model.filter.Filter;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import com.aurorasoft.fuelsearcher.service.dictionary.Translatable;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class FilterFactory<F extends Filter<?>, E extends SpecificationPropertyExtractor>
        implements Translatable {
    private final E filtrationValueExtractor;

    @Override
    public final String findAlias() {
        return this.filtrationValueExtractor.getPropertyName();
    }

    public final F create(final int filtrationCellIndex) {
        return this.create(this.filtrationValueExtractor, filtrationCellIndex);
    }

    protected abstract F create(final E filtrationValueExtractor, final int filtrationCellIndex);
}
