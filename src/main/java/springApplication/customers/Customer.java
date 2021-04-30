package springApplication.customers;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springApplication.Clinics.Clinic;
import springApplication.auth.models.Role;
import springApplication.security.password.PasswordGenerator;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue
    private UUID id;
    @NotNull
    @Min(3)
    @Max(25)
    private String firstName;
    @NotNull
    @Min(3)
    @Max(25)
    private String lastName;
    @NotNull
    @Min(3)
    @Max(25)
    private String username;
    @NotNull
    @Min(12)
    @Max(50)
    private String email;
    @GeneratedValue
    @NotNull
    @Min(5)
    @Max(25)
    private String password;
    @NotNull
    private String address;
    private String phoneNumber;
    private String gender;
    private Integer age;
    private byte[] image;
    private Date lastSeen = new Date();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Customer(String firstName, String lastName, String username, String email, String password, String address, String phoneNumber, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;

    }

    @ManyToOne
    private Clinic clinic;

}

