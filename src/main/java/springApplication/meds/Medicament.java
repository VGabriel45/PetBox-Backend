package springApplication.meds;

import lombok.Data;
import lombok.NoArgsConstructor;
import springApplication.pets.Pet;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "medicaments")
public class Medicament {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @ManyToOne
    private Pet pet;

}
