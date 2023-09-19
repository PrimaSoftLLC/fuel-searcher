package by.aurorasoft.fuelsearcher.crud.mapper;

import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import by.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
import by.aurorasoft.fuelsearcher.crud.model.entity.PropertyMetadataEntity;
import by.aurorasoft.fuelsearcher.crud.model.entity.TableMetadataEntity;
import by.nhorushko.crudgeneric.v2.mapper.AbsMapperEntityDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

//TODO: refactor and refactor tests
@Component
public final class TableMetadataMapper extends AbsMapperEntityDto<TableMetadataEntity, TableMetadata> {

    public TableMetadataMapper(final ModelMapper modelMapper) {
        super(modelMapper, TableMetadataEntity.class, TableMetadata.class);
    }

    @Override
    protected TableMetadata create(final TableMetadataEntity entity) {
        return new TableMetadata(
                entity.getId(),
                entity.getTableName(),
                this.findDtoPropertiesMetadata(entity)
        );
    }

    private List<PropertyMetadata> findDtoPropertiesMetadata(final TableMetadataEntity entity) {
        final List<PropertyMetadataEntity> sourceEntities = entity.getPropertiesMetadata();
        return super.mapAll(sourceEntities, PropertyMetadata.class);
    }
}
