package springApplication.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeConverter employeeConverter;

    @GetMapping("/employees")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<EmployeeDto> getAllEmployees(){
        List<Employee> employees = employeeService.findAll();
        return employeeConverter.modelToDto(employees);
    }

    @GetMapping("/employees/{employeeId}")
    public EmployeeDto getEmployee(@PathVariable UUID employeeId) throws Exception {
        if (employeeService.findById(employeeId) == null){
            throw new Exception("User does not exist");
        } else {
            Employee employee = employeeService.findById(employeeId);
            return employeeConverter.modelToDto(employee);
        }
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
