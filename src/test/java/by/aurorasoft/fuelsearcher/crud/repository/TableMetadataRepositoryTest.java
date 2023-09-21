package by.aurorasoft.fuelsearcher.crud.repository;

import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
import by.aurorasoft.fuelsearcher.crud.model.entity.PropertyMetadataEntity;
import by.aurorasoft.fuelsearcher.crud.model.entity.TableMetadataEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.hibernate.Hibernate.isInitialized;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public final class TableMetadataRepositoryTest extends AbstractContextTest {

    @Autowired
    private TableMetadataRepository repository;

    @Test
    public void metadataShouldBeFoundById() {
        final Long givenId = 255L;

        super.startQueryCount();
        final Optional<TableMetadataEntity> optionalActual = this.repository.findById(givenId);
        super.checkQueryCount(1);

        assertTrue(optionalActual.isPresent());
        final TableMetadataEntity actual = optionalActual.get();
        final TableMetadataEntity expected = TableMetadataEntity.builder()
                .id(givenId)
                .tableName("ПЕРВАЯ ТАБЛИЦА")
                .propertiesMetadata(
                        List.of(
                                super.entityManager.getReference(PropertyMetadataEntity.class, 255L),
                                super.entityManager.getReference(PropertyMetadataEntity.class, 256L)
                        )
                )
                .build();
        checkEquals(expected, actual);
    }

    @Test
    public void metadataShouldBeSaved() {
        final TableMetadataEntity givenMetadata = TableMetadataEntity.builder()
                .tableName("имя таблицы")
                .build();

        super.startQueryCount();
        this.repository.save(givenMetadata);
        super.checkQueryCount(1);
    }

    @Test
    public void metadataShouldBeFoundByTableName() {
        final String givenTableName = "ПЕРВАЯ ТАБЛИЦА";

        super.startQueryCount();
        final Optional<TableMetadataEntity> optionalActual = this.repository.findByTableName(givenTableName);
        super.checkQueryCount(1);

        assertTrue(optionalActual.isPresent());
        final TableMetadataEntity actual = optionalActual.get();
        assertTrue(isInitialized(actual.getPropertiesMetadata()));
        final TableMetadataEntity expected = TableMetadataEntity.builder()
                .id(255L)
                .tableName(givenTableName)
                .propertiesMetadata(
                        List.of(
                                super.entityManager.getReference(PropertyMetadataEntity.class, 255L),
                                super.entityManager.getReference(PropertyMetadataEntity.class, 256L)
                        )
                )
                .build();
        checkEquals(expected, actual);
    }

    @Test
    public void metadataShouldNotBeFoundByTableName() {
        final String givenTableName = "НЕСУЩЕСТВУЮЩАЯ ТАБЛИЦА";

        super.startQueryCount();
        final Optional<TableMetadataEntity> optionalActual = this.repository.findByTableName(givenTableName);
        super.checkQueryCount(1);

        assertTrue(optionalActual.isEmpty());
    }

    private static void checkEquals(final TableMetadataEntity expected, final TableMetadataEntity actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTableName(), actual.getTableName());
        assertEquals(
                findPropertyMetadataIds(expected),
                findPropertyMetadataIds(actual)
        );
    }

    private static Set<Long> findPropertyMetadataIds(final TableMetadataEntity metadata) {
        return metadata.getPropertiesMetadata()
                .stream()
                .map(PropertyMetadataEntity::getId)
                .collect(toSet());
    }
}
