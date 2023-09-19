package by.aurorasoft.fuelsearcher.crud.service;

import by.aurorasoft.fuelsearcher.crud.mapper.TableMetadataMapper;
import by.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
import by.aurorasoft.fuelsearcher.crud.model.entity.TableMetadataEntity;
import by.aurorasoft.fuelsearcher.crud.repository.TableMetadataRepository;
import by.nhorushko.crudgeneric.v2.service.AbsServiceCRUD;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TableMetadataService
        extends AbsServiceCRUD<Long, TableMetadataEntity, TableMetadata, TableMetadataRepository> {

    public TableMetadataService(final TableMetadataMapper mapper, final TableMetadataRepository repository) {
        super(mapper, repository);
    }

    @Transactional(readOnly = true)
    public Optional<TableMetadata> findByTableName(final String tableName) {
        final Optional<TableMetadataEntity> optionalEntity = super.repository.findByTableName(tableName);
        return optionalEntity.map(super.mapper::toDto);
    }

    //TODO: test
    public void deleteAll() {
        super.repository.deleteAll();
    }
}