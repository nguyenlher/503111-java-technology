package ex04.Service;

import ex04.Model.Student;
import ex04.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements Services<Student> {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public Iterable<Student> getAllStudents() {return studentRepository.findAll();}

    @Override
    public Student getStudent(long id) throws Exception {
        return studentRepository.findById(id)
                .orElseThrow(() -> new Exception("Student not found"));
    }

    @Override
    public void deleteStudent(long id) { studentRepository.deleteById(id); }

    @Override
    public Student save(Student student) { return studentRepository.save(student); }

    @Override
    public List<Student> findStudentsWithAgeGreaterThanEqual(int age) {
        return studentRepository.findByAgeGreaterThanEqual(age);
    }

    @Override
    public Integer countStudentsWithIeltsScore(double score) {
        return studentRepository.findByIeltsScore(score).size();
    }

    @Override
    public List<Student> searchStudentsByName(String keyword) {
        return studentRepository.findByNameContaining(keyword);
    }
}