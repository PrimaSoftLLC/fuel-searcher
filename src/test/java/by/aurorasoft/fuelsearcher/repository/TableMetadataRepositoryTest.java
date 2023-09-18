package by.aurorasoft.fuelsearcher.repository;

import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
import by.aurorasoft.fuelsearcher.model.entity.PropertyMetadataEntity;
import by.aurorasoft.fuelsearcher.model.entity.TableMetadataEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

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
                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
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
                .tableName("ВСПАШКА ПЛАСТА МНОГОЛЕТНИХ ТРАВ")
                .build();

        super.startQueryCount();
        this.repository.save(givenMetadata);
        super.checkQueryCount(1);
    }

    private static void checkEquals(final TableMetadataEntity expected, final TableMetadataEntity actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTableName(), actual.getTableName());
        assertEquals(expected.getPropertiesMetadata(), actual.getPropertiesMetadata());
    }
}
