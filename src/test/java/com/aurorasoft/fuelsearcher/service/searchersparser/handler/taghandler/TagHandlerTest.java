package com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler;

import com.aurorasoft.fuelsearcher.service.searchersparser.handler.SearchersParsingContext;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public final class TagHandlerTest {

    @Test
    public void aliasShouldBeFound() {
        final String givenTagName = "tag-name";
        final TagHandler givenTagHandler = new TestTagHandler(givenTagName);

        final String actual = givenTagHandler.findAlias();
        assertSame(givenTagName, actual);
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
