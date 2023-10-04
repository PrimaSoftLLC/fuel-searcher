package com.aurorasoft.fuelsearcher.service.searcher;

import com.aurorasoft.fuelsearcher.model.Fuel;
import com.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import com.aurorasoft.fuelsearcher.model.specification.propertyextractor.TableNameExtractor;
import com.aurorasoft.fuelsearcher.service.dictionary.FuelSearcherDictionary;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static java.util.Optional.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public final class FuelSearchingManagerTest {

    @Mock
    private FuelSearcherDictionary mockedFuelSearcherDictionary;

    @Mock
    private TableNameExtractor mockedTableNameExtractor;

    private FuelSearchingManager searchingManager;

    @Before
    public void initializeSearchingManager() {
        this.searchingManager = new FuelSearchingManager(
                this.mockedFuelSearcherDictionary,
                this.mockedTableNameExtractor
        );
    }

    @Test
    public void fuelShouldBeFound() {
        final FuelSpecification givenSpecification = createSpecification();

        final String givenTableName = "table-name";
        when(this.mockedTableNameExtractor.extract(givenSpecification)).thenReturn(givenTableName);

        final FuelSearcher givenFuelSearcher = Mockito.mock(FuelSearcher.class);
        when(this.mockedFuelSearcherDictionary.find(givenTableName)).thenReturn(Optional.of(givenFuelSearcher));

        final Fuel expected = new Fuel(5.5, 6.6);
        when(givenFuelSearcher.find(givenSpecification)).thenReturn(Optional.of(expected));

        final Optional<Fuel> optionalActual = this.searchingManager.find(givenSpecification);
        assertTrue(optionalActual.isPresent());
        final Fuel actual = optionalActual.get();
        assertEquals(expected, actual);
    }

    @Test
    public void fuelShouldNotBeFoundBecauseOfSearcherShouldNotBeFound() {
        final FuelSpecification givenSpecification = createSpecification();

        final String givenTableName = "table-name";
        when(this.mockedTableNameExtractor.extract(givenSpecification)).thenReturn(givenTableName);

        when(this.mockedFuelSearcherDictionary.find(givenTableName)).thenReturn(empty());

        final Optional<Fuel> optionalActual = this.searchingManager.find(givenSpecification);
        assertTrue(optionalActual.isEmpty());
    }

    @Test
    public void fuelShouldNotBeFoundByFoundSearcher() {
        final FuelSpecification givenSpecification = createSpecification();

        final String givenTableName = "table-name";
        when(this.mockedTableNameExtractor.extract(givenSpecification)).thenReturn(givenTableName);

        final FuelSearcher givenFuelSearcher = Mockito.mock(FuelSearcher.class);
        when(this.mockedFuelSearcherDictionary.find(givenTableName)).thenReturn(Optional.of(givenFuelSearcher));

        when(givenFuelSearcher.find(givenSpecification)).thenReturn(empty());

        final Optional<Fuel> optionalActual = this.searchingManager.find(givenSpecification);
        assertTrue(optionalActual.isEmpty());
    }

    private static FuelSpecification createSpecification() {
        return FuelSpecification.builder().build();
    }

}
