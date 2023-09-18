package by.aurorasoft.fuelsearcher.crud.model.dto;

import by.nhorushko.crudgeneric.v2.domain.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class PropertyMetadata implements AbstractDto<Long> {
    Long id;
    String propertyName;
    String[] allowableValues;
}
