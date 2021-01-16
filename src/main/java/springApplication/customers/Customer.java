package springApplication.customers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springApplication.security.password.PasswordGenerator;

import javax.persistence.*;
import java.util.Date;
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
    @GeneratedValue
    private String password = getGeneratedPassword();
    private String address;
    private String phoneNumber;
    private String gender;
    private Integer age;
    private Date lastSeen = new Date();

    public String getGeneratedPassword(){
        PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
        return passwordGenerator.generate(8);
    }

}

