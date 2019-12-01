package md2html;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Md2Html {
    public static void main(String[] args) {
        Source source;
        try {
            source = new Source(args);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            return;
        }

        StringBuilder paragraph = new StringBuilder();
        String str = "";
        StringBuilder resultHtml = new StringBuilder();
        try {
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
        } catch (IOException e) {
            System.err.println("Input error" + e.getMessage());
            return;
        }

        try {
            source.write(resultHtml);
        } catch (IOException e) {
            System.err.println("Couldn't write" + e.getMessage());
        } finally {
            source.close();
        }
    }
}
