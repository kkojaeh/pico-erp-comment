package pico.erp.comment.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;
import pico.erp.comment.core.CommentParser;
import pico.erp.comment.core.ParsedComment;
import pico.erp.shared.Public;

@Public
@Component
public class CommentParserImpl implements CommentParser {

  private static final Pattern AT_PATTERN = Pattern.compile("@([^@^,\\s])+");

  private static final Pattern HTML_TAG_PATTERN = Pattern.compile("\\<.*?>");

  @Override
  public ParsedComment parse(String comment) {
    String striped = HtmlUtils.htmlUnescape(HTML_TAG_PATTERN.matcher(comment).replaceAll(" "));
    Matcher matcher = AT_PATTERN.matcher(striped);
    Set<String> mentions = new HashSet<>();
    while (matcher.find()) {
      mentions.add(matcher.group().substring(1));
    }
    return ParsedComment.builder()
      .mentions(mentions)
      .build();
  }
}
