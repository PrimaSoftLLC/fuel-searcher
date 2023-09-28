package by.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler;

import by.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class TagHandlerTest {

    @Test
    public void aliasShouldBeFound() {
        final String givenTagName = "tag-name";
        final TagHandler givenTagHandler = new TestTagHandler(givenTagName);

        final String actual = givenTagHandler.findAlias();
        assertEquals(givenTagName, actual);
    }

    private static final class TestTagHandler extends TagHandler {

        public TestTagHandler(final String tagName) {
            super(tagName);
        }

        @Override
        public void handleStartTag(final SearchersParsingContext context) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void handleEndTag(final SearchersParsingContext context) {
            throw new UnsupportedOperationException();
        }
    }
}
