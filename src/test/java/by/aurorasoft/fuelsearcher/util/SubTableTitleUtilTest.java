package by.aurorasoft.fuelsearcher.util;

import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static by.aurorasoft.fuelsearcher.util.SubTableTitleUtil.findPropertyNames;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public final class SubTableTitleUtilTest {
    private static final List<String> GIVEN_TEMPLATES_WITH_PROPERTY_NAMES = List.of(
            "РАЗБРАСЫВАТЕЛЕМ {механизм} (трактор {трактор})",
            "Опрыскивателем {механизм}",
            "{трактор} с разбрасывателем {механизм}. Производительность погрузчика более 60 т/ч",
            "{трактор} с {механизм}",
            "{комбайн} Соотношение массы зерна к массе соломы {соотношение массы зерна к массе соломы}",
            "ТРАКТОР {трактор} + {механизм}. При механизированной погрузке и разгрузке"
    );

    @Test
    public void propertyNamesShouldBeFound() {
        final Stream<String> actual = GIVEN_TEMPLATES_WITH_PROPERTY_NAMES.stream()
                .flatMap(SubTableTitleUtil::findPropertyNames);
        final List<String> actualAsList = actual.toList();
        final List<String> expectedAsList = List.of(
                "механизм", "трактор",
                "механизм",
                "трактор", "механизм",
                "трактор", "механизм",
                "комбайн", "соотношение массы зерна к массе соломы",
                "трактор", "механизм"
        );
        assertEquals(expectedAsList, actualAsList);
    }

    @Test
    public void propertyNamesShouldNotBeFound() {
        final String givenTemplate = "template";

        final Stream<String> actual = findPropertyNames(givenTemplate);
        final boolean actualEmpty = actual.findFirst().isEmpty();
        assertTrue(actualEmpty);
    }
}
