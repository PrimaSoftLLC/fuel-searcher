package com.aurorasoft.fuelsearcher.crud.service;

import com.aurorasoft.fuelsearcher.base.AbstractContextTest;
import com.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
import com.aurorasoft.fuelsearcher.crud.model.entity.PropertyMetadataEntity;
import com.aurorasoft.fuelsearcher.crud.model.entity.TableMetadataEntity;
import by.nhorushko.crudgeneric.v2.domain.AbstractEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public final class TableMetadataServiceTest extends AbstractContextTest {
    private static final String HQL_QUERY_TO_FIND_ALL_TABLES_METADATA = "SELECT e FROM TableMetadataEntity e";
    private static final String HQL_QUERY_TO_FIND_ALL_PROPERTIES_METADATA = "SELECT e FROM PropertyMetadataEntity e";

    @Autowired
    private TableMetadataService service;

    @Test
    public void metadataShouldBeFoundByTableName() {
        final String givenTableName = "ПЕРВАЯ ТАБЛИЦА";

        final Optional<TableMetadata> optionalActual = this.service.findByTableName(givenTableName);
        assertTrue(optionalActual.isPresent());
        final TableMetadata actual = optionalActual.get();
        final Long expectedId = 255L;
        assertEquals(expectedId, actual.getId());
    }

    @Test
    public void metadataShouldNotBeFoundByTableName() {
        final String givenTableName = "НЕСУЩЕСТВУЮЩАЯ ТАБЛИЦА";

        final Optional<TableMetadata> optionalActual = this.service.findByTableName(givenTableName);
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void allTablePropertiesShouldBeDeleted() {
        this.service.deleteAll();

        assertTrue(this.areTablesMetadataNotExist());
        assertTrue(this.arePropertiesMetadataNotExist());
    }

    private boolean areTablesMetadataNotExist() {
        return this.areEntitiesNotExist(
                HQL_QUERY_TO_FIND_ALL_TABLES_METADATA,
                TableMetadataEntity.class
        );
    }

    private boolean arePropertiesMetadataNotExist() {
        return this.areEntitiesNotExist(
                HQL_QUERY_TO_FIND_ALL_PROPERTIES_METADATA,
                PropertyMetadataEntity.class
        );
    }

    private boolean areEntitiesNotExist(final String hqlQueryToSelectAllEntities,
                                        final Class<? extends AbstractEntity<?>> entityType) {
        return super.entityManager.createQuery(hqlQueryToSelectAllEntities, entityType)
                .getResultStream()
                .findAny()
                .isEmpty();
    }
}
