package by.aurorasoft.fuelsearcher.configuration;

import by.aurorasoft.fuelsearcher.model.FuelDocument;
import by.aurorasoft.fuelsearcher.service.factory.derivingsearcher.SpecificationValidatorsFactory;
import by.aurorasoft.fuelsearcher.service.factory.document.FuelDocumentFactory;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearcher;
import by.aurorasoft.fuelsearcher.service.searchersparser.FuelSearchersParser;
import by.aurorasoft.fuelsearcher.service.validator.SpecificationValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class FuelConfigurationTest {
    private final FuelConfiguration configuration = new FuelConfiguration();

    @Test
    public void fuelDocumentShouldBeCreated() {
        final FuelDocumentFactory givenFactory = mock(FuelDocumentFactory.class);

        final FuelDocument givenFuelDocument = mock(FuelDocument.class);
        final String givenFilePath = "file-path";
        when(givenFactory.create(same(givenFilePath))).thenReturn(givenFuelDocument);

        final FuelDocument actual = this.configuration.fuelDocument(givenFactory, givenFilePath);
        assertSame(givenFuelDocument, actual);
    }

    @Test
    public void fuelSearchersShouldBeCreated() {
        final FuelSearchersParser givenParser = mock(FuelSearchersParser.class);

        final List<FuelSearcher> givenSearchers = List.of(
                mock(FuelSearcher.class),
                mock(FuelSearcher.class)
        );
        final String givenFilePath = "file-path";
        when(givenParser.parse(same(givenFilePath))).thenReturn(givenSearchers);

        final List<FuelSearcher> actual = this.configuration.fuelSearchers(givenParser, givenFilePath);
        assertSame(givenSearchers, actual);
    }

    @Test
    public void specificationValidatorsShouldBeCreated() {
        final SpecificationValidatorsFactory givenFactory = mock(SpecificationValidatorsFactory.class);

        final List<SpecificationValidator> givenValidators = List.of(
                mock(SpecificationValidator.class),
                mock(SpecificationValidator.class)
        );
        when(givenFactory.create()).thenReturn(givenValidators);

        final List<SpecificationValidator> actual = givenFactory.create();
        assertSame(givenValidators, actual);
    }
}
