package by.aurorasoft.fuelsearcher.crud.mapper;

import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import by.aurorasoft.fuelsearcher.crud.model.entity.PropertyMetadataEntity;
import by.nhorushko.crudgeneric.v2.mapper.AbsMapperEntityDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

//TODO: refactor and refactor tests
@Component
public final class PropertyMetadataMapper
        extends AbsMapperEntityDto<PropertyMetadataEntity, PropertyMetadata> {
//    private static final Long NOT_DEFINED_
//
//    private final EntityManager entityManager;

    public PropertyMetadataMapper(final ModelMapper modelMapper, final EntityManager entityManager) {
        super(modelMapper, PropertyMetadataEntity.class, PropertyMetadata.class);
//        this.entityManager = entityManager;
    }

    @Override
    protected PropertyMetadata create(final PropertyMetadataEntity entity) {
//        return new PropertyMetadata(
//                entity.getId(),
//                entity.getPropertyName(),
//                entity.getAllowableValues()
//        );
        return null;
    }
}
