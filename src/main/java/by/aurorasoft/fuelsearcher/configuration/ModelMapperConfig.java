package by.aurorasoft.fuelsearcher.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    //TODO: test
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
