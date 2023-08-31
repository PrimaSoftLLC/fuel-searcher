package by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor;

import by.aurorasoft.fuelinfosearcher.model.specification.Specification;
import by.aurorasoft.fuelinfosearcher.model.specification.propertyextractor.exception.SpecificationPropertyExtractingException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
public abstract class SpecificationPropertyExtractor {
    private final Function<Specification, Optional<String>> propertyFounder;
    private final String propertyName;

    public final String extractProperty(final Specification specification) {
        final Optional<String> optionalProperty = this.propertyFounder.apply(specification);
        return optionalProperty.orElseThrow(
                () -> new SpecificationPropertyExtractingException(
                        "Specification property '%s' isn't defined".formatted(this.propertyName)
                )
        );
    }
}
