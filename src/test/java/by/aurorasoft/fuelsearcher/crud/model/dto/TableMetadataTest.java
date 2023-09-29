package by.aurorasoft.fuelsearcher.crud.model.dto;

import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public final class TableMetadataTest extends AbstractContextTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void metadataShouldBeConvertedToJson()
            throws Exception {
        final Long givenId = 255L;
        final String givenTableName = "table-name";
        final List<PropertyMetadata> givenPropertiesMetadata = List.of(
                PropertyMetadata.builder()
                        .id(256L)
                        .propertyName("first-property")
                        .allowableValues(new String[]{"first-value", "second-value"})
                        .tableMetadataId(258L)
                        .build(),
                PropertyMetadata.builder()
                        .id(257L)
                        .propertyName("second-property")
                        .allowableValues(new String[]{"third-value", "fourth-value"})
                        .tableMetadataId(259L)
                        .build()
        );
        final TableMetadata givenMetadata = new TableMetadata(givenId, givenTableName, givenPropertiesMetadata);

        final String actual = this.objectMapper.writeValueAsString(givenMetadata);
        final String expected = "{\"tableName\":\"table-name\",\"propertiesMetadata\":"
                + "[{\"propertyName\":\"first-property\",\"allowableValues\":[\"first-value\",\"second-value\"]},"
                + "{\"propertyName\":\"second-property\",\"allowableValues\":[\"third-value\",\"fourth-value\"]}]}";
        assertEquals(expected, actual);
    }

    @Test
    public void jsonShouldBeConvertedToMetadata()
            throws Exception {
        final String givenJson = "{\"tableName\":\"table-name\",\"propertiesMetadata\":"
                + "[{\"propertyName\":\"first-property\",\"allowableValues\":[\"first-value\",\"second-value\"]},"
                + "{\"propertyName\":\"second-property\",\"allowableValues\":[\"third-value\",\"fourth-value\"]}]}";

        final TableMetadata actual = this.objectMapper.readValue(givenJson, TableMetadata.class);
        final TableMetadata expected = new TableMetadata(
                null,
                "table-name",
                List.of(
                        PropertyMetadata.builder()
                                .propertyName("first-property")
                                .allowableValues(new String[]{"first-value", "second-value"})
                                .build(),
                        PropertyMetadata.builder()
                                .propertyName("second-property")
                                .allowableValues(new String[]{"third-value", "fourth-value"})
                                .build()
                )
        );
        assertEquals(expected, actual);
    }
}
