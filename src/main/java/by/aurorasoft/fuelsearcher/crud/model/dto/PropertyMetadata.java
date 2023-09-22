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

    public static PropertyMetadata replaceTableMetadata(final PropertyMetadata source,
                                                        final TableMetadata tableMetadata) {
        return new PropertyMetadata(
                source.getId(),
                source.getPropertyName(),
                source.getAllowableValues(),
                tableMetadata.getId()
        );
    }
}
