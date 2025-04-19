package lvpq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
@PropertySource("classpath:app.properties")
public class AppConfig {

    @Value("${name}")
    private String name;

    @Value("${id}")
    private String id;

    @Value("${price}")
    private String price;

    @Value("${description}")
    private String description;

    @Bean
    @Scope("prototype")
    public Product Product1(){
        Product product = new Product(
                Long.valueOf(this.id),
                String.valueOf(this.name),
                Double.valueOf(this.price),
                String.valueOf(this.description)
        );
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
