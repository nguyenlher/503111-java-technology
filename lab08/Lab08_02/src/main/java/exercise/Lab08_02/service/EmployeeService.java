package exercise.Lab08_02.service;

import exercise.Lab08_02.model.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAll();

    public Employee getById(long id) throws Exception;

    public Employee addAndUpdate(Employee employee);

    public void delete(Long id);

    public List<Employee> getCurrentEmployeePage(int startIndex, List<Employee> employees);
}
