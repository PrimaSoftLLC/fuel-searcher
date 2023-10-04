package com.aurorasoft.fuelsearcher.crud.model.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public final class BaseEntityTest {

    @Test
    public void entitiesShouldBeEqual() {
        final TestEntity firstGivenEntity = new TestEntity();
        firstGivenEntity.setId(255L);

        final TestEntity secondGivenEntity = new TestEntity();
        secondGivenEntity.setId(255L);

        final boolean actual = firstGivenEntity.equals(secondGivenEntity);
        assertTrue(actual);
    }

    @Test
    public void entitiesShouldNotBeEqual() {
        final TestEntity firstGivenEntity = new TestEntity();
        firstGivenEntity.setId(255L);

        final TestEntity secondGivenEntity = new TestEntity();
        secondGivenEntity.setId(256L);

        final boolean actual = firstGivenEntity.equals(secondGivenEntity);
        assertFalse(actual);
    }

    @Test
    public void entitiesShouldNotBeEqualBecauseOfSecondEntityIsNull() {
        final TestEntity firstGivenEntity = new TestEntity();
        firstGivenEntity.setId(255L);

        final TestEntity secondGivenEntity = null;

        @SuppressWarnings("all") final boolean actual = firstGivenEntity.equals(secondGivenEntity);
        assertFalse(actual);
    }

    @Test
    @SuppressWarnings("EqualsBetweenInconvertibleTypes")
    public void entitiesShouldNotBeEqualBecauseOfDifferentTypes() {
        final TestEntity firstGivenEntity = new TestEntity();
        firstGivenEntity.setId(255L);

        final BaseEntity<Long> secondGivenEntity = new BaseEntity<>() {
            private Long id;

            @Override
            public void setId(final Long id) {
                this.id = id;
            }

            @Override
            public Long getId() {
                return this.id;
            }
        };
        secondGivenEntity.setId(255L);

        final boolean actual = firstGivenEntity.equals(secondGivenEntity);
        assertFalse(actual);
    }

    @Test
    public void hashCodeShouldBeFound() {
        final TestEntity givenEntity = new TestEntity();
        givenEntity.setId(255L);

        final int actual = givenEntity.hashCode();
        final int expected = 255;
        assertEquals(expected, actual);
    }

    private static final class TestEntity extends BaseEntity<Long> {
        private Long id;

        @Override
        public void setId(final Long id) {
            this.id = id;
        }

        @Override
        public Long getId() {
            return this.id;
        }
    }
}
