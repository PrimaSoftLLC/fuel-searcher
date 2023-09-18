package by.aurorasoft.fuelsearcher.crud.service;

import by.aurorasoft.fuelsearcher.crud.mapper.PropertyMetadataMapper;
import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
import by.aurorasoft.fuelsearcher.crud.model.entity.PropertyMetadataEntity;
import by.aurorasoft.fuelsearcher.crud.repository.PropertyMetadataRepository;
import by.nhorushko.crudgeneric.v2.service.AbsServiceCRUD;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PropertyMetadataService
        extends AbsServiceCRUD<Long, PropertyMetadataEntity, PropertyMetadata, PropertyMetadataRepository> {

    public PropertyMetadataService(final PropertyMetadataMapper mapper, final PropertyMetadataRepository repository) {
        super(mapper, repository);
    }
}
