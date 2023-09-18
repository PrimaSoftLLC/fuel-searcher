package by.aurorasoft.fuelsearcher.configuration;

import org.junit.Test;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertNotNull;

public final class ModelMapperConfigurationTest {
    private final ModelMapperConfiguration configuration = new ModelMapperConfiguration();

    @Test
    public void modelMapperShouldBeCreated() {
        final ModelMapper actual = this.configuration.modelMapper();
        assertNotNull(actual);
    }
}
