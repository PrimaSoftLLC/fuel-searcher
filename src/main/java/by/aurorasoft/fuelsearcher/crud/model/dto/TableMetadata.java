package by.aurorasoft.fuelsearcher.crud.model.dto;

import by.nhorushko.crudgeneric.v2.domain.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
@Builder
public class TableMetadata implements AbstractDto<Long> {
    Long id;
    String tableName;
    List<PropertyMetadata> propertiesMetadata;
}
