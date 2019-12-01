package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Source {
    private BufferedReader input;
    private BufferedWriter output;

    public Source(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            throw new IllegalArgumentException("No 2 args");
        }
        try {
            input = new BufferedReader(new InputStreamReader(new FileInputStream(new File(args[0])), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Couldn't open input file" + e.getMessage());
        }

        try {
            output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(args[1])), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Couldn't open output file" + e.getMessage());
        }
    }

    public String readLine() throws IOException {
        return input.readLine();
    }

    public void write(StringBuilder str) throws IOException {
        output.write(str.toString());
    }

    public void close() {
        try {
            input.close();
        } catch (IOException e) {
            System.err.println("Couldn't close input" + e.getMessage());
        }
        try {
            output.close();
        } catch (IOException e) {
            System.err.println("Couldn't close output" + e.getMessage());
        }
    }
}
