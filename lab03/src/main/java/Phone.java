import javax.persistence.*;

@Entity
@Table(name = "MobilePhone")
public class Phone {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "Name", nullable = false, length = 128)
    private String name;

    @Column(name = "Price", nullable = false)
    private int price;

    @Column(name = "Color", nullable = false)
    private String color;

    @Column(name = "Country")
    private String country;

    @Column(name = "Quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "manufacture_id")
    private Manufacture manufacture;

    // Default constructor
    public Phone() {
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && name.length() >= 3 && name.length() <= 128) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name must be between 3 and 128 characters.");
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Manufacture getManufacture() {
        return manufacture;
    }

    public void setManufacture(Manufacture manufacture) {
        this.manufacture = manufacture;
    }
}