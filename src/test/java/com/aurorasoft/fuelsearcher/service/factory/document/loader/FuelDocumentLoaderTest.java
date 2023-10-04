package com.aurorasoft.fuelsearcher.service.factory.document.loader;

import com.aurorasoft.fuelsearcher.base.AbstractContextTest;
import com.aurorasoft.fuelsearcher.model.FuelDocument;
import com.aurorasoft.fuelsearcher.model.FuelTable;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static com.aurorasoft.fuelsearcher.util.XWPFParagraphUtil.extractText;
import static java.util.stream.IntStream.iterate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public final class FuelDocumentLoaderTest extends AbstractContextTest {
    private static final Set<FuelTableResearcher> TABLE_RESEARCHERS = Set.of(
            new SimpleFuelTableResearcher("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ"),
            new SimpleFuelTableResearcher("ВСПАШКА СТЕРНИ"),
            new SimpleFuelTableResearcher("ВСПАШКА МЕЛИОРИРУЕМЫХ ЗЕМЕЛЬ"),
            new SimpleFuelTableResearcher("СПЛОШНАЯ И КОМБИНИРОВАННАЯ ОБРАБОТКА ПОЧВЫ"),
            new SimpleFuelTableResearcher("ЛУЩЕНИЕ И ДИСКОВАНИЕ СТЕРНИ"),
            new SimpleFuelTableResearcher("ЛУЩЕНИЕ И ДИСКОВАНИЕ МНОГОЛЕТНИХ ТРАВ"),
            new SimpleFuelTableResearcher("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ПОСЕВОМ СЕЛЬСКОХОЗЯЙСТВЕННЫХ КУЛЬТУР: ПШЕНИЦЫ, РЖИ, ЯЧМЕНЯ, ОВСА, ГОРОХА, ЛЮПИНА, ВИКИ, ВИКООВСЯНОЙ СМЕСИ, ЛЬНА"),
            new SimpleFuelTableResearcher("ПОСЕВ КУКУРУЗЫ, ПОДСОЛНЕЧНИКА"),
            new SimpleFuelTableResearcher("ПОСЕВ САХАРНОЙ СВЕКЛЫ"),
            new SimpleFuelTableResearcher("ПРЕДПОСЕВНАЯ ОБРАБОТКА ПОЧВЫ С ОДНОВРЕМЕННЫМ ПОСЕВОМ РАПСА"),
            new CompositeFuelTableResearcher("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ", "РАЗБРАСЫВАТЕЛЕМ .+ \\(трактор .+\\)"),
            new CompositeFuelTableResearcher("ВНЕСЕНИЕ МИНЕРАЛЬНЫХ УДОБРЕНИЙ И СРЕДСТВ ЗАЩИТЫ РАСТЕНИЙ", "Опрыскивателем .+"),
            new CompositeFuelTableResearcher("ВНЕСЕНИЕ ОРГАНИЧЕСКИХ УДОБРЕНИЙ", ".+ с разбрасывателем .+\\. Производительность погрузчика более 60 т/ч"),
            new CompositeFuelTableResearcher("ВНЕСЕНИЕ ЖИДКИХ ОРГАНИЧЕСКИХ УДОБРЕНИЙ", ".+ с .+"),
            new SimpleFuelTableResearcher("КОШЕНИЕ СЕЯНЫХ И ЕСТЕСТВЕННЫХ ТРАВ"),
            new SimpleFuelTableResearcher("КОШЕНИЕ ТРАВ С ПЛЮЩЕНИЕМ"),
            new SimpleFuelTableResearcher("ВОРОШЕНИЕ СЕНА"),
            new SimpleFuelTableResearcher("СГРЕБАНИЕ СЕНА В ВАЛКИ"),
            new SimpleFuelTableResearcher("ПРЕССОВАНИЕ СЕНА ПОСЛЕ КОМБАЙНА"),
            new SimpleFuelTableResearcher("ПРЕССОВАНИЕ СОЛОМЫ ПОСЛЕ КОМБАЙНА"),
            new SimpleFuelTableResearcher("ПРЕССОВАНИЕ ПРОВЯЛЕННОЙ МАССЫ ПОСЛЕ КОМБАЙНА"),
            new SimpleFuelTableResearcher("ПРЕССОВАНИЕ ЛЕНТ ЛЬНА"),
            new SimpleFuelTableResearcher("ПОДБОР ПРОВЯЛЕННЫХ ТРАВ ИЗ ВАЛКОВ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА"),
            new CompositeFuelTableResearcher("ПРЯМОЕ КОМБАЙНИРОВАНИЕ ЗЕРНОВЫХ С ИЗМЕЛЬЧЕНИЕМ СОЛОМЫ", ".+ Соотношение массы зерна к массе соломы .+"),
            new SimpleFuelTableResearcher("УБОРКА КАРТОФЕЛЯ"),
            new SimpleFuelTableResearcher("УБОРКА КУКУРУЗЫ С ИЗМЕЛЬЧЕНИЕМ И ПОДАЧЕЙ В ТРАНСПОРТНЫЕ СРЕДСТВА"),
            new CompositeFuelTableResearcher("ТРАНСПОРТИРОВКА ГРУЗОВ ТРАКТОРАМИ С ОДНИМ ПРИЦЕПОМ", "ТРАКТОР .+ \\+ .+\\. При механизированной погрузке и разгрузке")
    );

    @Value("${fuel-document.path}")
    private String documentPath;

    @Autowired
    private FuelDocumentLoader loader;

    @Test
    public void documentShouldBeLoaded() {
        final FuelDocument actual = this.loader.load(this.documentPath);
        final List<FuelTable> actualTables = actual.tables();
        assertEquals(TABLE_RESEARCHERS.size(), actualTables.size());
        assertTrue(areEveryMatchAnyResearcher(actualTables));
    }

    @Test(expected = Exception.class)
    public void documentShouldNotBeLoadedBecauseOfWrongFilePath() {
        final String givenFilePath = "not-existing-file";
        this.loader.load(givenFilePath);
    }

    private static boolean areEveryMatchAnyResearcher(final List<FuelTable> actualTables) {
        return actualTables.stream().allMatch(FuelDocumentLoaderTest::isMatchAnyResearcher);
    }

    private static boolean isMatchAnyResearcher(final FuelTable actualTable) {
        return TABLE_RESEARCHERS.stream().anyMatch(researcher -> researcher.isMatch(actualTable));
    }

    @RequiredArgsConstructor
    private static abstract class FuelTableResearcher {
        private final String expectedTableName;

        public final boolean isMatch(final FuelTable actualTable) {
            final String actualTableName = actualTable.name();
            final List<IBodyElement> actualTableElements = actualTable.elements();
            return this.isTableNameMatch(actualTableName) && this.areElementsMatch(actualTableElements);
        }

        protected abstract boolean areElementsMatch(final List<IBodyElement> actualTableElements);

        private boolean isTableNameMatch(final String actualTableName) {
            return Objects.equals(this.expectedTableName, actualTableName);
        }
    }

    private static final class SimpleFuelTableResearcher extends FuelTableResearcher {

        public SimpleFuelTableResearcher(final String expectedTableName) {
            super(expectedTableName);
        }

        @Override
        protected boolean areElementsMatch(final List<IBodyElement> actualTableElements) {
            return isOnlyOneElement(actualTableElements) && isFirstElementTable(actualTableElements);
        }

        private static boolean isOnlyOneElement(final List<IBodyElement> actualTableElements) {
            return actualTableElements.size() == 1;
        }

        private static boolean isFirstElementTable(final List<IBodyElement> actualTableElements) {
            final IBodyElement firstElement = actualTableElements.get(0);
            return firstElement instanceof XWPFTable;
        }
    }

    private static final class CompositeFuelTableResearcher extends FuelTableResearcher {
        private final String expectedSubTableTitleRegex;

        public CompositeFuelTableResearcher(final String expectedTableName,
                                            final String expectedSubTableTitleRegex) {
            super(expectedTableName);
            this.expectedSubTableTitleRegex = expectedSubTableTitleRegex;
        }

        @Override
        protected boolean areElementsMatch(final List<IBodyElement> actualTableElements) {
            return this.areMatchingSubTableTitlesOnEvenIndexes(actualTableElements)
                    && areTablesOnNotEvenIndexes(actualTableElements);
        }

        private boolean areMatchingSubTableTitlesOnEvenIndexes(final List<IBodyElement> elements) {
            return areMatchingPredicateAfterOne(
                    elements,
                    0,
                    this::isMatchingSubTableTitle
            );
        }

        private static boolean areTablesOnNotEvenIndexes(final List<IBodyElement> elements) {
            return areMatchingPredicateAfterOne(
                    elements,
                    1,
                    CompositeFuelTableResearcher::isTable
            );
        }

        private static boolean areMatchingPredicateAfterOne(final List<IBodyElement> elements,
                                                            final int firstElementIndex,
                                                            final Predicate<IBodyElement> predicate) {
            return findIndexesAfterOne(elements, firstElementIndex)
                    .mapToObj(elements::get)
                    .allMatch(predicate);
        }

        private static IntStream findIndexesAfterOne(final List<IBodyElement> elements, final int firstIndex) {
            return iterate(firstIndex, i -> i < elements.size(), i -> i + 2);
        }

        private boolean isMatchingSubTableTitle(final IBodyElement element) {
            return (element instanceof final XWPFParagraph paragraph) && this.isMatchingSubTableTitle(paragraph);
        }

        private boolean isMatchingSubTableTitle(final XWPFParagraph paragraph) {
            final String paragraphText = extractText(paragraph);
            return paragraphText.matches(this.expectedSubTableTitleRegex);
        }

        private static boolean isTable(final IBodyElement element) {
            return element instanceof XWPFTable;
        }
    }
}
