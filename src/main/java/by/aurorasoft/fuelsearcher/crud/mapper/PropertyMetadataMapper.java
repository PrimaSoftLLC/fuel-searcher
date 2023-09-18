package by.aurorasoft.fuelsearcher.crud.mapper;

import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import by.aurorasoft.fuelsearcher.crud.model.entity.PropertyMetadataEntity;
import by.nhorushko.crudgeneric.v2.mapper.AbsMapperEntityDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public final class PropertyMetadataMapper
        extends AbsMapperEntityDto<PropertyMetadataEntity, PropertyMetadata> {

    public PropertyMetadataMapper(final ModelMapper modelMapper) {
        super(modelMapper, PropertyMetadataEntity.class, PropertyMetadata.class);
    }

    @Override
    protected PropertyMetadata create(final PropertyMetadataEntity entity) {
        return new PropertyMetadata(
                entity.getId(),
                entity.getPropertyName(),
                entity.getAllowableValues()
        );
    }
}
