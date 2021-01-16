package springApplication.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springApplication.customers.Customer;
import springApplication.customers.CustomerRepository;

import java.util.List;
import java.util.UUID;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable UUID employeeId){
        return employeeRepository.findById(employeeId);
    }

    @PostMapping("/employees")
    public void addEmployee(@RequestBody Employee employee){
        employeeRepository.save(employee);
    }

    @PutMapping("/employees/{employeeId}")
    public void updateEmployee(@RequestBody Employee employee){
        Employee updatedEmployee = employeeRepository.findById(employee.getId());
        employee.setFirstName(employee.getFirstName());
        employee.setLastName(employee.getLastName());
        employee.setAge(employee.getAge());
        employee.setAddress(employee.getAddress());
        employee.setContractStartingDate(employee.getContractStartingDate());
        employee.setContractEndingDate(employee.getContractEndingDate());
        employee.setRole(employee.getRole());
        employee.setSalary(employee.getSalary());
        employee.setGender(employee.getGender());

        employeeRepository.save(updatedEmployee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable UUID employeeId){
        Employee employee = employeeRepository.findById(employeeId);
        employeeRepository.delete(employee);
    }
}
