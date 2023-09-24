package by.aurorasoft.fuelsearcher.model;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.builder.BuilderRequiringAllProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;
import static lombok.AccessLevel.PRIVATE;

//TODO: refactor and refactor tests
@Value
@AllArgsConstructor(access = PRIVATE)
public class SubTableTitleMetadata {
    String templateWithStringFillers;
    String regex;
    List<SpecificationPropertyExtractor> argumentExtractors;

    public List<SubTableTitlePropertyMetadata> findPropertiesMetadata() {
        return range(0, this.argumentExtractors.size())
                .mapToObj(
                        i -> new SubTableTitlePropertyMetadata(
                                i,
                                this.argumentExtractors.get(i)
                        )
                )
                .toList();
    }

    public static SubTableTitleMetadataBuilder builder() {
        return new SubTableTitleMetadataBuilder();
    }

    @Value
    @AllArgsConstructor(access = PRIVATE)
    public class SubTableTitlePropertyMetadata {
        int index;
        SpecificationPropertyExtractor extractor;

        public String findPropertyName() {
            return this.extractor.getPropertyName();
        }

        public String findTitleRegex() {
            return SubTableTitleMetadata.this.regex;
        }

        public int findGroupIndexInRegex() {
            return this.index + 1;
        }
    }

    //TODO: stop in refactoring it
    @NoArgsConstructor(access = PRIVATE)
    public static final class SubTableTitleMetadataBuilder extends BuilderRequiringAllProperties<SubTableTitleMetadata> {
        private String templateWithPropertyNames;
        private List<SpecificationPropertyExtractor> argumentExtractors;

        public void template(final String template) {
            this.templateWithPropertyNames = template;
        }

        public void argumentExtractor(final SpecificationPropertyExtractor extractor) {
            this.initializeArgumentExtractorsIfNecessary();
            this.argumentExtractors.add(extractor);
        }

        @Override
        protected Stream<Object> findProperties() {
            return Stream.of(this.templateWithPropertyNames, this.argumentExtractors);
        }

        @Override
        protected SubTableTitleMetadata buildAfterStateValidation() {
            return new SubTableTitleMetadata(this.templateWithPropertyNames, this.argumentExtractors);
        }

        private void initializeArgumentExtractorsIfNecessary() {
            if (this.argumentExtractors == null) {
                this.argumentExtractors = new ArrayList<>();
            }
        }
    }
}
