package by.aurorasoft.fuelsearcher.service.documentcreating.corrector.component;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.aurorasoft.fuelsearcher.util.XWPFParagraphUtil.replaceText;
import static java.util.regex.Pattern.compile;

public abstract class ContentParagraphCorrector {
    private final Pattern patternReplacedRegex;

    public ContentParagraphCorrector(final String replacedRegex) {
        this.patternReplacedRegex = compile(replacedRegex);
    }

    public final void correct(final XWPFParagraph paragraph) {
        final String content = paragraph.getText();
        final String correctedContent = this.correct(content);
        replaceText(paragraph, correctedContent);
    }

    protected abstract String createReplacement(final MatchResult matchResult);

    private String correct(final String paragraphContent) {
        final Matcher matcher = this.patternReplacedRegex.matcher(paragraphContent);
        return matcher.replaceAll(this::createReplacement);
    }
}
