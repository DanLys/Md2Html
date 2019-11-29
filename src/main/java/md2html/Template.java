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
    private Map<Character, String> templChar = Map.of(
            '>', "&gt;",
            '<', "&lt;",
            '&', "&amp;"
    );

    public Template () {}


    public StringBuilder get(String tegMark) {
        return new StringBuilder(templ.getOrDefault(tegMark, ""));
    }

    public String getC(char c) {
        return templChar.getOrDefault(c, null);
    }

    public StringBuilder getOpenTeg(String tegMark) {
        StringBuilder tmp = new StringBuilder();
        tmp.append("<");
        tmp.append(templ.get(tegMark));
        return tmp.append(">");
    }

    public StringBuilder getCloseTeg(String tegMark) {
        StringBuilder tmp = new StringBuilder();
        tmp.append("</");
        tmp.append(templ.get(tegMark));
        return tmp.append(">");
    }
}
