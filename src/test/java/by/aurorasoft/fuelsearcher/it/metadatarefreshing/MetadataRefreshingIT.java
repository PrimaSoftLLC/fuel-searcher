package by.aurorasoft.fuelsearcher.it.metadatarefreshing;

import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataArguments;
import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider.DocumentMetadataArgumentsProvider;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import static by.aurorasoft.fuelsearcher.testutil.CollectionUtil.areEqualIgnoringOrder;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.context.support.TestPropertySourceUtils.addInlinedPropertiesToEnvironment;

@ContextConfiguration(initializers = {MetadataRefreshingIT.MetadataRefreshingEnablePropertyOverrider.class})
public final class MetadataRefreshingIT extends AbstractContextTest {
    private static final String HQL_QUERY_TO_FIND_PROPERTY_ALLOWABLE_VALUES
            = "SELECT e.allowableValues FROM PropertyMetadataEntity e "
            + "WHERE e.propertyName = :propertyName AND e.tableMetadata.tableName = :tableName";
    private static final String PARAMETER_NAME_PROPERTY_NAME = "propertyName";
    private static final String PARAMETER_NAME_TABLE_NAME = "tableName";

    @ParameterizedTest
    @ArgumentsSource(DocumentMetadataArgumentsProvider.class)
    public void propertyMetadataShouldBeRefreshed(final PropertyMetadataArguments arguments) {
        final String givenTableName = arguments.tableName();
        final String givenPropertyName = arguments.propertyName();

        final String[] actualPropertyAllowableValues = this.findPropertyAllowableValues(
                givenTableName,
                givenPropertyName
        );
        final String[] expectedPropertyAllowableValues = arguments.expectedPropertyAllowableValues();
        assertTrue(areEqualIgnoringOrder(expectedPropertyAllowableValues, actualPropertyAllowableValues));
    }

    private String[] findPropertyAllowableValues(final String tableName, final String propertyName) {
        return super.entityManager.createQuery(HQL_QUERY_TO_FIND_PROPERTY_ALLOWABLE_VALUES, String[].class)
                .setParameter(PARAMETER_NAME_TABLE_NAME, tableName)
                .setParameter(PARAMETER_NAME_PROPERTY_NAME, propertyName)
                .getSingleResult();
    }

    public static final class MetadataRefreshingEnablePropertyOverrider
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        private static final String PROPERTY_DESCRIPTION = "metadata-refreshing.enable=true";

        @Override
        public void initialize(final @NotNull ConfigurableApplicationContext context) {
            addInlinedPropertiesToEnvironment(context, PROPERTY_DESCRIPTION);
        }
    }
}
