package by.aurorasoft.fuelsearcher.service.validator;

import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import by.aurorasoft.fuelsearcher.model.specification.propertyextractor.TableNameExtractor;
import by.aurorasoft.fuelsearcher.service.dictionary.SpecificationValidatorDictionary;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static by.aurorasoft.fuelsearcher.service.validator.SpecificationValidatingResult.createNotValidValidatingResult;
import static java.util.Optional.empty;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public final class SpecificationValidatingManagerTest {

    @Mock
    private SpecificationValidatorDictionary mockedValidatorDictionary;

    @Mock
    private TableNameExtractor mockedTableNameExtractor;

    private SpecificationValidatingManager validatingManager;

    @Before
    public void initializeValidatingManager() {
        this.validatingManager = new SpecificationValidatingManager(
                this.mockedValidatorDictionary, this.mockedTableNameExtractor
        );
    }

    @Test
    public void specificationShouldBeValidated() {
        final FuelSpecification givenSpecification = mock(FuelSpecification.class);

        final String givenTableName = "table-name";
        when(this.mockedTableNameExtractor.find(same(givenSpecification)))
                .thenReturn(Optional.of(givenTableName));

        final SpecificationValidator givenValidator = mock(SpecificationValidator.class);
        when(this.mockedValidatorDictionary.find(same(givenTableName))).thenReturn(Optional.of(givenValidator));

        final SpecificationValidatingResult givenValidatingResult = mock(SpecificationValidatingResult.class);
        when(givenValidator.validate(same(givenSpecification))).thenReturn(givenValidatingResult);

        final SpecificationValidatingResult actual = this.validatingManager.validate(givenSpecification);
        assertSame(givenValidatingResult, actual);
    }

    @Test
    public void specificationShouldBeValidatedAsNotValidBecauseOfSpecificationDoesNotContainTableName() {
        try (final MockedStatic<SpecificationValidatingResult> mockedStatic = mockStatic(SpecificationValidatingResult.class)) {
            final FuelSpecification givenSpecification = mock(FuelSpecification.class);

            when(this.mockedTableNameExtractor.find(same(givenSpecification))).thenReturn(empty());

            final SpecificationValidatingResult givenValidatingResult = mock(SpecificationValidatingResult.class);
            mockedStatic.when(() -> createNotValidValidatingResult(same(this.mockedTableNameExtractor)))
                    .thenReturn(givenValidatingResult);

            final SpecificationValidatingResult actual = this.validatingManager.validate(givenSpecification);
            assertSame(givenValidatingResult, actual);

            verify(this.mockedValidatorDictionary, times(0)).find(anyString());
        }
    }

    @Test
    public void specificationShouldBeValidatedAsNotValidBecauseOfSpecificationContainNotExistingTableName() {
        try (final MockedStatic<SpecificationValidatingResult> mockedStatic = mockStatic(SpecificationValidatingResult.class)) {
            final FuelSpecification givenSpecification = mock(FuelSpecification.class);

            final String givenTableName = "table-name";
            when(this.mockedTableNameExtractor.find(same(givenSpecification)))
                    .thenReturn(Optional.of(givenTableName));

            when(this.mockedValidatorDictionary.find(same(givenTableName))).thenReturn(empty());

            final SpecificationValidatingResult givenValidatingResult = mock(SpecificationValidatingResult.class);
            mockedStatic.when(() -> createNotValidValidatingResult(same(this.mockedTableNameExtractor)))
                    .thenReturn(givenValidatingResult);

            final SpecificationValidatingResult actual = this.validatingManager.validate(givenSpecification);
            assertSame(givenValidatingResult, actual);
        }
    }

}
