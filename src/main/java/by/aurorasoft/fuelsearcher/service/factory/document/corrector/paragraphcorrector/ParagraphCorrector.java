package by.aurorasoft.fuelsearcher.service.factory.document.corrector.paragraphcorrector;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.aurorasoft.fuelsearcher.util.XWPFParagraphUtil.replaceText;
import static java.util.regex.Pattern.compile;

public abstract class ParagraphCorrector {
    private final Pattern replacedPattern;

    public ParagraphCorrector(final String replacedRegex) {
        this.replacedPattern = compile(replacedRegex);
    }

    public final void correct(final XWPFParagraph paragraph) {
        final String content = paragraph.getText();
        final String correctedContent = this.correct(content);
        replaceText(paragraph, correctedContent);
    }

    protected abstract String createReplacement(final MatchResult matchResult);

    private String correct(final String paragraphContent) {
        final Matcher matcher = this.replacedPattern.matcher(paragraphContent);
        return matcher.replaceAll(this::createReplacement);
    }
}
