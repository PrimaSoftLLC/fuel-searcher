package by.aurorasoft.fuelsearcher.service.searchersparser.handler;

import by.aurorasoft.fuelsearcher.service.dictionary.TagHandlerDictionary;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.context.SearchersParsingContext;
import by.aurorasoft.fuelsearcher.service.searchersparser.handler.context.SearchersParsingContextFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static by.aurorasoft.fuelsearcher.testutil.ReflectionUtil.findProperty;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public final class SearchersParsingHandlerFactoryTest {
    private static final String HANDLER_FIELD_NAME_TAG_HANDLER_DICTIONARY = "handlerDictionary";
    private static final String HANDLER_FIELD_NAME_CONTEXT = "context";

    @Mock
    private SearchersParsingContextFactory mockedContextFactory;

    @Mock
    private TagHandlerDictionary mockedHandlerDictionary;

    private SearchersParsingHandlerFactory parsingHandlerFactory;

    @Before
    public void initializeParsingHandlerFactory() {
        this.parsingHandlerFactory = new SearchersParsingHandlerFactory(
                this.mockedContextFactory,
                this.mockedHandlerDictionary
        );
    }

    @Test
    public void parsingHandlerShouldBeCreated()
            throws Exception {
        final SearchersParsingContext givenContext = mock(SearchersParsingContext.class);
        when(this.mockedContextFactory.create()).thenReturn(givenContext);

        final SearchersParsingHandler actual = this.parsingHandlerFactory.create();

        final TagHandlerDictionary actualHandlerDictionary = findTagHandlerDictionary(actual);
        assertSame(this.mockedHandlerDictionary, actualHandlerDictionary);

        final SearchersParsingContext actualContext = findContext(actual);
        assertSame(givenContext, actualContext);
    }

    private static TagHandlerDictionary findTagHandlerDictionary(final SearchersParsingHandler handler)
            throws Exception {
        return findProperty(
                handler,
                SearchersParsingHandler.class,
                HANDLER_FIELD_NAME_TAG_HANDLER_DICTIONARY,
                TagHandlerDictionary.class
        );
    }

    private static SearchersParsingContext findContext(final SearchersParsingHandler handler)
            throws Exception {
        return findProperty(
                handler,
                SearchersParsingHandler.class,
                HANDLER_FIELD_NAME_CONTEXT,
                SearchersParsingContext.class
        );
    }
}
