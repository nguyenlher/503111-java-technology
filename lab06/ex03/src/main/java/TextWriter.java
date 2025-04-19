import java.io.IOException;


public interface TextWriter {
    public void write(String filename, String text) throws IOException;
}
