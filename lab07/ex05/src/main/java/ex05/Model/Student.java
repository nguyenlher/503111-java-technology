package ex05.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @Data @AllArgsConstructor
@Entity
public class Student {
    @Id
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Double ieltsScore;
}
