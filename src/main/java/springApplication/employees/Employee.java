package springApplication.employees;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springApplication.Clinics.Clinic;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue
    private UUID id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Integer age;
    private String gender;
    private String role;
    private String address;
    private Date contractStartingDate;
    private Date contractEndingDate;
    private String salary;

    @ManyToOne
    private Clinic clinic;

}
