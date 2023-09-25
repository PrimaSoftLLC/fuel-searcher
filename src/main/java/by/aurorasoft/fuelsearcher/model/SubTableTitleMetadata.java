package by.aurorasoft.fuelsearcher.model;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.builder.BuilderRequiringAllProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.SubTableTitleUtil.findTemplateRegex;
import static by.aurorasoft.fuelsearcher.util.SubTableTitleUtil.findTemplateWithStringFillers;
import static java.util.stream.IntStream.range;
import static lombok.AccessLevel.PRIVATE;

//TODO: refactor tests
@Value
public class SubTableTitleMetadata {
    String templateWithStringFillers;
    String regex;
    List<SubTableTitleArgumentMetadata> argumentsMetadata;

    private SubTableTitleMetadata(final String templateWithStringFillers,
                                  final String regex,
                                  final List<SpecificationPropertyExtractor> argumentExtractors) {
        this.templateWithStringFillers = templateWithStringFillers;
        this.regex = regex;
        this.argumentsMetadata = this.findPropertiesMetadata(argumentExtractors);
    }

    public static SubTableTitleMetadataBuilder builder() {
        return new SubTableTitleMetadataBuilder();
    }

    private List<SubTableTitleArgumentMetadata> findPropertiesMetadata(
            final List<SpecificationPropertyExtractor> argumentExtractors) {
        return range(0, this.argumentsMetadata.size())
                .mapToObj(
                        i -> this.new SubTableTitleArgumentMetadata(
                                i,
                                argumentExtractors.get(i)
                        )
                )
                .toList();
    }

    @Value
    @AllArgsConstructor(access = PRIVATE)
    public class SubTableTitleArgumentMetadata {
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

    @NoArgsConstructor(access = PRIVATE)
    public static final class SubTableTitleMetadataBuilder extends BuilderRequiringAllProperties<SubTableTitleMetadata> {
        private String templateWithPropertyNames;
        private List<SpecificationPropertyExtractor> argumentExtractors;

        public void templateWithPropertyNames(final String template) {
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
            final String templateWithStringFillers = findTemplateWithStringFillers(this.templateWithPropertyNames);
            final String templateRegex = findTemplateRegex(this.templateWithPropertyNames);
            return new SubTableTitleMetadata(templateWithStringFillers, templateRegex, this.argumentExtractors);
        }

        private void initializeArgumentExtractorsIfNecessary() {
            if (this.argumentExtractors == null) {
                this.argumentExtractors = new ArrayList<>();
            }
        }
    }
}
