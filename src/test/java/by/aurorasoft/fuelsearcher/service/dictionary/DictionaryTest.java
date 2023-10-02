package by.aurorasoft.fuelsearcher.service.dictionary;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.aurorasoft.fuelsearcher.testutil.ReflectionUtil.findProperty;
import static org.junit.Assert.*;

public final class DictionaryTest {
    private static final String FIELD_NAME_VALUES_BY_ALIAS = "valuesByAliases";

    @Test
    public void dictionaryShouldBeCreated() {
        final String firstGivenAlias = "first";
        final TestObject firstGivenObject = new TestObject(firstGivenAlias);

        final String secondGivenAlias = "second";
        final TestObject secondGivenObject = new TestObject(secondGivenAlias);

        final String thirdGivenAlias = "third";
        final TestObject thirdGivenObject = new TestObject(thirdGivenAlias);

        final List<TestObject> givenObjects = List.of(firstGivenObject, secondGivenObject, thirdGivenObject);

        final Dictionary<TestObject> actual = new TestDictionary(givenObjects);
        final Map<String, TestObject> actualValuesByAliases = findValuesByAliases(actual);
        final Map<String, TestObject> expectedValuesByAliases = Map.of(
                firstGivenAlias, firstGivenObject,
                secondGivenAlias, secondGivenObject,
                thirdGivenAlias, thirdGivenObject
        );
        assertEquals(expectedValuesByAliases, actualValuesByAliases);
    }

    @Test(expected = Exception.class)
    public void dictionaryShouldNotBeCreatedBecauseOfSeveralValuesForOneAlias() {
        final String firstGivenAlias = "first";
        final TestObject firstGivenObject = new TestObject(firstGivenAlias);

        final TestObject secondGivenObject = new TestObject(firstGivenAlias);

        final String thirdGivenAlias = "third";
        final TestObject thirdGivenObject = new TestObject(thirdGivenAlias);

        final List<TestObject> givenObjects = List.of(firstGivenObject, secondGivenObject, thirdGivenObject);

        new TestDictionary(givenObjects);
    }

    @Test
    public void valueShouldBeFoundByAlias() {
        final String firstGivenAlias = "first";
        final TestObject firstGivenObject = new TestObject(firstGivenAlias);

        final String secondGivenAlias = "second";
        final TestObject secondGivenObject = new TestObject(secondGivenAlias);

        final String thirdGivenAlias = "third";
        final TestObject thirdGivenObject = new TestObject(thirdGivenAlias);

        final List<TestObject> givenObjects = List.of(firstGivenObject, secondGivenObject, thirdGivenObject);
        final Dictionary<TestObject> givenDictionary = new TestDictionary(givenObjects);

        final Optional<TestObject> actualOptional = givenDictionary.find(secondGivenAlias);
        assertTrue(actualOptional.isPresent());
        final TestObject actual = actualOptional.get();
        assertSame(secondGivenObject, actual);
    }

    @Test
    public void valueShouldNotBeFoundByAlias() {
        final String firstGivenAlias = "first";
        final TestObject firstGivenObject = new TestObject(firstGivenAlias);

        final String secondGivenAlias = "second";
        final TestObject secondGivenObject = new TestObject(secondGivenAlias);

        final String thirdGivenAlias = "third";
        final TestObject thirdGivenObject = new TestObject(thirdGivenAlias);

        final List<TestObject> givenObjects = List.of(firstGivenObject, secondGivenObject, thirdGivenObject);
        final Dictionary<TestObject> givenDictionary = new TestDictionary(givenObjects);

        final Optional<TestObject> actualOptional = givenDictionary.find("not-exist-alias");
        assertTrue(actualOptional.isEmpty());
    }

    @SuppressWarnings("unchecked")
    private static Map<String, TestObject> findValuesByAliases(final Dictionary<TestObject> dictionary) {
        return findProperty(
                dictionary,
                FIELD_NAME_VALUES_BY_ALIAS,
                Map.class
        );
    }

    private record TestObject(String alias) implements Translatable {
        @Override
        public String findAlias() {
            return this.alias;
        }
    }

    private static final class TestDictionary extends Dictionary<TestObject> {

        public TestDictionary(final List<TestObject> values) {
            super(values);
        }

    }
}
