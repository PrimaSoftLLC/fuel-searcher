//package by.aurorasoft.fuelsearcher.crud.mapper;
//
//import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
//import by.aurorasoft.fuelsearcher.crud.model.dto.PropertyMetadata;
//import by.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
//import by.aurorasoft.fuelsearcher.crud.model.entity.PropertyMetadataEntity;
//import by.aurorasoft.fuelsearcher.crud.model.entity.TableMetadataEntity;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//public final class TableMetadataMapperTest extends AbstractContextTest {
//
//    @Autowired
//    private TableMetadataMapper mapper;
//
//    @Test
//    public void dtoShouldBeMappedToEntity() {
//        final TableMetadata givenDto = TableMetadata.builder()
//                .id(255L)
//                .tableName("table-name")
//                .propertiesMetadata(
//                        List.of(
//                                PropertyMetadata.builder()
//                                        .id(256L)
//                                        .build(),
//                                PropertyMetadata.builder()
//                                        .id(257L)
//                                        .build()
//                        )
//                )
//                .build();
//
//        final TableMetadataEntity actual = this.mapper.toEntity(givenDto);
//        final TableMetadataEntity expected = TableMetadataEntity.builder()
//                .id(255L)
//                .tableName("table-name")
//                .propertiesMetadata(
//                        List.of(
//                                PropertyMetadataEntity.builder()
//                                        .id(256L)
//                                        .build(),
//                                PropertyMetadataEntity.builder()
//                                        .id(257L)
//                                        .build()
//                        )
//                )
//                .build();
//        checkEquals(expected, actual);
//    }
//
//    @Test
//    public void entityShouldBeMappedToDto() {
//        final TableMetadataEntity givenEntity = TableMetadataEntity.builder()
//                .id(255L)
//                .tableName("table-name")
//                .propertiesMetadata(
//                        List.of(
//                                PropertyMetadataEntity.builder()
//                                        .id(256L)
//                                        .propertyName("first-property")
//                                        .allowableValues(new String[]{"first-value", "second-value"})
//                                        .build(),
//                                PropertyMetadataEntity.builder()
//                                        .id(257L)
//                                        .propertyName("second-property")
//                                        .allowableValues(new String[]{"third-value", "fourth-value"})
//                                        .build()
//                        )
//                )
//                .build();
//
//        final TableMetadata actual = this.mapper.toDto(givenEntity);
//        final TableMetadata expected = TableMetadata.builder()
//                .id(255L)
//                .tableName("table-name")
//                .propertiesMetadata(
//                        List.of(
//                                PropertyMetadata.builder()
//                                        .id(256L)
//                                        .propertyName("first-property")
//                                        .allowableValues(new String[]{"first-value", "second-value"})
//                                        .build(),
//                                PropertyMetadata.builder()
//                                        .id(257L)
//                                        .propertyName("second-property")
//                                        .allowableValues(new String[]{"third-value", "fourth-value"})
//                                        .build()
//                        )
//                )
//                .build();
//        assertEquals(expected, actual);
//    }
//
//    private static void checkEquals(final TableMetadataEntity expected, final TableMetadataEntity actual) {
//        assertEquals(expected.getId(), actual.getId());
//        assertEquals(expected.getTableName(), actual.getTableName());
//        assertEquals(expected.getPropertiesMetadata(), actual.getPropertiesMetadata());
//    }
//}
