package com.aurorasoft.fuelsearcher.crud.model.entity;

import by.nhorushko.crudgeneric.v2.domain.AbstractEntity;

import java.util.Objects;

public abstract class BaseEntity<IdType> implements AbstractEntity<IdType> {

    @Override
    @SuppressWarnings("unchecked")
    public final boolean equals(final Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null) {
            return false;
        }
        if (this.getClass() != otherObject.getClass()) {
            return false;
        }
        final BaseEntity<IdType> other = (BaseEntity<IdType>) otherObject;
        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(this.getId());
    }
}
