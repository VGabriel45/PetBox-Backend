package springApplication.employees;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class EmployeeDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Integer age;
    private String gender;
    private String role;
    private String address;
    private Date contractStartingDate;
    private Date contractEndingDate;
    private String salary;
}
