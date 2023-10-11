//package com.aurorasoft.fuelsearcher.configuration;
//
//import com.aurorasoft.fuelsearcher.model.FuelDocument;
//import com.aurorasoft.fuelsearcher.model.metadata.TableMetadata;
//import com.aurorasoft.fuelsearcher.service.factory.derivingsearcher.SpecificationValidatorsFactory;
//import com.aurorasoft.fuelsearcher.service.factory.document.FuelDocumentFactory;
//import com.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
//import com.aurorasoft.fuelsearcher.service.searchersparser.FuelSearchersParser;
//import com.aurorasoft.fuelsearcher.service.validator.SpecificationValidator;
//import org.junit.Test;
//
//import java.util.List;
//
//import static org.junit.Assert.assertSame;
//import static org.mockito.ArgumentMatchers.same;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//public final class FuelConfigurationTest {
//    private final FuelConfiguration configuration = new FuelConfiguration();
//
//    @Test
//    public void fuelDocumentShouldBeCreated() {
//        final FuelDocumentFactory givenFactory = mock(FuelDocumentFactory.class);
//
//        final FuelDocument givenFuelDocument = mock(FuelDocument.class);
//        final String givenFilePath = "file-path";
//        when(givenFactory.create(same(givenFilePath))).thenReturn(givenFuelDocument);
//
//        final FuelDocument actual = this.configuration.fuelDocument(givenFactory, givenFilePath);
//        assertSame(givenFuelDocument, actual);
//    }
//
//    @Test
//    public void fuelSearchersShouldBeCreated() {
//        final FuelSearchersParser givenParser = mock(FuelSearchersParser.class);
//
//        final List<FuelSearcher> givenSearchers = List.of(
//                mock(FuelSearcher.class),
//                mock(FuelSearcher.class)
//        );
//        final String givenFilePath = "file-path";
//        when(givenParser.parse(same(givenFilePath))).thenReturn(givenSearchers);
//
//        final List<FuelSearcher> actual = this.configuration.fuelSearchers(givenParser, givenFilePath);
//        assertSame(givenSearchers, actual);
//    }
//
//    @Test
//    public void specificationValidatorsShouldBeCreated() {
//        final SpecificationValidatorsFactory givenFactory = mock(SpecificationValidatorsFactory.class);
//
//        final List<SpecificationValidator> givenValidators = List.of(
//                mock(SpecificationValidator.class),
//                mock(SpecificationValidator.class)
//        );
//        when(givenFactory.create()).thenReturn(givenValidators);
//
//        final List<SpecificationValidator> actual = this.configuration.specificationValidators(givenFactory);
//        assertSame(givenValidators, actual);
//    }
//
//    @Test
//    public void tablesMetadataShouldBeLoaded() {
//        final TablesMetadataLoader givenLoader = mock(TablesMetadataLoader.class);
//
//        final List<TableMetadata> givenTablesMetadata = List.of(
//                mock(TableMetadata.class),
//                mock(TableMetadata.class)
//        );
//        when(givenLoader.load()).thenReturn(givenTablesMetadata);
//
//        final List<TableMetadata> actual = this.configuration.tablesMetadata(givenLoader);
//        assertSame(givenTablesMetadata, actual);
//    }
//}
