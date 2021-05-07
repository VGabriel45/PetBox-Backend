package springApplication.employees;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springApplication.customers.Customer;
import springApplication.customers.CustomerDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeConverter {

    @Autowired
    private ModelMapper modelMapper;

    public EmployeeDto modelToDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public List<EmployeeDto> modelToDto(List<Employee> employees) {
        return employees.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    public Employee dtoToModel(EmployeeDto employeeDto) {
        return modelMapper.map(employeeDto, Employee.class);
    }

    public List<Employee> dtoToModel(List<EmployeeDto> employeeDtos) {
        return employeeDtos.stream().map(this::dtoToModel).collect(Collectors.toList());
    }

}
