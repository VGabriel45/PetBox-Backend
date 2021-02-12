package springApplication.pets;

import lombok.Data;
import lombok.NoArgsConstructor;
import springApplication.customers.Customer;

import javax.persistence.*;
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

    @ManyToOne
    private Customer customer;

}
