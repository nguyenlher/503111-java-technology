package ex02.Repository;

import ex02.Model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository <Student, Long> {
}
