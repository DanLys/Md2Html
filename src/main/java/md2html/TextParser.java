package md2html;

public class TextParser {
    private StringBuilder text;
    private Template template;
    private int point;

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

        point = from;
        for (int i = from; i < text.length(); i++, point++) {
            if (text.charAt(i) == '\\') {
                continue;
            }
            String tegP = text.substring(i, Math.min(i + 2, text.length()));
            int flag = isTeg(tegP);

            if (tegP.substring(0, flag).equals(teg) && !teg.isBlank()) {
                str.append(template.getCloseTeg(teg));
                point += flag;
                return str.toString();
            }
            if (flag > 0) {
                str.append(parser(tegP.substring(0, flag), i + flag));
                i = --point;
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
