package by.aurorasoft.fuelsearcher.model;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.builder.BuilderRequiringAllProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor(access = PRIVATE)
public class SubTableTitleMetadata {
    String template;
    List<SpecificationPropertyExtractor> argumentExtractors;

    @NoArgsConstructor(access = PRIVATE)
    public static final class SubTableTitleMetadataBuilder extends BuilderRequiringAllProperties<SubTableTitleMetadata> {
        private String template;
        private List<SpecificationPropertyExtractor> argumentExtractors;

        public void template(final String template) {
            this.template = template;
        }

        public void argumentExtractor(final SpecificationPropertyExtractor extractor) {
            this.initializeArgumentExtractorsIfNecessary();
            this.argumentExtractors.add(extractor);
        }

        @Override
        protected Stream<Object> findProperties() {
            return Stream.of(this.template, this.argumentExtractors);
        }

        @Override
        protected SubTableTitleMetadata buildAfterStateValidation() {
            return new SubTableTitleMetadata(this.template, this.argumentExtractors);
        }

        private void initializeArgumentExtractorsIfNecessary() {
            if (this.argumentExtractors == null) {
                this.argumentExtractors = new ArrayList<>();
            }
        }
    }
}
