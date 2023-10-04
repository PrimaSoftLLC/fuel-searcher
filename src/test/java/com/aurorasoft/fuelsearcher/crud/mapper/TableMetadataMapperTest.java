package com.aurorasoft.fuelsearcher.crud.mapper;

import com.aurorasoft.fuelsearcher.base.AbstractContextTest;
import com.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import com.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
import com.aurorasoft.fuelsearcher.crud.model.entity.PropertyMetadataEntity;
import com.aurorasoft.fuelsearcher.crud.model.entity.TableMetadataEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.util.stream.IntStream.range;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public final class TableMetadataMapperTest extends AbstractContextTest {

    @Autowired
    private TableMetadataMapper mapper;

    @Test
    public void dtoShouldBeMappedToEntity() {
        final Long givenId = 255L;
        final String givenTableName = "table-name";

        final Long firstGivenPropertyMetadataId = 256L;
        final Long secondGivenPropertyMetadataId = 257L;
        final List<PropertyMetadata> givenPropertiesMetadata = List.of(
                PropertyMetadata.builder()
                        .id(firstGivenPropertyMetadataId)
                        .tableMetadataId(givenId)
                        .build(),
                PropertyMetadata.builder()
                        .id(secondGivenPropertyMetadataId)
                        .tableMetadataId(givenId)
                        .build()
        );

        final TableMetadata givenDto = new TableMetadata(
                givenId,
                givenTableName,
                givenPropertiesMetadata
        );

        final TableMetadataEntity actual = this.mapper.toEntity(givenDto);

        final PropertyMetadataEntity firstExpectedPropertyMetadata = PropertyMetadataEntity.builder()
                .id(firstGivenPropertyMetadataId)
                .tableMetadata(super.entityManager.getReference(TableMetadataEntity.class, givenId))
                .build();
        final PropertyMetadataEntity secondExpectedPropertyMetadata = PropertyMetadataEntity.builder()
                .id(secondGivenPropertyMetadataId)
                .tableMetadata(super.entityManager.getReference(TableMetadataEntity.class, givenId))
                .build();
        final List<PropertyMetadataEntity> expectedPropertiesMetadata = List.of(
                firstExpectedPropertyMetadata,
                secondExpectedPropertyMetadata
        );

        final TableMetadataEntity expected = TableMetadataEntity.builder()
                .id(givenId)
                .tableName(givenTableName)
                .propertiesMetadata(expectedPropertiesMetadata)
                .build();
        checkEquals(expected, actual);
    }

    @Test
    public void entityShouldBeMappedToDto() {
        final Long givenId = 255L;
        final String givenTableName = "table-name";

        final Long firstGivenPropertyMetadataId = 256L;
        final String firstGivenPropertyName = "first-property";
        final String[] firstGivenAllowableValues = {"first-value", "second-value"};
        final PropertyMetadataEntity firstGivenPropertyMetadata = PropertyMetadataEntity.builder()
                .id(firstGivenPropertyMetadataId)
                .propertyName(firstGivenPropertyName)
                .allowableValues(firstGivenAllowableValues)
                .tableMetadata(super.entityManager.getReference(TableMetadataEntity.class, givenId))
                .build();

        final Long secondGivenPropertyMetadataId = 257L;
        final String secondGivenPropertyName = "second-property";
        final String[] secondGivenAllowableValues = {"third-value", "fourth-value"};
        final PropertyMetadataEntity secondGivenPropertyMetadata = PropertyMetadataEntity.builder()
                .id(secondGivenPropertyMetadataId)
                .propertyName(secondGivenPropertyName)
                .allowableValues(secondGivenAllowableValues)
                .tableMetadata(super.entityManager.getReference(TableMetadataEntity.class, givenId))
                .build();

        final List<PropertyMetadataEntity> givenPropertiesMetadata = List.of(
                firstGivenPropertyMetadata,
                secondGivenPropertyMetadata
        );

        final TableMetadataEntity givenEntity = TableMetadataEntity.builder()
                .id(givenId)
                .tableName(givenTableName)
                .propertiesMetadata(givenPropertiesMetadata)
                .build();

        final TableMetadata actual = this.mapper.toDto(givenEntity);

        final PropertyMetadata firstExpectedPropertyMetadata = PropertyMetadata.builder()
                .id(firstGivenPropertyMetadataId)
                .propertyName(firstGivenPropertyName)
                .allowableValues(firstGivenAllowableValues)
                .tableMetadataId(givenId)
                .build();
        final PropertyMetadata secondExpectedPropertyMetadata = PropertyMetadata.builder()
                .id(secondGivenPropertyMetadataId)
                .propertyName(secondGivenPropertyName)
                .allowableValues(secondGivenAllowableValues)
                .tableMetadataId(givenId)
                .build();
        final List<PropertyMetadata> expectedPropertiesMetadata = List.of(
                firstExpectedPropertyMetadata,
                secondExpectedPropertyMetadata
        );

        final TableMetadata expected = new TableMetadata(
                givenId,
                givenTableName,
                expectedPropertiesMetadata
        );
        assertEquals(expected, actual);
    }

    private static void checkEquals(final TableMetadataEntity expected, final TableMetadataEntity actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTableName(), actual.getTableName());
        checkEquals(expected.getPropertiesMetadata(), actual.getPropertiesMetadata());
    }

    private static void checkEquals(final List<PropertyMetadataEntity> expected,
                                    final List<PropertyMetadataEntity> actual) {
        assertEquals(expected.size(), actual.size());
        range(0, expected.size())
                .forEach(
                        i -> checkEquals(
                                expected.get(i),
                                actual.get(i)
                        )
                );
    }

    private static void checkEquals(final PropertyMetadataEntity expected, final PropertyMetadataEntity actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getPropertyName(), actual.getPropertyName());
        assertArrayEquals(expected.getAllowableValues(), actual.getAllowableValues());
        assertEquals(expected.getTableMetadata(), actual.getTableMetadata());
    }
}
