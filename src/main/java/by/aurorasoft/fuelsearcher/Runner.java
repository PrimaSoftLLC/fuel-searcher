package by.aurorasoft.fuelsearcher;

import by.aurorasoft.fuelsearcher.configuration.MainConfiguration;
import by.aurorasoft.fuelsearcher.service.searcher.manager.FuelSearchingManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class Runner {
    public static void main(final String... args) {
        final ApplicationContext context = new AnnotationConfigApplicationContext(MainConfiguration.class);
        System.out.println(context.getBean(FuelSearchingManager.class));
    }
}
