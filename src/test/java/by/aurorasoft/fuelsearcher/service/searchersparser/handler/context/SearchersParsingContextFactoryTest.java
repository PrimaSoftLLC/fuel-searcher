package by.aurorasoft.fuelsearcher.service.searchersparser.handler.context;

import by.aurorasoft.fuelsearcher.service.searchersparser.handler.metadatasearcher.PropertyMetadataSearchingManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;

import static org.junit.Assert.assertSame;

@RunWith(MockitoJUnitRunner.class)
public final class SearchersParsingContextFactoryTest {
    private static final String FIELD_NAME_PROPERTY_METADATA_SEARCHING_MANAGER = "propertyMetadataSearchingManager";

    @Mock
    private PropertyMetadataSearchingManager mockedPropertyMetadataSearchingManager;

    private SearchersParsingContextFactory factory;

    @Before
    public void initializeFactory() {
        this.factory = new SearchersParsingContextFactory(this.mockedPropertyMetadataSearchingManager);
    }

    @Test
    public void contextShouldBeCreated()
            throws Exception {
        final SearchersParsingContext actual = this.factory.create();

        final PropertyMetadataSearchingManager actualSearchingManager = findPropertyMetadataSearchingManager(actual);
        assertSame(this.mockedPropertyMetadataSearchingManager, actualSearchingManager);
    }

    private static PropertyMetadataSearchingManager findPropertyMetadataSearchingManager(
            final SearchersParsingContext context)
            throws Exception {
        final Field field = SearchersParsingContext.class.getDeclaredField(
                FIELD_NAME_PROPERTY_METADATA_SEARCHING_MANAGER
        );
        field.setAccessible(true);
        try {
            return (PropertyMetadataSearchingManager) field.get(context);
        } finally {
            field.setAccessible(false);
        }
    }
}
