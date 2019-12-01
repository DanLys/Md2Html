package md2html;

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
        str.append(template.getC(c));
    }

    private String parser(String teg, int from) {
        StringBuilder str = new StringBuilder();

        index = from;
        for (int i = from; i < text.length(); i++, index++) {
            if (text.charAt(i) == '\\') {
                if (i + 1 < text.length()) {
                    pushChar(text.charAt(i + 1), str);
                    i++;
                    index++;
                    continue;
                }
            }
            String tegP = text.substring(i, Math.min(i + 2, text.length()));
            int flag = isTeg(tegP);
            tegP = tegP.substring(0, flag);

            if (tegP.equals(teg) && !teg.isBlank()) {
                str.append(template.getCloseTeg(teg));
                index += flag;
                return template.getOpenTeg(teg).append(str).toString();
            }
            if (flag > 0) {
                str.append(parser(tegP, i + flag));
                i = --index;
            } else {
                pushChar(text.charAt(i), str);
            }
        }

        return teg + str.toString();
    }

    public void toHtml(StringBuilder resultHtml) {
        resultHtml.append(parser("", 0));
    }
}
