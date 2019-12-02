package md2html;

public class ParagraphParser {
    private StringBuilder paragraph;

    public ParagraphParser(StringBuilder paragraph) {
        this.paragraph = paragraph;
    }

    private boolean isHeader() {
        int index = 0;
        while (index < paragraph.length() && paragraph.charAt(index) == '#') {
            index++;
        }
        return index != 0 && index < paragraph.length() && paragraph.charAt(index) == ' ';
    }

    public void toHtml(StringBuilder resultHtml) {
        if (isHeader()) {
            new HeaderParser(paragraph).toHtml(resultHtml);
        } else {
            paragraph(paragraph, resultHtml);
        }
    }

    private void paragraph(final StringBuilder paragraph, StringBuilder resultHtml) {
        resultHtml.append("<p>");
        new TextParser(paragraph).toHtml(resultHtml);
        resultHtml.append("</p>");
    }
}
