package springApplication.appointments;

import lombok.Data;
import lombok.NoArgsConstructor;
import springApplication.customers.Customer;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue
    private UUID id;

    private Date dateOfAppointment;
    private boolean done = false;
    private boolean accepted = false;
    private boolean declined = false;
    private String reason;
    private boolean seen;

    @ManyToOne
    private Customer customer;

}
