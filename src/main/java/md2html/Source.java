package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Source {
    private BufferedReader input;
    private BufferedWriter output;

    public Source(String[] args) {
        try {
            input = new BufferedReader(new InputStreamReader(new FileInputStream(new File(args[0])), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't open input file" + e.getMessage());
        }

        try {
            output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(args[1])), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't open output file");
        }
    }

    public String readLine() {
        try {
            return input.readLine();
        } catch (IOException e) {
            System.err.println("Couldn't read line" + e.getMessage());
            return null;
        }
    }

    public void write(StringBuilder str) {
        try {
            output.write(str.toString());
        } catch (IOException e) {
            System.err.println("Couldn't write" + e.getMessage());
        }
    }

    public void close() {
        try {
            input.close();
            output.close();
        } catch (IOException e) {
            System.err.println("Couldn't close" + e.getMessage());
        }
    }
}
