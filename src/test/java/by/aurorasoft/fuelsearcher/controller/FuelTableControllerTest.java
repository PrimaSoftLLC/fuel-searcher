package by.aurorasoft.fuelsearcher.controller;

import by.aurorasoft.fuelsearcher.model.FuelDocument;
import by.aurorasoft.fuelsearcher.model.FuelTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static by.aurorasoft.fuelsearcher.testutil.ControllerRequestUtil.doRequest;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(FuelTableController.class)
public final class FuelTableControllerTest {
    private static final String URL_CONTROLLER = "/fuelTable";

    private static final String PATH_TO_FIND_TABLE_NAMES = "/tableNames";
    private static final String URL_TO_FIND_TABLE_NAMES = URL_CONTROLLER + PATH_TO_FIND_TABLE_NAMES;

    @MockBean
    private FuelDocument mockedDocument;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void tableNamesShouldBeFound() throws Exception {
        final List<FuelTable> givenTables = List.of(
                createTable("first-table"),
                createTable("second-table"),
                createTable("third-table")
        );
        when(this.mockedDocument.tables()).thenReturn(givenTables);

        final String actual = doRequest(this.mockMvc, get(URL_TO_FIND_TABLE_NAMES), OK);
        final String expected = "[\"first-table\",\"second-table\",\"third-table\"]";
        assertEquals(expected, actual);
    }

    private static FuelTable createTable(final String name) {
        final FuelTable table = mock(FuelTable.class);
        when(table.name()).thenReturn(name);
        return table;
    }
}
