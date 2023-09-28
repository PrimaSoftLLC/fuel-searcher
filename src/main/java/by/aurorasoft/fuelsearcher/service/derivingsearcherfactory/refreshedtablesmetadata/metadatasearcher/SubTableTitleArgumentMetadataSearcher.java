package by.aurorasoft.fuelsearcher.service.derivingsearcherfactory.refreshedtablesmetadata.metadatasearcher;

import by.aurorasoft.fuelsearcher.model.SubTableTitleMetadata.SubTableTitleArgumentMetadata;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.XWPFParagraphUtil.extractText;
import static java.util.regex.Pattern.compile;

@Component
public final class SubTableTitleArgumentMetadataSearcher extends PropertyMetadataSearcher<SubTableTitleArgumentMetadata> {

    public SubTableTitleArgumentMetadataSearcher() {
        super(SubTableTitleArgumentMetadata.class);
    }

    @Override
    protected Stream<String> findAllowableValues(final List<IBodyElement> tableElements,
                                                 final SubTableTitleArgumentMetadata metadata) {
        return tableElements.stream()
                .filter(XWPFParagraph.class::isInstance)
                .map(XWPFParagraph.class::cast)
                .map(subTableTitleParagraph -> extractArgumentValue(subTableTitleParagraph, metadata));
    }

    @Override
    protected boolean isAllowableValuesDuplicated() {
        return true;
    }

    private static String extractArgumentValue(final XWPFParagraph subTableTitleParagraph,
                                               final SubTableTitleArgumentMetadata metadata) {
        final String title = extractText(subTableTitleParagraph);
        final Matcher titleMatcher = matchTitle(title, metadata);
        final int argumentGroupIndex = metadata.findGroupIndexInRegex();
        return titleMatcher.group(argumentGroupIndex);
    }

    private static Matcher matchTitle(final String title, final SubTableTitleArgumentMetadata metadata) {
        final String regex = metadata.findTitleRegex();
        final Pattern pattern = compile(regex);
        final Matcher matcher = pattern.matcher(title);
        if (!matcher.matches()) {
            throw new SubTableTitleArgumentMetadataSearchingException(
                    "Searching argument's metadata was failed for title of sub table: '%s'".formatted(title)
            );
        }
        return matcher;
    }

    private static final class SubTableTitleArgumentMetadataSearchingException extends RuntimeException {

        @SuppressWarnings("unused")
        public SubTableTitleArgumentMetadataSearchingException() {

        }

        public SubTableTitleArgumentMetadataSearchingException(final String description) {
            super(description);
        }

        @SuppressWarnings("unused")
        public SubTableTitleArgumentMetadataSearchingException(final Exception cause) {
            super(cause);
        }

        @SuppressWarnings("unused")
        public SubTableTitleArgumentMetadataSearchingException(final String description, final Exception cause) {
            super(description, cause);
        }

    }
}
