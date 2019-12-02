package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Md2Html {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("No 2 files");
            return;
        }

        StringBuilder resultHtml = new StringBuilder();
        try {
            BufferedReader input = new BufferedReader(
                                       new InputStreamReader(
                                           new FileInputStream(
                                               new File(args[0])), StandardCharsets.UTF_8)
            );

            try {
                StringBuilder paragraph = new StringBuilder();
                String str = "";

                while (str != null && (str = input.readLine()) != null) {
                    while (str != null && !str.isEmpty()) {
                        paragraph.append(str).append('\n');
                        str = input.readLine();
                    }

                    if (paragraph.length() != 0) {
                        paragraph.setLength(paragraph.length() - 1);
                        new ParagraphParser(paragraph).toHtml(resultHtml);
                        resultHtml.append('\n');
                        paragraph.setLength(0);
                    }
                }
            } finally {
                input.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't open file" + e.getMessage());
            return;
        } catch (UnsupportedEncodingException e) {
            System.err.println("UnsupportedEncoding in input" + e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println("Input error" + e.getMessage());
            return;
        }

        try {
            BufferedWriter output = new BufferedWriter(
                                        new OutputStreamWriter(
                                            new FileOutputStream(
                                                new File(args[1])), StandardCharsets.UTF_8)
            );
            try {
                output.write(resultHtml.toString());
            } finally {
                output.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't open output" + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.err.println("UnsupportedEncoding in output" + e.getMessage());
        } catch (IOException e) {
            System.err.println("Output error" + e.getMessage());
        }
    }
}
