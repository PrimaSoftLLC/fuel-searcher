package by.aurorasoft.fuelsearcher.it.metadatarefreshing;

import by.aurorasoft.fuelsearcher.base.AbstractContextTest;
import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataView;
import by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.argumentsprovider.DocumentPropertyMetadataViewsProvider;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.context.support.TestPropertySourceUtils.addInlinedPropertiesToEnvironment;

@ContextConfiguration(initializers = {MetadataRefreshingIT.MetadataRefreshingEnablePropertyOverrider.class})
public final class MetadataRefreshingIT extends AbstractContextTest {
    private static final String HQL_QUERY_TO_FIND_ALL_PROPERTY_METADATA_VIEWS = "SELECT new "
            + "by.aurorasoft.fuelsearcher.it.metadatarefreshing.model.PropertyMetadataView("
            + "e.tableMetadata.tableName, e.propertyName, e.allowableValues"
            + ") "
            + "FROM PropertyMetadataEntity e";

    private final DocumentPropertyMetadataViewsProvider expectedViewsProvider
            = new DocumentPropertyMetadataViewsProvider();

    @Test
    public void propertyMetadataShouldBeRefreshed() {
        final Set<PropertyMetadataView> actual = this.findPropertyMetadataViews();
        final Set<PropertyMetadataView> expected = this.expectedViewsProvider.provide();
        assertEquals(expected, actual);
    }

    private Set<PropertyMetadataView> findPropertyMetadataViews() {
        return super.entityManager.createQuery(
                HQL_QUERY_TO_FIND_ALL_PROPERTY_METADATA_VIEWS,
                PropertyMetadataView.class
        ).getResultStream().collect(toSet());
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
