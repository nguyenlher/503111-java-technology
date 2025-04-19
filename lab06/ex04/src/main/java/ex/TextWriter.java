package ex;

import java.io.IOException;

public interface TextWriter {
    public void write(String fileName, String text) throws IOException;
}
