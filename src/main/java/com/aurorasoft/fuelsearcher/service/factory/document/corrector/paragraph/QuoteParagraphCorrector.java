package com.aurorasoft.fuelsearcher.service.factory.document.corrector.paragraph;

import org.springframework.stereotype.Component;

import java.util.regex.MatchResult;

@Component
public final class QuoteParagraphCorrector extends ParagraphCorrector {
    private static final String REPLACED_REGEX = "[«»]";
    private static final String REPLACEMENT = "\"";

    public QuoteParagraphCorrector() {
        super(REPLACED_REGEX);
    }

    @Override
    protected String createReplacement(final MatchResult matchResult) {
        return REPLACEMENT;
    }
}
