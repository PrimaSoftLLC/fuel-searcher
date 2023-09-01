package by.aurorasoft.fuelsearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelsearcher.service.dictionary.Translatable;
import by.aurorasoft.fuelsearcher.model.specification.Specification;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.exception.SpecificationPropertyExtractingException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
public abstract class SpecificationPropertyExtractor implements Translatable {
    private final Function<Specification, Optional<String>> propertyFounder;
    private final String propertyName;

    @Override
    public final String findAlias() {
        return this.propertyName;
    }

    public final String extract(final Specification specification) {
        final Optional<String> optionalProperty = this.propertyFounder.apply(specification);
        return optionalProperty.orElseThrow(
                () -> new SpecificationPropertyExtractingException(
                        "Specification property '%s' isn't defined".formatted(this.propertyName)
                )
        );
    }
}
