package lvpq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
@Data
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
