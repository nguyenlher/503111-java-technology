package exercise.Lab08_02.repository;

import exercise.Lab08_02.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> { }
