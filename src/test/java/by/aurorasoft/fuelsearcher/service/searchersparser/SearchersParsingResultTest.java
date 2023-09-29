//package by.aurorasoft.fuelsearcher.service.searchersparser;
//
//import by.aurorasoft.fuelsearcher.crud.model.dto.TableMetadata;
//import org.junit.Test;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.assertSame;
//import static org.junit.Assert.assertTrue;
//import static org.mockito.Mockito.mock;
//
//public final class SearchersParsingResultTest {
//
//    @Test
//    public void tablesMetadataShouldBeTakenAway() {
//        final List<TableMetadata> givenTablesMetadata = List.of(
//                mock(TableMetadata.class),
//                mock(TableMetadata.class),
//                mock(TableMetadata.class)
//        );
//        final SearchersParsingResult givenParsingResult = SearchersParsingResult.builder()
//                .tablesMetadata(givenTablesMetadata)
//                .build();
//
//        Optional<List<TableMetadata>> optionalActual = givenParsingResult.takeAwayTablesMetadata();
//        assertTrue(optionalActual.isPresent());
//        final List<TableMetadata> actual = optionalActual.get();
//        assertSame(givenTablesMetadata, actual);
//
//        optionalActual = givenParsingResult.takeAwayTablesMetadata();
//        assertTrue(optionalActual.isEmpty());
//    }
//
//    @Test
//    public void tablesMetadataShouldNotBeTakenAway() {
//        final SearchersParsingResult givenParsingResult = SearchersParsingResult.builder().build();
//
//        final Optional<List<TableMetadata>> optionalActual = givenParsingResult.takeAwayTablesMetadata();
//        assertTrue(optionalActual.isEmpty());
//    }
//}
