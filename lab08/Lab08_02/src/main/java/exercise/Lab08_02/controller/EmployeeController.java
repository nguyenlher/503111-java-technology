package exercise.Lab08_02.controller;

import exercise.Lab08_02.model.Employee;
import exercise.Lab08_02.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping(value = "/employees")
    String getEmployees(@RequestParam(name = "page", defaultValue = "1") int currentPage, Model model) {
        List<Employee> employees = employeeService.getAll();

        var pageSize = 4;
        var startIndex = (currentPage - 1) * pageSize;
        var currentEmployees = employeeService.getCurrentEmployeePage(startIndex, employees);
        var totalPages = (int) Math.ceil((double) employees.size() / pageSize);



        model.addAttribute("employees", currentEmployees);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);

        return "html/index";
    }

    @GetMapping(value = "/employees/add")
    String getAdd() { return "html/add"; }

    @PostMapping(value = "/employees/add")
    RedirectView postAdd(HttpServletRequest request) {
        Employee employee = new Employee(request.getParameter("name"),
                request.getParameter("email"),
                request.getParameter("address"),
                request.getParameter("phone"));
        employeeService.addAndUpdate(employee);
        return new RedirectView("/employees");
    }

    @PostMapping(value="/employees/delete")
    RedirectView deleteEmployee(@RequestParam("selectedIds") List<Long> selectedIds){
        for (Long id : selectedIds) {
            employeeService.delete(id);
        }
        return new RedirectView("/employees");
    }

    @GetMapping(value="/employees/edit/{id}")
    String getEdit(Model model, @PathVariable(value="id") Long id) throws Exception {
        Employee employee = employeeService.getById(id);
        model.addAttribute("employee", employee);
        return "html/edit";
    }

    @PostMapping(value="/employees/edit/{id}")
    RedirectView postEdit(HttpServletRequest request, @PathVariable(value="id") Long id) throws Exception {
        Employee employee = employeeService.getById(id);
        employee.setAddress(request.getParameter("address"));
        employee.setName(request.getParameter("name"));
        employee.setEmail(request.getParameter("email"));
        employee.setPhone(request.getParameter("phone"));
        employeeService.addAndUpdate(employee);
        return new RedirectView("/employees");
    }
}
