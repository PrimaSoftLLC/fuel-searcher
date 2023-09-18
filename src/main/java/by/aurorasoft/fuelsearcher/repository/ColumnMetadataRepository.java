package by.aurorasoft.fuelsearcher.repository;

import by.aurorasoft.fuelsearcher.model.entity.PropertyMetadataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColumnMetadataRepository extends JpaRepository<PropertyMetadataEntity, Long> {

}
