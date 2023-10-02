package by.aurorasoft.fuelsearcher.service.factory.derivingsearcher.refreshedtablesmetadata.metadatasearcher;

import by.aurorasoft.fuelsearcher.model.SubTableTitleMetadata;
import by.aurorasoft.fuelsearcher.model.SubTableTitleMetadata.SubTableTitleArgumentMetadata;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.SpecificationPropertyExtractor;
import by.aurorasoft.fuelsearcher.util.XWPFParagraphUtil;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.List;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.testutil.ReflectionUtil.createObject;
import static by.aurorasoft.fuelsearcher.util.XWPFParagraphUtil.extractText;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public final class SubTableTitleArgumentMetadataSearcherTest {
    private final SubTableTitleArgumentMetadataSearcher searcher = new SubTableTitleArgumentMetadataSearcher();

    @Test
    public void allowableValuesShouldBeFound() {
        try (final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil = mockStatic(XWPFParagraphUtil.class)) {
            final XWPFParagraph firstGivenParagraph = createParagraph(
                    "трактор-1 с механизм-1",
                    mockedParagraphUtil
            );
            final XWPFParagraph secondGivenParagraph = createParagraph(
                    "трактор-2 с механизм-2",
                    mockedParagraphUtil
            );

            final List<IBodyElement> givenTableElements = List.of(
                    firstGivenParagraph,
                    mock(XWPFTable.class),
                    secondGivenParagraph,
                    mock(XWPFTable.class)
            );

            final String givenTitleRegex = "(.+) с (.+)";
            final SubTableTitleMetadata givenTitleMetadata = createTitleMetadata(givenTitleRegex);
            final SubTableTitleArgumentMetadata givenTitleArgumentMetadata = createTitleArgumentMetadata(
                    givenTitleMetadata,
                    0
            );

            final Stream<String> actual = this.searcher.findAllowableValues(
                    givenTableElements,
                    givenTitleArgumentMetadata
            );
            final List<String> actualAsList = actual.toList();
            final List<String> expectedAsList = List.of("трактор-1", "трактор-2");
            assertEquals(expectedAsList, actualAsList);
        }
    }

    @Test
    public void allowableValuesShouldBeDuplicated() {
        final boolean actual = this.searcher.isAllowableValuesDuplicated();
        assertTrue(actual);
    }

    private static XWPFParagraph createParagraph(final String text,
                                                 final MockedStatic<XWPFParagraphUtil> mockedParagraphUtil) {
        final XWPFParagraph paragraph = mock(XWPFParagraph.class);
        mockedParagraphUtil.when(() -> extractText(same(paragraph))).thenReturn(text);
        return paragraph;
    }

    @SuppressWarnings("SameParameterValue")
    private static SubTableTitleMetadata createTitleMetadata(final String regex) {
        return createObject(
                SubTableTitleMetadata.class,
                new Class<?>[]{String.class, String.class, List.class},
                new Object[]{null, regex, emptyList()}
        );
    }

    @SuppressWarnings("SameParameterValue")
    private static SubTableTitleArgumentMetadata createTitleArgumentMetadata(final SubTableTitleMetadata titleMetadata,
                                                                             final int argumentIndex) {
        return createObject(
                SubTableTitleArgumentMetadata.class,
                new Class<?>[]{SubTableTitleMetadata.class, SpecificationPropertyExtractor.class, int.class},
                new Object[]{titleMetadata, null, argumentIndex}
        );
    }
}
