package ex04.Service;

import ex04.Model.Student;

import java.util.List;

public interface Services<T> {
    public Iterable<T> getAllStudents();

    public Student getStudent(long id) throws Exception;

    public void deleteStudent(long id);

    public Student save(T student);

    public List<T> findStudentsWithAgeGreaterThanEqual(int age);

    public Integer countStudentsWithIeltsScore(double score);

    public List<T> searchStudentsByName(String keyword);
}
