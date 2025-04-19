import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Setter @Getter
public class Product {
    private Long id;
    private String name;
    private Double price;
    private String description;

    public Product(Product product) {
        this.name = product.name;
        this.id = product.id;
        this.price = product.price;
        this.description = product.description;
    }

    public Product(Long id, String name, Double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
