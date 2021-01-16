package springApplication.employees;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue
    private UUID id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Integer age;
    private String gender;
    private String role;
    private String address;
    private Date contractStartingDate;
    private Date contractEndingDate;
    private String salary;

}
