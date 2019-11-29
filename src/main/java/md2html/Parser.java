package md2html;

public class Parser {
    private StringBuilder paragraph;

    public Parser(StringBuilder paragraph) {
        this.paragraph = paragraph;
    }

    public void toHtml(StringBuilder resultHtml) {
        resultHtml.append("<p>");
        new TextParser(paragraph).toHtml(resultHtml);
        resultHtml.append("</p>");
    }
}
