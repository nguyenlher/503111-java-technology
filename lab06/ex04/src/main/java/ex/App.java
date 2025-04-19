package ex;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Arrays;

@ComponentScan
@Configuration
public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(App.class);
        TextEditor textEditor = (TextEditor) applicationContext.getBean("textEditor");
        textEditor.input("Spring is coming!!");
        try {
            textEditor.save("spring.txt");
        } catch (IOException e) {
            System.out.print(Arrays.toString(e.getStackTrace()));
        }
    }
}

