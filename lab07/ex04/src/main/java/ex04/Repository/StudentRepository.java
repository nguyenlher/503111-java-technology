package ex04.Repository;

import ex04.Model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository <Student, Long> {
    public List<Student> findByAgeGreaterThanEqual(Integer age);

    public List<Student> findByIeltsScore(Double ieltsScore);

    List<Student> findByNameContaining(String keyword);
}
