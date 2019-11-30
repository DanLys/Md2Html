package md2html;

import static java.lang.Character.isWhitespace;

public class TextParser {
    private StringBuilder text;
    private Template template;
    private int index;

    public TextParser(StringBuilder text) {
        this.text = text;
        template = new Template();
    }

    private int isTeg(String teg) {
        StringBuilder tegRes = template.get(teg);
        if (tegRes.length() > 0) {
            return 2;
        }

        tegRes = template.get(teg.substring(0, 1));
        return (tegRes.length() == 0 ? 0 : 1);
    }

    private void pushChar(char c, StringBuilder str) {
        String s = template.getC(c);
        if (s != null) {
            str.append(s);
            return;
        }
        str.append(c);
    }

    private String parser(String teg, int from) {
        StringBuilder str = new StringBuilder();
        StringBuilder openTeg = template.getOpenTeg(teg);
        str.append(openTeg);

        index = from;
        for (int i = from; i < text.length(); i++, index++) {
            if (text.charAt(i) == '\\') {
                continue;
            }
            String tegP = text.substring(i, Math.min(i + 2, text.length()));
            int flag = isTeg(tegP);

            if (tegP.substring(0, flag).equals(teg) && !teg.isBlank()) {
                str.append(template.getCloseTeg(teg));
                index += flag;
                return str.toString();
            }
            if (flag > 0 && i + flag + 1 < text.length() && !isWhitespace(text.charAt(i + flag + 1))) {
                str.append(parser(tegP.substring(0, flag), i + flag));
                i = --index;
            } else {
                pushChar(text.charAt(i), str);
            }
        }

        return teg + str.substring(openTeg.length());
    }

    public void toHtml(StringBuilder resultHtml) {
        resultHtml.append(parser("", 0));
    }
}
