package springApplication.healthProblem;

import lombok.Data;
import lombok.NoArgsConstructor;
import springApplication.pets.Pet;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "healthProblems")
public class HealthProblem {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @ManyToOne
    private Pet pet;
}
