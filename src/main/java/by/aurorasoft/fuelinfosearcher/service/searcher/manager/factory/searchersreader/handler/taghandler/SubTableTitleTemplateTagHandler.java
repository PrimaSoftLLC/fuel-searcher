package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler;

import by.aurorasoft.fuelinfosearcher.functionalinterface.SpecificationPropertyExtractor;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.dictionary.SpecificationPropertyExtractorDictionary;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

//TODO: refactor
@Component
public final class SubTableTitleTemplateTagHandler extends TagHandler {
    private static final String TAG_NAME = "sub-table-title-template";

    private static final String PROPERTY_PLACE_REGEX = "\\{([^}]+)}";
    private static final int PROPERTY_GROUP_NUMBER = 1;
    private static final Pattern PROPERTY_PLACE_PATTERN = compile(PROPERTY_PLACE_REGEX);

    private final SpecificationPropertyExtractorDictionary propertyExtractorDictionary;

    public SubTableTitleTemplateTagHandler(final SpecificationPropertyExtractorDictionary propertyExtractorDictionary) {
        super(TAG_NAME);
        this.propertyExtractorDictionary = propertyExtractorDictionary;
    }

    @Override
    public void handleStartTag(final SearchersParsingContext context) {

    }

    @Override
    public void handleEndTag(final SearchersParsingContext context) {
        final String template = context.getLastContent();
        final Matcher propertyPlaceMatcher = PROPERTY_PLACE_PATTERN.matcher(template);
        while (propertyPlaceMatcher.find()) {
            final String currentProperty = propertyPlaceMatcher.group(PROPERTY_GROUP_NUMBER);
            final SpecificationPropertyExtractor extractor = this.propertyExtractorDictionary.find(currentProperty).orElseThrow();
            context.accumulateSubTableTitleTemplateArgumentExtractor(extractor);
        }

        final String template2 = template.replaceAll(PROPERTY_PLACE_REGEX, "%s");
        context.accumulateSubTableTitleTemplate(template2);
    }
}
