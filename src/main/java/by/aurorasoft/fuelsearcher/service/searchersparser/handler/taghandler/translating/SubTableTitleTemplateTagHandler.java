package by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating;

import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.service.dictionary.SpecificationPropertyExtractorDictionary;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.exception.NoSuchKeyException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.regex.Pattern.compile;

@Component
public final class SubTableTitleTemplateTagHandler extends TranslatingTagHandler<SpecificationPropertyExtractor> {
    private static final String TAG_NAME = "sub-table-title-template";

    private static final String PROPERTY_PLACE_REGEX = "\\{([^{}]+)}";
    private static final int PROPERTY_GROUP_NUMBER = 1;
    private static final Pattern PROPERTY_PLACE_PATTERN = compile(PROPERTY_PLACE_REGEX);

    private static final String STRING_FILLER = "%s";

    public SubTableTitleTemplateTagHandler(final SpecificationPropertyExtractorDictionary dictionary) {
        super(TAG_NAME, dictionary, NoSuchSpecificationPropertyExtractorException::new);
    }

    @Override
    public void handleStartTag(final SearchersParsingContext context) {

    }

    @Override
    protected Stream<String> findAliases(final SearchersParsingContext context) {
        final String initialSubTableTitleTemplate = context.getLastContent();
        return PROPERTY_PLACE_PATTERN.matcher(initialSubTableTitleTemplate)
                .results()
                .map(matchResult -> matchResult.group(PROPERTY_GROUP_NUMBER));
    }

    @Override
    protected void accumulateTranslatedValue(final SearchersParsingContext context,
                                             final SpecificationPropertyExtractor extractor) {
        context.accumulateSubTableTitleTemplateArgumentExtractor(extractor);
    }

    @Override
    protected void accumulateAdditionalValues(final SearchersParsingContext context) {
        final String subTableTitleTemplate = findSubTableTitleTemplateWithStringFillers(context);
        context.accumulateSubTableTitleTemplate(subTableTitleTemplate);
    }

    private static String findSubTableTitleTemplateWithStringFillers(final SearchersParsingContext context) {
        final String initialSubTableTitleTemplate = context.getLastContent();
        return initialSubTableTitleTemplate.replaceAll(PROPERTY_PLACE_REGEX, STRING_FILLER);
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
