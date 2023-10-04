package com.aurorasoft.fuelsearcher.service.tableservice;

import com.aurorasoft.fuelsearcher.model.FuelDocument;
import com.aurorasoft.fuelsearcher.model.FuelTable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public final class FuelTableServiceTest {

    @Mock
    private FuelDocument mockedDocument;

    private FuelTableService tableService;

    @Before
    public void initializeTableService() {
        this.tableService = new FuelTableService(this.mockedDocument);
    }

    @Test
    public void tableNamesShouldBeFound() {
        final String firstGivenTableName = "first-table";
        final FuelTable firstGivenTable = createTable(firstGivenTableName);

        final String secondGivenTableName = "second-table";
        final FuelTable secondGivenTable = createTable(secondGivenTableName);

        final List<FuelTable> givenTables = List.of(firstGivenTable, secondGivenTable);
        when(this.mockedDocument.tables()).thenReturn(givenTables);

        final List<String> actual = this.tableService.findTableNames();
        final List<String> expected = List.of(firstGivenTableName, secondGivenTableName);
        assertEquals(expected, actual);
    }

    @Test
    public void tableNamesShouldNotBeFound() {
        final List<FuelTable> givenTables = emptyList();
        when(this.mockedDocument.tables()).thenReturn(givenTables);

        final List<String> actual = this.tableService.findTableNames();
        assertTrue(actual.isEmpty());
    }

    private static FuelTable createTable(final String name) {
        final FuelTable table = mock(FuelTable.class);
        when(table.name()).thenReturn(name);
        return table;
    }
}
