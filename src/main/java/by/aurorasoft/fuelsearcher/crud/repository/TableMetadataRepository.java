package by.aurorasoft.fuelsearcher.crud.repository;

import by.aurorasoft.fuelsearcher.crud.model.entity.TableMetadataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TableMetadataRepository extends JpaRepository<TableMetadataEntity, Long> {

    @Query("SELECT e FROM TableMetadataEntity e JOIN FETCH e.propertiesMetadata WHERE e.tableName = :tableName")
    Optional<TableMetadataEntity> findByTableName(final String tableName);
}
