package ex05.Service;

import ex05.Model.Student;

import java.util.List;

public interface Services<T> {
    public Iterable<T> getAllStudents();

    public Student getStudent(long id) throws Exception;

    public void deleteStudent(long id);

    public Student save(T student);

    public List<Student> searchByAge(int age);

    public Integer countStudentsWithIeltsScore(double score);

    public List<Student> searchStudentsByName(String keyword);
}
