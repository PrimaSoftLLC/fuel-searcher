package com.aurorasoft.fuelsearcher.controller;

import com.aurorasoft.fuelsearcher.model.metadata.PropertyMetadata;
import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
import com.aurorasoft.fuelsearcher.service.dictionary.TableMetadataDictionary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;
import java.util.Optional;

import static com.aurorasoft.fuelsearcher.testutil.ControllerRequestUtil.doRequestAndGetContentAsString;
import static java.util.Optional.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(TableMetadataController.class)
public final class TableMetadataControllerTest {
    private static final String CONTROLLER_URL = "/tableMetadata";
    private static final String REQUEST_PARAM_NAME_TABLE_NAME = "tableName";

    @MockBean
    private TableMetadataDictionary mockedMetadataDictionary;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void metadataShouldBeFoundByTableName()
            throws Exception {
        final String givenTableName = "table-name";
        final List<PropertyMetadata> givenPropertiesMetadata = List.of(
                PropertyMetadata.builder()
                        .propertyName("first-property")
                        .allowableValues(new String[]{"first-value", "second-value"})
                        .build(),
                PropertyMetadata.builder()
                        .propertyName("second-property")
                        .allowableValues(new String[]{"third-value", "fourth-value"})
                        .build()
        );
        final TableMetadata givenMetadata = TableMetadata.builder()
                .tableName(givenTableName)
                .propertiesMetadata(givenPropertiesMetadata)
                .build();
        when(this.mockedMetadataDictionary.find(eq(givenTableName))).thenReturn(Optional.of(givenMetadata));

        final RequestBuilder givenRequestBuilder = createRequestBuilderToFindMetadataByTableName(givenTableName);

        final String actual = doRequestAndGetContentAsString(this.mockMvc, givenRequestBuilder, OK);
        final String expected = "{\"tableName\":\"table-name\","
                + "\"propertiesMetadata\":[{\"propertyName\":\"first-property\","
                + "\"allowableValues\":[\"first-value\",\"second-value\"]},"
                + "{\"propertyName\":\"second-property\","
                + "\"allowableValues\":[\"third-value\",\"fourth-value\"]}]}";
        assertEquals(expected, actual);
    }

    @Test
    public void metadataShouldNotBeFoundByTableName()
            throws Exception {
        final String givenTableName = "table-name";

        when(this.mockedMetadataDictionary.find(eq(givenTableName))).thenReturn(empty());

        final RequestBuilder givenRequestBuilder = createRequestBuilderToFindMetadataByTableName(givenTableName);

        final String actual = doRequestAndGetContentAsString(this.mockMvc, givenRequestBuilder, NOT_FOUND);
        final String expectedRegex = "\\{\"httpStatus\":\"NOT_FOUND\","
                + "\"message\":\"There is no metadata for table 'table-name'\","
                + "\"dateTime\":\"\\d{4}-\\d{2}-\\d{2} \\d{2}-\\d{2}-\\d{2}\"}";
        assertTrue(actual.matches(expectedRegex));
    }

    @SuppressWarnings("SameParameterValue")
    private static MockHttpServletRequestBuilder createRequestBuilderToFindMetadataByTableName(final String tableName) {
        final MockHttpServletRequestBuilder builder = get(CONTROLLER_URL);
        builder.param(REQUEST_PARAM_NAME_TABLE_NAME, tableName);
        return builder;
    }
}
