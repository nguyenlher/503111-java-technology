import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PdfTextEditor implements TextWriter {

    @Override
    public void write(String filename, String text) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(filename));
        printWriter.println("Printing in pdf format: ");
        printWriter.print(text);
        printWriter.close();
    }
}
