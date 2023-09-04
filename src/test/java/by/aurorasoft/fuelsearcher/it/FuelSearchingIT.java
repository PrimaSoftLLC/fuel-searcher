package by.aurorasoft.fuelsearcher.it;

import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
import by.aurorasoft.fuelsearcher.model.Fuel;
import by.aurorasoft.fuelsearcher.model.specification.FuelSpecification;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
public final class FuelSearchingIT extends AbstractContextTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource("fuelSearchingArgumentProvider")
    public void fuelShouldBeFound(final FuelSpecification specification, final Fuel expected) {

    }


}
