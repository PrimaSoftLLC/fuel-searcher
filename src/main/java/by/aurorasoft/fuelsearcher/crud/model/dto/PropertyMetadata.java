package by.aurorasoft.fuelsearcher.crud.model.dto;

import by.nhorushko.crudgeneric.v2.domain.AbstractDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class PropertyMetadata implements AbstractDto<Long> {

    @JsonIgnore
    Long id;

    String propertyName;

    String[] allowableValues;

    @JsonIgnore
    Long tableMetadataId;
}
