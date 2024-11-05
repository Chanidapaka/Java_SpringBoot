package sit.int202.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int202.demo.entities.Employee;
import sit.int202.demo.entities.Office;
import sit.int202.demo.repositories.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    // READ (R)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    public Employee getEmployee(int employeeNumber){
        return employeeRepository.findById(employeeNumber).orElse(null);
    }
    // CREATE (C)
    public Employee addEmployee(Employee employee){
        if (employee.getId() == null || employeeRepository.existsById(employee.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Employee id '%s' already exists"
                            , employee.getId()));
        }
        return employeeRepository.save(employee);
    }
    // DELETE (D)
    public Employee deleteEmployee(int id) {
        Employee employee = getEmployee(id);
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Can not delete, Employee id '%s' does not exists"
                            , employee.getId()));
        }
        employeeRepository.deleteById(id);
        return employee;
    }
    // UPDATE (U) || !employeeRepository.existsById(employee.getId())
    public Employee updateEmployee(Employee employee){
        Employee employee_copy = getEmployee(employee.getId());
        if (employee_copy == null ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Can not update, Employee id '%s' does not exists"
                            ,employee.getId()));
        }
        return employeeRepository.save(employee);
    }

}
