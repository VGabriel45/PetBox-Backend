package springApplication.customers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springApplication.auth.models.Role;
import springApplication.security.password.PasswordGenerator;

import javax.persistence.*;
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
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    @GeneratedValue
    private String password;
    private String address;
    private String phoneNumber;
    private String gender;
    private Integer age;
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

    //    public String getGeneratedPassword(){
//        PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
//                .useDigits(true)
//                .useLower(true)
//                .useUpper(true)
//                .build();
//        return passwordGenerator.generate(8);
//    }

}

