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
}
