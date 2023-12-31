package com.aurorasoft.fuelsearcher.service.builder;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public final class BuilderRequiringAllPropertiesTest {

    @Test
    public void objectShouldBeBuilt() {
        final TestBuilderRequiringAllProperties givenBuilder = new TestBuilderRequiringAllProperties();

        final Object givenFirstProperty = new Object();
        givenBuilder.firstProperty(givenFirstProperty);

        final Object givenSecondProperty = new Object();
        givenBuilder.secondProperty(givenSecondProperty);

        final TestObject actual = givenBuilder.build();
        final TestObject expected = new TestObject(givenFirstProperty, givenSecondProperty);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalStateException.class)
    public void objectShouldNotBeBuiltBecauseOfNotDefinedProperty() {
        final TestBuilderRequiringAllProperties givenBuilder = new TestBuilderRequiringAllProperties();

        final Object givenFirstProperty = new Object();
        givenBuilder.firstProperty(givenFirstProperty);

        givenBuilder.build();
    }

    private record TestObject(Object firstProperty, Object secondProperty) {
    }

    private static final class TestBuilderRequiringAllProperties extends BuilderRequiringAllProperties<TestObject> {
        private Object firstProperty;
        private Object secondProperty;

        @Override
        protected Stream<Object> findProperties() {
            return Stream.of(this.firstProperty, this.secondProperty);
        }

        @Override
        protected TestObject buildAfterStateValidation() {
            return new TestObject(this.firstProperty, this.secondProperty);
        }

        public void firstProperty(final Object firstProperty) {
            this.firstProperty = firstProperty;
        }

        public void secondProperty(final Object secondProperty) {
            this.secondProperty = secondProperty;
        }
    }
}
