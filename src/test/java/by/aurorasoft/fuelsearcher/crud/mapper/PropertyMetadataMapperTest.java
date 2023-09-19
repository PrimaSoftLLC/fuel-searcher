//package by.aurorasoft.fuelsearcher.crud.mapper;
//
//import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
//import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
//import by.aurorasoft.fuelsearcher.crud.model.entity.PropertyMetadataEntity;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import static org.junit.Assert.assertArrayEquals;
//import static org.junit.Assert.assertEquals;
//
//public final class PropertyMetadataMapperTest extends AbstractContextTest {
//
//    @Autowired
//    private PropertyMetadataMapper mapper;
//
//    @Test
//    public void dtoShouldBeMappedToEntity() {
//        final PropertyMetadata givenDto = PropertyMetadata.builder()
//                .id(255L)
//                .propertyName("property-name")
//                .allowableValues(new String[]{"first-value", "second-value", "third-value"})
//                .build();
//
//        final PropertyMetadataEntity actual = this.mapper.toEntity(givenDto);
//        final PropertyMetadataEntity expected = PropertyMetadataEntity.builder()
//                .id(255L)
//                .propertyName("property-name")
//                .allowableValues(new String[]{"first-value", "second-value", "third-value"})
//                .build();
//        checkEquals(expected, actual);
//    }
//
//    @Test
//    public void entityShouldBeMappedToDto() {
//        final PropertyMetadataEntity givenEntity = PropertyMetadataEntity.builder()
//                .id(255L)
//                .propertyName("property-name")
//                .allowableValues(new String[]{"first-value", "second-value", "third-value"})
//                .build();
//
//        final PropertyMetadata actual = this.mapper.toDto(givenEntity);
//        final PropertyMetadata expected = PropertyMetadata.builder()
//                .id(255L)
//                .propertyName("property-name")
//                .allowableValues(new String[]{"first-value", "second-value", "third-value"})
//                .build();
//        assertEquals(expected, actual);
//    }
//
//    private static void checkEquals(final PropertyMetadataEntity expected, final PropertyMetadataEntity actual) {
//        assertEquals(expected.getId(), actual.getId());
//        assertEquals(expected.getPropertyName(), actual.getPropertyName());
//        assertArrayEquals(expected.getAllowableValues(), actual.getAllowableValues());
//        assertEquals(expected.getTableMetadata(), actual.getTableMetadata());
//    }
//}
