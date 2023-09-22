package by.aurorasoft.fuelsearcher.service.searchersparser.handler;

import by.aurorasoft.fuelsearcher.service.dictionary.TagHandlerDictionary;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.context.SearchersParsingContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

@RunWith(MockitoJUnitRunner.class)
public final class SearchersParsingHandlerFactoryTest {
    private static final String HANDLER_FIELD_NAME_TAG_HANDLER_DICTIONARY = "handlerDictionary";
    private static final String HANDLER_FIELD_NAME_CONTEXT = "context";

    @Mock
    private TagHandlerDictionary mockedHandlerDictionary;

    private SearchersParsingHandlerFactory parsingHandlerFactory;

    @Before
    public void initializeParsingHandlerFactory() {
        this.parsingHandlerFactory = new SearchersParsingHandlerFactory(null, this.mockedHandlerDictionary);
    }

    @Test
    public void parsingHandlerShouldBeCreated()
            throws Exception {
        final SearchersParsingHandler actual = this.parsingHandlerFactory.create();

        final TagHandlerDictionary actualHandlerDictionary = findTagHandlerDictionary(actual);
        assertSame(this.mockedHandlerDictionary, actualHandlerDictionary);

        final SearchersParsingContext actualContext = findContext(actual);
        assertNotNull(actualContext);
    }

    private static TagHandlerDictionary findTagHandlerDictionary(final SearchersParsingHandler handler)
            throws Exception {
        return findHandlerProperty(handler, HANDLER_FIELD_NAME_TAG_HANDLER_DICTIONARY, TagHandlerDictionary.class);
    }

    private static SearchersParsingContext findContext(final SearchersParsingHandler handler)
            throws Exception {
        return findHandlerProperty(handler, HANDLER_FIELD_NAME_CONTEXT, SearchersParsingContext.class);
    }

    private static <T> T findHandlerProperty(final SearchersParsingHandler handler,
                                             final String fieldName,
                                             final Class<T> propertyType)
            throws Exception {
        final Field field = SearchersParsingHandler.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        try {
            final Object property = field.get(handler);
            return propertyType.cast(property);
        } finally {
            field.setAccessible(false);
        }
    }
}
