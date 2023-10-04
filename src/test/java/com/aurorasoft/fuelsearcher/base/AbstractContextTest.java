package com.aurorasoft.fuelsearcher.base;

import com.yannbriancon.interceptor.HibernateQueryInterceptor;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.TimeZone;

import static java.lang.System.out;
import static java.util.TimeZone.getTimeZone;
import static java.util.TimeZone.setDefault;
import static org.junit.Assert.assertEquals;
import static org.testcontainers.utility.DockerImageName.parse;

@DirtiesContext
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = {AbstractContextTest.DBContainerInitializer.class})
public abstract class AbstractContextTest {
    private static final String POSTGRESQL_CONTAINER_FULL_IMAGE_NAME = "mdillon/postgis:9.5";
    private static final String POSTGRESQL_CONTAINER_OTHER_IMAGE_NAME = "postgres";
    private static final String POSTGRESQL_CONTAINER_DATABASE_NAME = "integration-tests-db";
    private static final String POSTGRESQL_CONTAINER_USERNAME = "sa";
    private static final String POSTGRESQL_CONTAINER_PASSWORD = "sa";

    private static final String DEFAULT_TIME_ZONE_ID = "UTC";
    private static final TimeZone DEFAULT_TIME_ZONE = getTimeZone(DEFAULT_TIME_ZONE_ID);

    private static final String STARTING_QUERY_COUNT_MESSAGE
            = "======================= START QUERY COUNTER ====================================";
    private static final String FINISHING_QUERY_COUNT_MESSAGE
            = "======================= FINISH QUERY COUNTER ====================================";
    private static final String WRONG_QUERY_COUNT_MESSAGE = "wrong count of queries";

    private static final PostgreSQLContainer<?> POSTGRESQL_CONTAINER = createPostgreSQLContainer();

    static {
        POSTGRESQL_CONTAINER.start();
    }

    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    protected HibernateQueryInterceptor queryInterceptor;

    @BeforeClass
    public static void setDefaultTimeZone() {
        setDefault(DEFAULT_TIME_ZONE);
    }

    protected final void startQueryCount() {
        out.println(STARTING_QUERY_COUNT_MESSAGE);
        this.queryInterceptor.startQueryCount();
    }

    protected final void flushAndCheckQueryCount(final int expected) {
        this.entityManager.flush();
        out.println(FINISHING_QUERY_COUNT_MESSAGE);
        assertEquals(WRONG_QUERY_COUNT_MESSAGE, Long.valueOf(expected), this.findQueryCount());
    }

    @SuppressWarnings("resource")
    private static PostgreSQLContainer<?> createPostgreSQLContainer() {
        final DockerImageName dockerImageName = createDockerImageName();
        return new PostgreSQLContainer<>(dockerImageName)
                .withDatabaseName(POSTGRESQL_CONTAINER_DATABASE_NAME)
                .withUsername(POSTGRESQL_CONTAINER_USERNAME)
                .withPassword(POSTGRESQL_CONTAINER_PASSWORD);
    }

    private static DockerImageName createDockerImageName() {
        return parse(POSTGRESQL_CONTAINER_FULL_IMAGE_NAME)
                .asCompatibleSubstituteFor(POSTGRESQL_CONTAINER_OTHER_IMAGE_NAME);
    }

    private Long findQueryCount() {
        return this.queryInterceptor.getQueryCount();
    }

    static final class DBContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        private static final String DATASOURCE_URL_PROPERTY_DESCRIPTION = "spring.datasource.url="
                + POSTGRESQL_CONTAINER.getJdbcUrl();
        private static final String DATASOURCE_USERNAME_PROPERTY_DESCRIPTION = "spring.datasource.username="
                + POSTGRESQL_CONTAINER.getUsername();
        private static final String DATASOURCE_PASSWORD_PROPERTY_DESCRIPTION = "spring.datasource.password="
                + POSTGRESQL_CONTAINER.getPassword();

        @Override
        public void initialize(final ConfigurableApplicationContext context) {
            final ConfigurableEnvironment environment = context.getEnvironment();
            TestPropertyValues.of(
                    DATASOURCE_URL_PROPERTY_DESCRIPTION,
                    DATASOURCE_USERNAME_PROPERTY_DESCRIPTION,
                    DATASOURCE_PASSWORD_PROPERTY_DESCRIPTION
            ).applyTo(environment);
        }
    }
}
