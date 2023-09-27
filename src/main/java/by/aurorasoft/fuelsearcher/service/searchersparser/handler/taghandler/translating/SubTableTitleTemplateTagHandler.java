package by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.dictionary.SpecificationPropertyExtractorDictionary;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.exception.NoSuchKeyException;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.SubTableTitleUtil.findPropertyNames;

@Component
public final class SubTableTitleTemplateTagHandler extends TranslatingTagHandler<SpecificationPropertyExtractor> {
    private static final String TAG_NAME = "sub-table-title-template";

    public SubTableTitleTemplateTagHandler(final SpecificationPropertyExtractorDictionary dictionary) {
        super(TAG_NAME, dictionary, NoSuchSpecificationPropertyExtractorException::new);
    }

    @Override
    public void handleStartTag(final SearchersParsingContext context) {

    }

    @Override
    protected Stream<String> findAliases(final SearchersParsingContext context) {
        final String subTableTitleTemplateWithPropertyNames = context.getLastContent();
        return findPropertyNames(subTableTitleTemplateWithPropertyNames);
    }

    @Override
    protected void accumulateTranslatedValue(final SearchersParsingContext context,
                                             final SpecificationPropertyExtractor extractor) {
        context.accumulateSubTableTitleTemplateArgumentExtractor(extractor);
    }

    @Override
    protected void accumulateAdditionalValues(final SearchersParsingContext context) {
        final String templateWithPropertyNames = context.getLastContent();
        context.accumulateSubTableTitleTemplate(templateWithPropertyNames);
    }

    private static final class NoSuchSpecificationPropertyExtractorException extends NoSuchKeyException {

        @SuppressWarnings("unused")
        public NoSuchSpecificationPropertyExtractorException() {

        }

        public NoSuchSpecificationPropertyExtractorException(final String key) {
            super(key);
        }

        @SuppressWarnings("unused")
        public NoSuchSpecificationPropertyExtractorException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public NoSuchSpecificationPropertyExtractorException(final String key, final Exception cause) {
            super(key, cause);
        }
    }
}
