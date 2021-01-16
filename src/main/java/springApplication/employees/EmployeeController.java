package springApplication.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable UUID employeeId){
        return employeeService.findById(employeeId);
    }

    @PostMapping("/employees")
    public void addEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
    }

    @PutMapping("/employees/{employeeId}")
    public void updateEmployee(@RequestBody Employee employee){
        Employee updatedEmployee = employeeService.findById(employee.getId());

        employee.setFirstName(employee.getFirstName());
        employee.setLastName(employee.getLastName());
        employee.setAge(employee.getAge());
        employee.setAddress(employee.getAddress());
        employee.setContractStartingDate(employee.getContractStartingDate());
        employee.setContractEndingDate(employee.getContractEndingDate());
        employee.setRole(employee.getRole());
        employee.setSalary(employee.getSalary());
        employee.setGender(employee.getGender());

        employeeService.saveEmployee(updatedEmployee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable UUID employeeId){
        Employee employee = employeeService.findById(employeeId);
        employeeService.deleteEmployee(employee);
    }
}
