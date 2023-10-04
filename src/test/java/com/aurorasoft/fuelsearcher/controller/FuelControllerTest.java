package com.aurorasoft.fuelsearcher.controller;

import com.aurorasoft.fuelsearcher.model.Fuel;
import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import com.aurorasoft.fuelsearcher.service.searcher.FuelSearchingManager;
import com.aurorasoft.fuelsearcher.service.validator.SpecificationValidatingManager;
import com.aurorasoft.fuelsearcher.service.validator.SpecificationValidatingResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.aurorasoft.fuelsearcher.testutil.FuelControllerRequestUtil.*;
import static java.util.Arrays.asList;
import static java.util.Optional.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

@RunWith(SpringRunner.class)
@WebMvcTest(FuelController.class)
public final class FuelControllerTest {

    @MockBean
    private FuelSearchingManager mockedSearchingManager;

    @MockBean
    private SpecificationValidatingManager mockedValidatingManager;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void fuelShouldBeFound()
            throws Exception {
        final String givenTableName = "table";
        final FuelSpecification givenSpecification = createSpecification(givenTableName);

        final SpecificationValidatingResult givenValidatingResult = createValidValidatingResult();
        when(this.mockedValidatingManager.validate(eq(givenSpecification))).thenReturn(givenValidatingResult);

        final Fuel givenFuel = new Fuel(5.5, 6.6);
        when(this.mockedSearchingManager.find(eq(givenSpecification))).thenReturn(Optional.of(givenFuel));

        final String actualResponse = doRequest(this.mockMvc, givenSpecification, OK);
        final String expectedResponse = "{\"generationNorm\":5.5,\"consumption\":6.6}";
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void fuelShouldNotBeFound()
            throws Exception {
        final String givenTableName = "table-name";
        final FuelSpecification givenSpecification = createSpecification(givenTableName);

        final SpecificationValidatingResult givenValidatingResult = createValidValidatingResult();
        when(this.mockedValidatingManager.validate(eq(givenSpecification))).thenReturn(givenValidatingResult);

        when(this.mockedSearchingManager.find(eq(givenSpecification))).thenReturn(empty());

        final String actualResponse = doRequest(this.mockMvc, givenSpecification, NOT_FOUND);
        assertTrue(isNoSuchFuelError(actualResponse));
    }

    @Test
    public void fuelShouldNotBeFoundBecauseOfSpecificationIsNotValid()
            throws Exception {
        final String givenTableName = "table-name";
        final FuelSpecification givenSpecification = createSpecification(givenTableName);
        final String[] givenFailedPropertyNames = new String[]{
                "property-1", "property-2", "property-3"
        };

        final SpecificationValidatingResult givenValidatingResult = createNotValidValidatingResult(
                givenFailedPropertyNames
        );
        when(this.mockedValidatingManager.validate(eq(givenSpecification))).thenReturn(givenValidatingResult);

        final String actualResponse = doRequest(this.mockMvc, givenSpecification, NOT_ACCEPTABLE);
        assertTrue(isNotValidSpecificationError(actualResponse));

        final Set<String> actualFailedPropertyNames = findFailedPropertyNames(actualResponse);
        final Set<String> expectedFailedPropertyNames = Set.of(givenFailedPropertyNames);
        assertEquals(expectedFailedPropertyNames, actualFailedPropertyNames);

        verify(this.mockedSearchingManager, times(0)).find(any(FuelSpecification.class));
    }

    private static FuelSpecification createSpecification(final String tableName) {
        return FuelSpecification.builder()
                .tableName(tableName)
                .build();
    }

    private static SpecificationValidatingResult createValidValidatingResult() {
        return createValidatingResult(true);
    }

    private static SpecificationValidatingResult createNotValidValidatingResult(final String... failedPropertyNames) {
        final SpecificationValidatingResult validatingResult = createValidatingResult(false);
        final List<String> failedPropertyNamesAsList = asList(failedPropertyNames);
        when(validatingResult.findFailedPropertyNames()).thenReturn(failedPropertyNamesAsList);
        return validatingResult;
    }

    private static SpecificationValidatingResult createValidatingResult(final boolean valid) {
        final SpecificationValidatingResult validatingResult = mock(SpecificationValidatingResult.class);
        when(validatingResult.isValid()).thenReturn(valid);
        return validatingResult;
    }

}
