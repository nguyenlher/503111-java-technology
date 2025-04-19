package ex05.Repository;

import ex05.Model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends CrudRepository <Student, Long> {
    @Query("SELECT s FROM Student s WHERE s.age >= :age")
    public Collection<Student> searchByAge(@Param("age") Integer age);

    @Query("SELECT s FROM Student s WHERE s.ieltsScore = :ieltsScore")
    public List<Student> searchByIeltsScore(@Param("ieltsScore") Double ieltsScore);

    @Query("SELECT s FROM Student s WHERE s.name like %:keyword%")
    List<Student> searchByName(@Param("keyword") String keyword);
}
