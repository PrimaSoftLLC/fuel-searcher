package by.aurorasoft.fuelsearcher.crud.mapper;

import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import by.aurorasoft.fuelsearcher.crud.model.entity.PropertyMetadataEntity;
import by.aurorasoft.fuelsearcher.crud.model.entity.TableMetadataEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;

import static java.lang.Long.MIN_VALUE;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public final class PropertyMetadataMapperTest extends AbstractContextTest {

    @Autowired
    private PropertyMetadataMapper mapper;

    @Test
    public void dtoShouldBeMappedToEntity() {
        final Long givenId = 255L;
        final String givenPropertyName = "property-name";
        final String[] givenAllowableValues = new String[]{"first-value", "second-value", "third-value"};
        final Long givenTableMetadataId = 256L;
        final PropertyMetadata givenDto = PropertyMetadata.builder()
                .id(givenId)
                .propertyName(givenPropertyName)
                .allowableValues(givenAllowableValues)
                .tableMetadataId(givenTableMetadataId)
                .build();

        final PropertyMetadataEntity actual = this.mapper.toEntity(givenDto);
        final PropertyMetadataEntity expected = PropertyMetadataEntity.builder()
                .id(givenId)
                .propertyName(givenPropertyName)
                .allowableValues(givenAllowableValues)
                .tableMetadata(super.entityManager.getReference(TableMetadataEntity.class, givenTableMetadataId))
                .build();
        checkEquals(expected, actual);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test(expected = EntityNotFoundException.class)
    public void dtoShouldNotBeMappedToEntityBecauseOfNoTableMetadataWithGivenId() {
        final Long givenId = 255L;
        final String givenPropertyName = "property-name";
        final String[] givenAllowableValues = new String[]{"first-value", "second-value", "third-value"};
        final Long givenTableMetadataId = MIN_VALUE;
        final PropertyMetadata givenDto = PropertyMetadata.builder()
                .id(givenId)
                .propertyName(givenPropertyName)
                .allowableValues(givenAllowableValues)
                .tableMetadataId(givenTableMetadataId)
                .build();

        final PropertyMetadataEntity actual = this.mapper.toEntity(givenDto);
        final TableMetadataEntity actualTableMetadata = actual.getTableMetadata();
        actualTableMetadata.getTableName();
    }

    @Test
    public void entityShouldBeMappedToDto() {
        final Long givenId = 255L;
        final String givenPropertyName = "property-name";
        final String[] givenAllowableValues = {"first-value", "second-value", "third-value"};
        final Long givenTableMetadataId = 256L;
        final PropertyMetadataEntity givenEntity = PropertyMetadataEntity.builder()
                .id(givenId)
                .propertyName(givenPropertyName)
                .allowableValues(givenAllowableValues)
                .tableMetadata(super.entityManager.getReference(TableMetadataEntity.class, givenTableMetadataId))
                .build();

        final PropertyMetadata actual = this.mapper.toDto(givenEntity);
        final PropertyMetadata expected = PropertyMetadata.builder()
                .id(givenId)
                .propertyName(givenPropertyName)
                .allowableValues(givenAllowableValues)
                .tableMetadataId(givenTableMetadataId)
                .build();
        assertEquals(expected, actual);
    }

    @Test
    public void entityShouldBeMappedToDtoWithZeroAsTableMetadataId() {
        final Long givenId = 255L;
        final String givenPropertyName = "property-name";
        final String[] givenAllowableValues = {"first-value", "second-value", "third-value"};
        final PropertyMetadataEntity givenEntity = PropertyMetadataEntity.builder()
                .id(givenId)
                .propertyName(givenPropertyName)
                .allowableValues(givenAllowableValues)
                .build();

        final PropertyMetadata actual = this.mapper.toDto(givenEntity);
        final Long expectedTableMetadataId = 0L;
        final PropertyMetadata expected = PropertyMetadata.builder()
                .id(givenId)
                .propertyName(givenPropertyName)
                .allowableValues(givenAllowableValues)
                .tableMetadataId(expectedTableMetadataId)
                .build();
        assertEquals(expected, actual);
    }

    private static void checkEquals(final PropertyMetadataEntity expected, final PropertyMetadataEntity actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getPropertyName(), actual.getPropertyName());
        assertArrayEquals(expected.getAllowableValues(), actual.getAllowableValues());
        assertEquals(expected.getTableMetadata(), actual.getTableMetadata());
    }
}
