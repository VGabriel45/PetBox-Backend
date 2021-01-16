package springApplication.employees;

import org.springframework.data.jpa.repository.JpaRepository;
import springApplication.customers.Customer;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Employee findById(UUID employeeId);

    void deleteById(UUID employeeId);
}
