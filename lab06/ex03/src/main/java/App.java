import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class App
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        TextEditor textEditor = (TextEditor) context.getBean("textEditor");
        textEditor.input("Hello LVPThread!!");
        try {
            textEditor.save("output.txt");
        } catch (IOException e) {
            System.out.print(e.getStackTrace());
        }
    }
}
