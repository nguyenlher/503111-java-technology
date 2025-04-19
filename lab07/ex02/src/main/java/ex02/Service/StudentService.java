package ex02.Service;

import ex02.Model.Student;
import ex02.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}