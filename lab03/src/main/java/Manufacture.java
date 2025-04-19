import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Manufacture")
public class Manufacture {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "Name", nullable = false, length = 128)
    private String name;

    @Column(name = "Location")
    private String location;

    @Column(name = "Employee")
    private int employee;

    @OneToMany(mappedBy = "manufacture", cascade = CascadeType.ALL)
    private List<Phone> phones;

    // Default constructor
    public Manufacture() {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}