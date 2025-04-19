package ex02.Service;

import ex02.Model.Student;

public interface Services<T> {
    public Iterable<T> getAllStudents();

    public Student getStudent(long id) throws Exception;

    public void deleteStudent(long id);

    public Student save(Student T);
}
