package md2html;

public class TextParser {
    private StringBuilder text;
    private Template template;
    private int index;

    public TextParser(StringBuilder text) {
        this.text = text;
        template = new Template();
    }

    private int isTeg(String tag) {
        StringBuilder tagRes = template.get(tag);
        if (tagRes.length() > 0) {
            return 2;
        }

        tagRes = template.get(tag.substring(0, 1));
        return (tagRes.length() == 0 ? 0 : 1);
    }

    private void pushChar(char c, StringBuilder str) {
        str.append(template.getC(c));
    }

    private String parser(String tag, int from) {
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
            String tagP = text.substring(i, Math.min(i + 2, text.length()));
            int flag = isTeg(tagP);
            tagP = tagP.substring(0, flag);

            if (tagP.equals(tag) && !tag.isBlank()) {
                str.append(template.getCloseTag(tag));
                index += flag;
                return template.getOpenTag(tag).append(str).toString();
            }
            if (flag > 0) {
                str.append(parser(tagP, i + flag));
                i = --index;
            } else {
                pushChar(text.charAt(i), str);
            }
        }

        return tag + str.toString();
    }

    public void toHtml(StringBuilder resultHtml) {
        resultHtml.append(parser("", 0));
    }
}
