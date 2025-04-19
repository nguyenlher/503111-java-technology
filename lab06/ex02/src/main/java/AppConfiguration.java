import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfiguration {
    @Bean
    @Scope("prototype")
    public Product Product1(){
        Product product = new Product(1L, "Iphone", 1000.0, "A great phone!");
        return product;
    }

    @Bean
    @Scope("prototype")
    public Product Product2(){
        return new Product(Product1());
    }

    @Bean
    @Scope("singleton")
    public Product Product3() {
        Product product = new Product(2L, "Xbox", 500.0, "A nice toy!");
        return product;
    }
}
