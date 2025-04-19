import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public TextWriter plainTextWriter() {
        return new PlainTextWriter();
    }
    @Bean
    public TextWriter pdfTextWriter() {
        return  new PdfTextEditor();
    }
    @Bean
    public TextEditor textEditor() {
        return new TextEditor();
    }
}
