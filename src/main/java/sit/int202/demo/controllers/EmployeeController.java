package sit.int202.demo.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sit.int202.demo.entities.Employee;
import sit.int202.demo.services.EmployeeService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping("/all")
    public String allEmployees(ModelMap model) {
        List<Employee> employeeList = service.getAllEmployees();
        model.addAttribute("employees", employeeList);
        return "employees/employee_list";
    }

    @GetMapping("")
    public String getEmployeeById(@RequestParam int id, ModelMap model) {
        Employee employee = service.getEmployee(id);
        model.addAttribute("employee", employee);
        return "employees/employee_details";
    }

    @GetMapping("/create")
    public String createForm () {
        return "employees/create_form";
    }
    @PostMapping("/create")
    public void createEmployee(Employee employee, HttpServletResponse response) throws IOException {
        service.addEmployee(employee);
        response.sendRedirect("/employees/all");
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam int id, ModelMap model) {
        Employee employee = service.deleteEmployee(id);
        model.addAttribute("employee", employee);
        model.addAttribute("message", "Office deleted successfully");
        return "employees/employee_details";
    }

    @GetMapping("/update")
    public String updateForm (@RequestParam int id, ModelMap model) {
        Employee employee = service.getEmployee(id);
        model.addAttribute("employee", employee);
        return "employees/update_form";
    }
    @PostMapping("/update")
    public void updateEmployee(Employee employee, HttpServletResponse response) throws IOException {
        service.updateEmployee(employee);
        response.sendRedirect("/employees/all");
    }
}
