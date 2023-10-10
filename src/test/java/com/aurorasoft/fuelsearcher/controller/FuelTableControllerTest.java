package com.aurorasoft.fuelsearcher.controller;

import com.aurorasoft.fuelsearcher.service.tableservice.FuelTableService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.aurorasoft.fuelsearcher.testutil.ControllerRequestUtil.doRequestAndGetContentAsJson;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@WebMvcTest(FuelTableController.class)
public final class FuelTableControllerTest {
    private static final String CONTROLLER_URL = "/fuelTable";

    private static final String PATH_TO_FIND_TABLE_NAMES = "/tableNames";
    private static final String URL_TO_FIND_TABLE_NAMES = CONTROLLER_URL + PATH_TO_FIND_TABLE_NAMES;

    @MockBean
    private FuelTableService mockedTableService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void tableNamesShouldBeFound() throws Exception {
        final List<String> givenTableNames = List.of("first-table", "second-table", "third-table");
        when(this.mockedTableService.findTableNames()).thenReturn(givenTableNames);

        final String actual = doRequestAndGetContentAsJson(this.mockMvc, URL_TO_FIND_TABLE_NAMES, OK);
        final String expected = "[\"first-table\",\"second-table\",\"third-table\"]";
        assertEquals(expected, actual);
    }
}
