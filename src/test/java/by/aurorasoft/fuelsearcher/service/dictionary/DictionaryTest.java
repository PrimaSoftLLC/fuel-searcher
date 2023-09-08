package by.aurorasoft.fuelsearcher.service.dictionary;

import lombok.RequiredArgsConstructor;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

public final class DictionaryTest {
    private static final String FIELD_NAME_VALUES_BY_ALIAS = "valuesByAliases";

    @Test
    public void dictionaryShouldBeCreated()
            throws Exception {
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
    private static Map<String, TestObject> findValuesByAliases(final Dictionary<TestObject> dictionary)
            throws Exception {
        final Field field = Dictionary.class.getDeclaredField(FIELD_NAME_VALUES_BY_ALIAS);
        field.setAccessible(true);
        try {
            return (Map<String, TestObject>) field.get(dictionary);
        } finally {
            field.setAccessible(false);
        }
    }

    @RequiredArgsConstructor
    private static final class TestObject implements Translatable {
        private final String alias;

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