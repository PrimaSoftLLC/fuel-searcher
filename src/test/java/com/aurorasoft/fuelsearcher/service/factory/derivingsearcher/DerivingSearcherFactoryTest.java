package com.aurorasoft.fuelsearcher.service.factory.derivingsearcher;

import com.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import org.junit.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public final class DerivingSearcherFactoryTest {

    @Test
    public void derivedObjectsShouldBeCreated() {
        final List<FuelSearcher> givenSearchers = List.of(
                mock(FuelSearcher.class),
                mock(FuelSearcher.class),
                mock(FuelSearcher.class)
        );
        final Object givenCreatedDerivedObject = new Object();
        final TestDerivingSearcherFactory givenFactory = new TestDerivingSearcherFactory(
                givenSearchers,
                givenCreatedDerivedObject
        );

        final List<Object> actual = givenFactory.create();
        final List<Object> expected = List.of(
                givenCreatedDerivedObject,
                givenCreatedDerivedObject,
                givenCreatedDerivedObject
        );
        assertEquals(expected, actual);
    }

    @Test
    public void derivedObjectsShouldNotBeCreated() {
        final List<FuelSearcher> givenSearchers = emptyList();
        final Object givenCreatedDerivedObject = new Object();
        final TestDerivingSearcherFactory givenFactory = new TestDerivingSearcherFactory(
                givenSearchers,
                givenCreatedDerivedObject
        );

        final List<Object> actual = givenFactory.create();
        assertTrue(actual.isEmpty());
    }

    private static final class TestDerivingSearcherFactory extends DerivingSearcherFactory<Object> {
        private final Object createdDerivedObject;

        public TestDerivingSearcherFactory(final List<FuelSearcher> searchers,
                                           final Object createdDerivedObject) {
            super(searchers);
            this.createdDerivedObject = createdDerivedObject;
        }

        @Override
        protected Object createDerivedObject(final FuelSearcher searcher) {
            return this.createdDerivedObject;
        }
    }
}
