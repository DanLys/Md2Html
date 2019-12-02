package md2html;

import java.util.Map;

public class Template {
    private final static Map<String, String> templ = Map.of(
            "*", "em",
            "_", "em",
            "**", "strong",
            "__", "strong",
            "--", "s",
            "`", "code",
            "++", "u"
    );
    private final static Map<Character, String> templChar = Map.of(
            '>', "&gt;",
            '<', "&lt;",
            '&', "&amp;"
    );

    public Template () {}

    public StringBuilder get(String tagMark) {
        return new StringBuilder(templ.getOrDefault(tagMark, ""));
    }

    public String getC(char c) {
        return templChar.getOrDefault(c, String.valueOf(c));
    }

    public StringBuilder getOpenTag(String tagMark) {
        StringBuilder tmp = new StringBuilder();
        tmp.append("<");
        tmp.append(templ.get(tagMark));
        return tmp.append(">");
    }

    public StringBuilder getCloseTag(String tagMark) {
        StringBuilder tmp = new StringBuilder();
        tmp.append("</");
        tmp.append(templ.get(tagMark));
        return tmp.append(">");
    }
}
