package by.aurorasoft.fuelsearcher.crud.model.dto;

import by.aurorasoft.fuelsearcher.service.builder.BuilderRequiringAllProperties;
import by.nhorushko.crudgeneric.v2.domain.AbstractDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static lombok.AccessLevel.PRIVATE;

//TODO: tests
@Value
public class TableMetadata implements AbstractDto<Long> {

    @JsonIgnore
    Long id;

    String tableName;

    List<PropertyMetadata> propertiesMetadata;

    public static TableMetadataBuilder builder() {
        return new TableMetadataBuilder();
    }

    @NoArgsConstructor(access = PRIVATE)
    public static final class TableMetadataBuilder extends BuilderRequiringAllProperties<TableMetadata> {
        private String tableName;
        private List<PropertyMetadata> propertiesMetadata;

        public void tableName(final String tableName) {
            this.tableName = tableName;
        }

        public void propertyMetadata(final PropertyMetadata metadata) {
            this.initializePropertiesMetadataIfNecessary();
            this.propertiesMetadata.add(metadata);
        }

        @Override
        protected Stream<Object> findProperties() {
            return Stream.of(this.tableName, this.propertiesMetadata);
        }

        @Override
        protected TableMetadata buildAfterStateValidation() {
            return new TableMetadata(null, this.tableName, this.propertiesMetadata);
        }

        private void initializePropertiesMetadataIfNecessary() {
            if (this.propertiesMetadata == null) {
                this.propertiesMetadata = new ArrayList<>();
            }
        }
    }
}
