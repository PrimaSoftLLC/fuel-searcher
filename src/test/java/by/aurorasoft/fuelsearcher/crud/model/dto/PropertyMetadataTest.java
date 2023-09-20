package by.aurorasoft.fuelsearcher.crud.model.dto;

import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public final class PropertyMetadataTest extends AbstractContextTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void metadataShouldBeConvertedToJson()
            throws Exception {
        final PropertyMetadata givenMetadata = PropertyMetadata.builder()
                .id(255L)
                .propertyName("property")
                .allowableValues(new String[]{"value-1", "value-2", "value-3"})
                .tableMetadataId(256L)
                .build();

        final String actual = this.objectMapper.writeValueAsString(givenMetadata);
        final String expected = "{\"propertyName\":\"property\","
                + "\"allowableValues\":[\"value-1\",\"value-2\",\"value-3\"]}";
        assertEquals(expected, actual);
    }

    @Test
    public void jsonShouldBeConvertedToMetadata()
            throws Exception {
        final String givenJson = "{\"propertyName\":\"property\","
                + "\"allowableValues\":[\"value-1\",\"value-2\",\"value-3\"]}";

        final PropertyMetadata actual = this.objectMapper.readValue(givenJson, PropertyMetadata.class);
        final PropertyMetadata expected = PropertyMetadata.builder()
                .propertyName("property")
                .allowableValues(new String[]{"value-1", "value-2", "value-3"})
                .build();
        assertEquals(expected, actual);
    }
}
