package md2html;

public class Paragraph {
    private StringBuilder paragraph;

    public Paragraph(StringBuilder paragraph) {
        this.paragraph = paragraph;
    }

    public void toHtml(StringBuilder resultHtml) {
        resultHtml.append("<p>");
        new TextParser(paragraph).toHtml(resultHtml);
        resultHtml.append("</p>");
    }
}
