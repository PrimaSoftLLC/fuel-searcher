//package by.aurorasoft.fuelsearcher.crud.model.dto;
//
//import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
//import by.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata.TableMetadataBuilder;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Stream;
//
//import static by.aurorasoft.fuelsearcher.testutil.CollectionUtil.isImmutableList;
//import static by.aurorasoft.fuelsearcher.testutil.ReflectionUtil.findProperty;
//import static by.aurorasoft.fuelsearcher.testutil.ReflectionUtil.setProperty;
//import static org.junit.Assert.*;
//
//public final class TableMetadataTest extends AbstractContextTest {
//    private static final String FIELD_NAME_TABLE_NAME = "tableName";
//    private static final String FIELD_NAME_PROPERTIES_METADATA = "propertiesMetadata";
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    public void metadataShouldBeConvertedToJson()
//            throws Exception {
//        final Long givenId = 255L;
//        final String givenTableName = "table-name";
//        final List<PropertyMetadata> givenPropertiesMetadata = List.of(
//                PropertyMetadata.builder()
//                        .id(256L)
//                        .propertyName("first-property")
//                        .allowableValues(new String[]{"first-value", "second-value"})
//                        .tableMetadataId(258L)
//                        .build(),
//                PropertyMetadata.builder()
//                        .id(257L)
//                        .propertyName("second-property")
//                        .allowableValues(new String[]{"third-value", "fourth-value"})
//                        .tableMetadataId(259L)
//                        .build()
//        );
//        final TableMetadata givenMetadata = new TableMetadata(givenId, givenTableName, givenPropertiesMetadata);
//
//        final String actual = this.objectMapper.writeValueAsString(givenMetadata);
//        final String expected = "{\"tableName\":\"table-name\",\"propertiesMetadata\":"
//                + "[{\"propertyName\":\"first-property\",\"allowableValues\":[\"first-value\",\"second-value\"]},"
//                + "{\"propertyName\":\"second-property\",\"allowableValues\":[\"third-value\",\"fourth-value\"]}]}";
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void jsonShouldBeConvertedToMetadata()
//            throws Exception {
//        final String givenJson = "{\"tableName\":\"table-name\",\"propertiesMetadata\":"
//                + "[{\"propertyName\":\"first-property\",\"allowableValues\":[\"first-value\",\"second-value\"]},"
//                + "{\"propertyName\":\"second-property\",\"allowableValues\":[\"third-value\",\"fourth-value\"]}]}";
//
//        final TableMetadata actual = this.objectMapper.readValue(givenJson, TableMetadata.class);
//        final TableMetadata expected = new TableMetadata(
//                null,
//                "table-name",
//                List.of(
//                        PropertyMetadata.builder()
//                                .propertyName("first-property")
//                                .allowableValues(new String[]{"first-value", "second-value"})
//                                .build(),
//                        PropertyMetadata.builder()
//                                .propertyName("second-property")
//                                .allowableValues(new String[]{"third-value", "fourth-value"})
//                                .build()
//                )
//        );
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void builderShouldBeCreated() {
//        final TableMetadataBuilder actual = TableMetadata.builder();
//        assertNotNull(actual);
//    }
//
//    @Test
//    public void tableNameShouldBeAccumulatedByBuilder()
//            throws Exception {
//        final TableMetadataBuilder givenBuilder = TableMetadata.builder();
//
//        final String givenTableName = "table-name";
//        givenBuilder.tableName(givenTableName);
//
//        final String actual = findTableName(givenBuilder);
//        assertSame(givenTableName, actual);
//    }
//
//    @Test
//    public void propertiesMetadataShouldBeAccumulatedByBuilder()
//            throws Exception {
//        final TableMetadataBuilder givenBuilder = TableMetadata.builder();
//
//        final PropertyMetadata firstGivenPropertyMetadata = createPropertyMetadata(255L);
//        givenBuilder.propertyMetadata(firstGivenPropertyMetadata);
//
//        final PropertyMetadata secondGivenPropertyMetadata = createPropertyMetadata(256L);
//        givenBuilder.propertyMetadata(secondGivenPropertyMetadata);
//
//        final List<PropertyMetadata> actualPropertiesMetadata = findPropertiesMetadata(givenBuilder);
//        final List<PropertyMetadata> expectedPropertiesMetadata = List.of(
//                firstGivenPropertyMetadata, secondGivenPropertyMetadata
//        );
//        assertEquals(expectedPropertiesMetadata, actualPropertiesMetadata);
//    }
//
//    @Test
//    public void propertiesShouldBeFound()
//            throws Exception {
//        final TableMetadataBuilder givenBuilder = TableMetadata.builder();
//
//        final String givenTableName = "table-name";
//        setTableName(givenBuilder, givenTableName);
//
//        final List<PropertyMetadata> givenPropertiesMetadata = List.of(
//                createPropertyMetadata(255L),
//                createPropertyMetadata(256L)
//        );
//        injectPropertiesMetadata(givenBuilder, givenPropertiesMetadata);
//
//        final Stream<Object> actual = givenBuilder.findProperties();
//        final List<Object> actualAsList = actual.toList();
//        final List<Object> expectedAsList = List.of(givenTableName, givenPropertiesMetadata);
//        assertEquals(expectedAsList, actualAsList);
//    }
//
//    @Test
//    public void metadataShouldBeBuiltAfterStateValidation()
//            throws Exception {
//        final TableMetadataBuilder givenBuilder = TableMetadata.builder();
//
//        final String givenTableName = "table-name";
//        setTableName(givenBuilder, givenTableName);
//
//        final List<PropertyMetadata> givenPropertiesMetadata = new ArrayList<>() {
//            {
//                super.add(createPropertyMetadata(255L));
//                super.add(createPropertyMetadata(256L));
//            }
//        };
//        injectPropertiesMetadata(givenBuilder, givenPropertiesMetadata);
//
//        final TableMetadata actual = givenBuilder.buildAfterStateValidation();
//        final TableMetadata expected = new TableMetadata(null, givenTableName, givenPropertiesMetadata);
//        assertEquals(expected, actual);
//
//        assertTrue(isImmutableList(actual.getPropertiesMetadata()));
//    }
//
//    private static String findTableName(final TableMetadataBuilder builder)
//            throws Exception {
//        return findProperty(
//                builder,
//                TableMetadataBuilder.class,
//                FIELD_NAME_TABLE_NAME,
//                String.class
//        );
//    }
//
//    @SuppressWarnings("unchecked")
//    private static List<PropertyMetadata> findPropertiesMetadata(final TableMetadataBuilder builder)
//            throws Exception {
//        return findProperty(
//                builder,
//                TableMetadataBuilder.class,
//                FIELD_NAME_PROPERTIES_METADATA,
//                List.class
//        );
//    }
//
//
//    @SuppressWarnings("SameParameterValue")
//    private static void setTableName(final TableMetadataBuilder builder, final String tableName)
//            throws Exception {
//        setProperty(
//                builder,
//                tableName,
//                TableMetadataBuilder.class,
//                FIELD_NAME_TABLE_NAME
//        );
//    }
//
//    private static void injectPropertiesMetadata(final TableMetadataBuilder builder,
//                                                 final List<PropertyMetadata> propertiesMetadata)
//            throws Exception {
//        setProperty(
//                builder,
//                propertiesMetadata,
//                TableMetadataBuilder.class,
//                FIELD_NAME_PROPERTIES_METADATA
//        );
//    }
//
//    private static PropertyMetadata createPropertyMetadata(final Long id) {
//        return PropertyMetadata.builder()
//                .id(id)
//                .build();
//    }
//}
