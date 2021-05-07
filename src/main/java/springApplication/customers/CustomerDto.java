package springApplication.customers;

import lombok.Getter;
import lombok.Setter;
import springApplication.Clinics.Clinic;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class CustomerDto {
    private UUID id;
    private String firstName;
    private String password;
    private String lastName;
    private String username;
    private String email;
    private String address;
    private String phoneNumber;
    private String gender;
    private Integer age;
    private Date lastSeen = new Date();
    private Clinic clinic;
    private Boolean agreed;
}
