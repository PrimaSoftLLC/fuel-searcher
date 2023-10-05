package com.aurorasoft.fuelsearcher.service.searchersparser.handler;

import com.aurorasoft.fuelsearcher.service.dictionary.TagHandlerDictionary;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.aurorasoft.fuelsearcher.testutil.ReflectionUtil.findProperty;
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
        this.parsingHandlerFactory = new SearchersParsingHandlerFactory(this.mockedHandlerDictionary);
    }

    @Test
    public void parsingHandlerShouldBeCreated() {
        final SearchersParsingHandler actual = this.parsingHandlerFactory.create();

        final TagHandlerDictionary actualHandlerDictionary = findTagHandlerDictionary(actual);
        assertSame(this.mockedHandlerDictionary, actualHandlerDictionary);

        final SearchersParsingContext actualContext = findContext(actual);
        assertNotNull(actualContext);
    }

    private static TagHandlerDictionary findTagHandlerDictionary(final SearchersParsingHandler handler) {
        return findProperty(
                handler,
                HANDLER_FIELD_NAME_TAG_HANDLER_DICTIONARY,
                TagHandlerDictionary.class
        );
    }

    private static SearchersParsingContext findContext(final SearchersParsingHandler handler) {
        return findProperty(
                handler,
                HANDLER_FIELD_NAME_CONTEXT,
                SearchersParsingContext.class
        );
    }
}
