import java.io.*;
import java.nio.file.*;

public class TextFileExample {
    public static void main(String[] args) throws IOException {
        String fileName = "textFile.txt";

        // Write multiple lines
        String[] lines = {"Line 1", "Line 2", "Line 3"};
        Files.write(Paths.get(fileName), java.util.Arrays.asList(lines));

        // Read lines sequentially
        java.util.List<String> readLines = Files.readAllLines(Paths.get(fileName));
        for (String line : readLines) {
            System.out.println(line);
        }
    }
}