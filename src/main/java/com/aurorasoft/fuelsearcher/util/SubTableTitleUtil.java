package com.aurorasoft.fuelsearcher.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.text.translate.AggregateTranslator;
import org.apache.commons.text.translate.CharSequenceTranslator;
import org.apache.commons.text.translate.LookupTranslator;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.regex.Pattern.compile;
import static java.util.stream.Collectors.toMap;

@UtilityClass
public final class SubTableTitleUtil {
    private static final String SLASH = "\\";
    private static final String ESCAPED_SLASH = "\\\\";

    private static final List<String> TITLE_REGEX_SPECIAL_SYMBOLS = List.of(
            SLASH, ".", "[", "]", "(", ")", "<", ">", "*", "+", "-", "=", "!", "?", "^", "$", "|"
    );
    private static final Map<CharSequence, CharSequence> ESCAPED_SYMBOLS_BY_TITLE_REGEX_SPECIAL_SYMBOLS
            = findEscapedSymbolsByTitleRegexSpecialSymbols();
    private static final LookupTranslator TITLE_LOOKUP_TRANSLATOR = new LookupTranslator(
            ESCAPED_SYMBOLS_BY_TITLE_REGEX_SPECIAL_SYMBOLS
    );
    private static final CharSequenceTranslator TITLE_CHAR_SEQUENCE_TRANSLATOR = new AggregateTranslator(
            TITLE_LOOKUP_TRANSLATOR
    );

    private static final String PROPERTY_NAME_PLACE_REGEX = "\\{([^{}]+)}";
    private static final int PROPERTY_NAME_GROUP_NUMBER = 1;
    private static final Pattern PROPERTY_NAME_PATTERN = compile(PROPERTY_NAME_PLACE_REGEX);

    private static final String STRING_FILLER = "%s";

    private static final String PROPERTY_NAME_REGEX = "(.+)";

    public static Stream<String> findPropertyNames(final String templateWithPropertyNames) {
        return PROPERTY_NAME_PATTERN.matcher(templateWithPropertyNames)
                .results()
                .map(matchResult -> matchResult.group(PROPERTY_NAME_GROUP_NUMBER));
    }

    public static String findTemplateWithStringFillers(final String templateWithPropertyNames) {
        return templateWithPropertyNames.replaceAll(PROPERTY_NAME_PLACE_REGEX, STRING_FILLER);
    }

    public static String findTemplateRegex(final String templateWithPropertyNames) {
        final String templateWithEscapedSpecialSymbols = TITLE_CHAR_SEQUENCE_TRANSLATOR.translate(
                templateWithPropertyNames
        );
        return templateWithEscapedSpecialSymbols.replaceAll(PROPERTY_NAME_PLACE_REGEX, PROPERTY_NAME_REGEX);
    }

    private static Map<CharSequence, CharSequence> findEscapedSymbolsByTitleRegexSpecialSymbols() {
        return TITLE_REGEX_SPECIAL_SYMBOLS.stream()
                .collect(
                        toMap(
                                identity(),
                                SubTableTitleUtil::escape
                        )
                );
    }

    private static String escape(final String source) {
        return !Objects.equals(source, SLASH) ? SLASH + source : ESCAPED_SLASH;
    }
}
