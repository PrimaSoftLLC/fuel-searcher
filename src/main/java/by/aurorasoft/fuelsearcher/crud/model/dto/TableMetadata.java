package by.aurorasoft.fuelsearcher.crud.model.dto;

import by.nhorushko.crudgeneric.v2.domain.AbstractDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

//TODO: refactor tests
@Value
@AllArgsConstructor
public class TableMetadata implements AbstractDto<Long> {

    @JsonIgnore
    Long id;

    String tableName;

    List<PropertyMetadata> propertiesMetadata;
}
