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
            new Paragraph(paragraph).toHtml(resultHtml);
        }
    }
}
