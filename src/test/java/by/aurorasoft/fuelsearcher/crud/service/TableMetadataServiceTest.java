package by.aurorasoft.fuelsearcher.crud.service;

import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
import by.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
import by.aurorasoft.fuelsearcher.crud.model.entity.PropertyMetadataEntity;
import by.aurorasoft.fuelsearcher.crud.model.entity.TableMetadataEntity;
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
        final String givenTableName = "ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ";

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
