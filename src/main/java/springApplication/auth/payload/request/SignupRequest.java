package springApplication.auth.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springApplication.Clinics.Clinic;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    private String username;
    private String email;
    private String address;
    private String phoneNumber;
    private String gender;
    private String firstName;
    private String lastName;
    private String password;
    private List<String> roles;
    private Clinic clinic;
}
