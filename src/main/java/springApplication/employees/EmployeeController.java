package springApplication.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springApplication.Clinics.Clinic;
import springApplication.Clinics.ClinicRepository;

import java.util.List;
import java.util.UUID;

@RestController
@PreAuthorize("hasRole('ADMIN')")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeConverter employeeConverter;

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<EmployeeDto> getAllEmployees(){
        List<Employee> employees = employeeService.findAll();
        return employeeConverter.modelToDto(employees);
    }

    @GetMapping("/clinic/{clinicId}/employees")
    @PreAuthorize("hasRole('CLINIC') or hasRole('ADMIN')")
    public List<EmployeeDto> getAllEmployeesByClinic(@PathVariable UUID clinicId){
        List<Employee> employees = employeeService.findAllByClinic(clinicId);
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

    @PostMapping("/clinic/{clinicId}/employees")
    public void addEmployee(@RequestBody Employee employee, @PathVariable UUID clinicId){
        Clinic clinic = clinicRepository.findById(clinicId);
        employee.setClinic(clinic);
        employeeService.saveEmployee(employee);
    }

    @PutMapping("/employees/{employeeId}")
    public void updateEmployee(@RequestBody Employee employee){
        Employee updatedEmployee = employeeService.findById(employee.getId());

        updatedEmployee.setFirstName(employee.getFirstName());
        updatedEmployee.setLastName(employee.getLastName());
        updatedEmployee.setAge(employee.getAge());
        updatedEmployee.setAddress(employee.getAddress());
        updatedEmployee.setContractStartingDate(employee.getContractStartingDate());
        updatedEmployee.setContractEndingDate(employee.getContractEndingDate());
        updatedEmployee.setRole(employee.getRole());
        updatedEmployee.setSalary(employee.getSalary());
        updatedEmployee.setGender(employee.getGender());
        updatedEmployee.setEmail(employee.getEmail());

        employeeService.saveEmployee(updatedEmployee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable UUID employeeId){
        Employee employee = employeeService.findById(employeeId);
        employeeService.deleteEmployee(employee);
    }
}
