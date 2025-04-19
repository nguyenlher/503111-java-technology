package ex06.Service;

import ex06.Model.Student;
import ex06.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class StudentService implements Services<Student> {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll(Sort.by("age").descending().and(Sort.by("ieltsScore")));
    }
    @Override
    public Iterable<Student> getCustomizedStudentList() {
        Pageable sortedByAgeDescIeltsAsc =
                PageRequest.of(0, 10, Sort.by("age").descending().and(Sort.by("ieltsScore")));
        Page<Student> studentPage = studentRepository.findAll(sortedByAgeDescIeltsAsc);
        return studentPage.getContent().subList(4,7);
    }
    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }
}