package exercise.Lab08_02.service;

import exercise.Lab08_02.model.Employee;
import exercise.Lab08_02.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll()  { return employeeRepository.findAll(); }

    @Override
    public Employee getById(long id) throws Exception {
        return employeeRepository.findById(id).orElseThrow(() -> new Exception("Student not found"));
    }

    @Override
    public Employee addAndUpdate(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getCurrentEmployeePage(int startIndex, List<Employee> employees) {
        return employees.subList(startIndex, Math.min(startIndex + 4 /*Page Size*/, employees.size()));
    }
}
