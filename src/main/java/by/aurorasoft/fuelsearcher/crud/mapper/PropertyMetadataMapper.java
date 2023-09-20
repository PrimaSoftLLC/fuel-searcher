package by.aurorasoft.fuelsearcher.crud.mapper;

import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import by.aurorasoft.fuelsearcher.crud.model.entity.PropertyMetadataEntity;
import by.aurorasoft.fuelsearcher.crud.model.entity.TableMetadataEntity;
import by.nhorushko.crudgeneric.v2.mapper.AbsMapperEntityDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public final class PropertyMetadataMapper extends AbsMapperEntityDto<PropertyMetadataEntity, PropertyMetadata> {
    private static final Long NOT_DEFINED_TABLE_METADATA_ID = 0L;

    private final EntityManager entityManager;

    public PropertyMetadataMapper(final ModelMapper modelMapper, final EntityManager entityManager) {
        super(modelMapper, PropertyMetadataEntity.class, PropertyMetadata.class);
        this.entityManager = entityManager;
    }

    @Override
    protected void mapSpecificFields(final PropertyMetadata source, final PropertyMetadataEntity destination) {
        final TableMetadataEntity tableMetadata = this.findTableMetadataEntity(source);
        destination.setTableMetadata(tableMetadata);
    }

    @Override
    protected PropertyMetadata create(final PropertyMetadataEntity entity) {
        return new PropertyMetadata(
                entity.getId(),
                entity.getPropertyName(),
                entity.getAllowableValues(),
                findTableMetadataId(entity)
        );
    }

    private TableMetadataEntity findTableMetadataEntity(final PropertyMetadata source) {
        final Long entityId = source.getTableMetadataId();
        return this.entityManager.getReference(TableMetadataEntity.class, entityId);
    }

    private static Long findTableMetadataId(final PropertyMetadataEntity propertyMetadata) {
        final TableMetadataEntity tableMetadata = propertyMetadata.getTableMetadata();
        return tableMetadata != null ? tableMetadata.getId() : NOT_DEFINED_TABLE_METADATA_ID;
    }
}
