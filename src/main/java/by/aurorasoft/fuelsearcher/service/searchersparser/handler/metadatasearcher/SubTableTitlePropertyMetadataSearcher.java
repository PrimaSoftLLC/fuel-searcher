package by.aurorasoft.fuelsearcher.service.searchersparser.handler.metadatasearcher;

import by.aurorasoft.fuelsearcher.model.SubTableTitleMetadata.SubTableTitlePropertyMetadata;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.XWPFParagraphUtil.extractText;
import static java.util.regex.Pattern.compile;

//TODO: test
@Component
public final class SubTableTitlePropertyMetadataSearcher extends PropertyMetadataSearcher<SubTableTitlePropertyMetadata> {

    public SubTableTitlePropertyMetadataSearcher() {
        super(SubTableTitlePropertyMetadata.class);
    }

    @Override
    protected String findPropertyName(final SubTableTitlePropertyMetadata metadata) {
        return metadata.findPropertyName();
    }

    @Override
    protected Stream<String> findAllowableValues(final List<IBodyElement> tableElements,
                                                 final SubTableTitlePropertyMetadata metadata) {
        return tableElements.stream()
                .filter(XWPFParagraph.class::isInstance)
                .map(XWPFParagraph.class::cast)
                .map(subTableTitleParagraph -> extractPropertyValue(subTableTitleParagraph, metadata));
    }

    @Override
    protected boolean isAllowableValuesDuplicated() {
        return true;
    }

    private static String extractPropertyValue(final XWPFParagraph subTableTitleParagraph,
                                               final SubTableTitlePropertyMetadata metadata) {
        final String title = extractText(subTableTitleParagraph);
        final Matcher titleMatcher = matchTitle(title, metadata);
        final int propertyGroupIndex = metadata.findGroupIndexInRegex();
        return titleMatcher.group(propertyGroupIndex);
    }

    private static Matcher matchTitle(final String title, final SubTableTitlePropertyMetadata metadata) {
        final String regex = metadata.findTitleRegex();
        final Pattern pattern = compile(regex);
        final Matcher matcher = pattern.matcher(title);
        if (!matcher.matches()) {
            throw new SubTableTitlePropertyMetadataSearchingException(
                    "Searching property metadata was failed for title of sub table: '%s'".formatted(title)
            );
        }
        return matcher;
    }

    private static final class SubTableTitlePropertyMetadataSearchingException extends RuntimeException {

        @SuppressWarnings("unused")
        public SubTableTitlePropertyMetadataSearchingException() {

        }

        public SubTableTitlePropertyMetadataSearchingException(final String description) {
            super(description);
        }

        @SuppressWarnings("unused")
        public SubTableTitlePropertyMetadataSearchingException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public SubTableTitlePropertyMetadataSearchingException(final String description, final Exception cause) {
            super(description, cause);
        }

    }
}
