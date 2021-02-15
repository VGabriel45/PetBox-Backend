package springApplication.Clinics;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springApplication.auth.models.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "clinics")
public class Clinic {
    @Id
    @GeneratedValue
    private UUID id;
    private String clinicName;
    private String clinicPassword;
    private String email;

    public Clinic(String clinicName, String password,String email) {
        this.clinicName = clinicName;
        this.clinicPassword = password;
        this.email = email;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "clinic_roles",
            joinColumns = @JoinColumn(name = "clinic_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
