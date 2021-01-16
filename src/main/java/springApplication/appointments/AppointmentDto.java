package springApplication.appointments;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import springApplication.customers.Customer;

import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class AppointmentDto {

    private UUID id;
    private Date dateOfAppointment;
    private boolean done = false;
    private boolean accepted = false;
    private boolean declined = false;
    private String reason;
    private boolean seen;
    private Customer customer;
}
