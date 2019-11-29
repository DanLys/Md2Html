package md2html;

public class HeaderParser {
    private StringBuilder paragraph;

    public HeaderParser(StringBuilder paragraph) {
        this.paragraph = paragraph;
    }

    private int headCnt() {
        int index = 0;
        while (index < paragraph.length() && paragraph.charAt(index) == '#') {
            index++;
        }

        return index;
    }

    public void toHtml(StringBuilder resultHtml) {
        int cnt = headCnt();

        resultHtml.append("<h");
        resultHtml.append(cnt);
        resultHtml.append(">");

        StringBuilder text = new StringBuilder(paragraph.substring(cnt + 1));
        new TextParser(text).toHtml(resultHtml);

        resultHtml.append("</h");
        resultHtml.append(cnt);
        resultHtml.append(">");
    }
}
