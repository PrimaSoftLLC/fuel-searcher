package com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating;

import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import com.aurorasoft.fuelsearcher.service.dictionary.SpecificationPropertyExtractorDictionary;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.exception.NoSuchKeyException;
import com.aurorasoft.fuelsearcher.util.SubTableTitleUtil;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

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
        return SubTableTitleUtil.findPropertyNames(subTableTitleTemplateWithPropertyNames);
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
