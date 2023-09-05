package by.aurorasoft.fuelsearcher.controller;

import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
import by.aurorasoft.fuelsearcher.service.searcher.FuelSearchingManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(FuelController.class)
public final class FuelControllerTest extends AbstractContextTest {

    @MockBean
    private FuelSearchingManager mockedSearchingManager;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void fuelShouldBeFound() {
        throw new RuntimeException();
    }

}
