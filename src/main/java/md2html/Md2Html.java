package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Md2Html {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("No input/output file");
            return;
        }
        String inputFile = args[0];
        String outputFile = args[1];

        BufferedReader input;
        BufferedWriter output;
        try {
            input = new BufferedReader(new InputStreamReader(new FileInputStream(new File(inputFile)), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            System.err.println("No input file specified" + e.getMessage());
            return;
        }

        StringBuilder paragraph = new StringBuilder();
        String str = "";
        StringBuilder resultHtml = new StringBuilder();
        try {
            while (str != null && (str = input.readLine()) != null) {
                while (str != null && !str.equals("")) {
                    paragraph.append(str);
                    paragraph.append('\n');
                    str = input.readLine();
                }
                if (paragraph.length() != 0) {
                    paragraph.setLength(paragraph.length() - 1);
                    new ParagraphParser(paragraph).toHtml(resultHtml);
                    resultHtml.append('\n');
                    paragraph.setLength(0);
                }
            }
        } catch (IOException e) {
            System.err.println("Read error" + e.getMessage());
        }

        try {
            output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outputFile)), StandardCharsets.UTF_8));
            output.write(resultHtml.toString());
            output.close();
        } catch (FileNotFoundException e) {
            System.err.println("No output file specified" + e.getMessage());
        } catch (IOException e) {
            System.err.println("Write error" + e.getMessage());
        }
    }
}
