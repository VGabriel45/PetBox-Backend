package springApplication.appointments;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springApplication.Clinics.Clinic;
import springApplication.customers.Customer;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue
    private UUID id;
//    SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
//    private Date parsedDate;
//
//    {
//        try {
//            parsedDate = format.parse(getDateOfAppointment());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }

    private Date dateOfAppointment;
    private String hour;
    private boolean done = false;
    private boolean accepted = false;
    private boolean declined = false;
    private String reason;
    private boolean seen = false;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Clinic clinic;

}
