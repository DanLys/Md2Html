package md2html;

public class Md2Html {
    public static void main(String[] args) {
        Source source;
        try {
            source = new Source(args);
        } catch (NullPointerException e) {
            System.err.println("No 2 args" + e.getMessage());
            return;
        }

        StringBuilder paragraph = new StringBuilder();
        String str = "";
        StringBuilder resultHtml = new StringBuilder();

        while (str != null && (str = source.readLine()) != null) {
            while (str != null && !str.equals("")) {
                paragraph.append(str);
                paragraph.append('\n');
                str = source.readLine();
            }
            if (paragraph.length() != 0) {
                paragraph.setLength(paragraph.length() - 1);
                new ParagraphParser(paragraph).toHtml(resultHtml);
                resultHtml.append('\n');
                paragraph.setLength(0);
            }
        }

        source.write(resultHtml);
        source.close();
    }
}
