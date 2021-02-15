package springApplication.questions;

import lombok.Data;
import lombok.NoArgsConstructor;
import springApplication.Clinics.Clinic;
import springApplication.customers.Customer;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue
    private UUID id;
    private Date date;
    private boolean solved;
    private String text;
    private String author;
    private boolean seen;
    private String response;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Clinic clinic;

}
