package by.aurorasoft.fuelsearcher.service.searchersparser.handler.context;

import by.aurorasoft.fuelsearcher.service.derivingsearcherfactory.refreshedtablesmetadata.metadatasearcher.PropertyMetadataSearchingManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static by.aurorasoft.fuelsearcher.testutil.ReflectionUtil.findProperty;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

@RunWith(MockitoJUnitRunner.class)
public final class SearchersParsingContextFactoryTest {
    private static final String FIELD_NAME_METADATA_SEARCHING_MANAGER = "propertyMetadataSearchingManager";

    @Mock
    private PropertyMetadataSearchingManager mockedMetadataSearchingManager;

    @Test
    public void contextShouldBeCreatedWithMetadataSearchingManager()
            throws Exception {
        final SearchersParsingContextFactory givenFactory = createContextFactory(true);

        final SearchersParsingContext actual = givenFactory.create();

        final PropertyMetadataSearchingManager actualSearchingManager = findMetadataSearchingManager(actual);
        assertSame(this.mockedMetadataSearchingManager, actualSearchingManager);
    }

    @Test
    public void contextShouldBeCreatedWithoutMetadataSearchingManager()
            throws Exception {
        final SearchersParsingContextFactory givenFactory = createContextFactory(false);

        final SearchersParsingContext actual = givenFactory.create();

        final PropertyMetadataSearchingManager actualSearchingManager = findMetadataSearchingManager(actual);
        assertNull(actualSearchingManager);
    }

    private SearchersParsingContextFactory createContextFactory(final boolean metadataRefreshingEnabled) {
        return new SearchersParsingContextFactory(this.mockedMetadataSearchingManager, metadataRefreshingEnabled);
    }

    private static PropertyMetadataSearchingManager findMetadataSearchingManager(final SearchersParsingContext context)
            throws Exception {
        return findProperty(
                context,
                SearchersParsingContext.class,
                FIELD_NAME_METADATA_SEARCHING_MANAGER,
                PropertyMetadataSearchingManager.class
        );
    }
}
