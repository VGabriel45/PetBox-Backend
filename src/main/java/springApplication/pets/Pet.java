package springApplication.pets;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import springApplication.Clinics.Clinic;
import springApplication.customers.Customer;
import springApplication.meds.Medicament;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String picture;
    private String type;
    private String gender;
    private String race;
    private String age;
    private String color;
    private Boolean hasVaccine = false;
    private Date dateOfLastVaccine;
    private Boolean isSick = false;
    private Boolean hadInjuries = false;
    private Boolean hasInjuries = false;
    private Boolean isAlergic = false;
    private Integer weight;
    private byte[] image;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Clinic clinic;

}
