package ex06.Service;

import ex06.Model.Student;

import java.util.List;

public interface Services<T> {
    public Iterable<T> getAllStudents();
    public Iterable<T> getCustomizedStudentList();

    public Student save(T object);
}
