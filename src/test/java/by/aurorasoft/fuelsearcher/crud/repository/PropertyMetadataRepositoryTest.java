package by.aurorasoft.fuelsearcher.crud.repository;

import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
import by.aurorasoft.fuelsearcher.crud.model.entity.PropertyMetadataEntity;
import by.aurorasoft.fuelsearcher.crud.model.entity.TableMetadataEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.Assert.*;

public final class PropertyMetadataRepositoryTest extends AbstractContextTest {

    @Autowired
    private PropertyMetadataRepository repository;

    @Test
    public void metadataShouldBeFoundById() {
        final Long givenId = 257L;

        super.startQueryCount();
        final Optional<PropertyMetadataEntity> optionalActual = this.repository.findById(givenId);
        super.checkQueryCount(1);

        assertTrue(optionalActual.isPresent());
        final PropertyMetadataEntity actual = optionalActual.get();
        final PropertyMetadataEntity expected = PropertyMetadataEntity.builder()
                .id(givenId)
                .propertyName("третье свойство")
                .allowableValues(new String[]{"значение-1", "значение-2"})
                .tableMetadata(super.entityManager.getReference(TableMetadataEntity.class, 256L))
                .build();
        checkEquals(expected, actual);
    }

    @Test
    public void metadataShouldBeSaved() {
        final PropertyMetadataEntity givenMetadata = PropertyMetadataEntity.builder()
                .propertyName("имя свойства")
                .allowableValues(new String[]{"значение-1", "значение-2"})
                .tableMetadata(super.entityManager.getReference(TableMetadataEntity.class, 255L))
                .build();

        super.startQueryCount();
        this.repository.save(givenMetadata);
        super.checkQueryCount(2);
    }

    private static void checkEquals(final PropertyMetadataEntity expected, final PropertyMetadataEntity actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getPropertyName(), actual.getPropertyName());
        assertArrayEquals(expected.getAllowableValues(), actual.getAllowableValues());
        assertEquals(expected.getTableMetadata(), actual.getTableMetadata());
    }
}
