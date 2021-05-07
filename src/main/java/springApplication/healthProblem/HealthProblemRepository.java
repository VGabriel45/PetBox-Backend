package springApplication.healthProblem;

import org.springframework.data.jpa.repository.JpaRepository;
import springApplication.pets.Pet;

import java.util.List;
import java.util.UUID;

public interface HealthProblemRepository extends JpaRepository<HealthProblem, UUID> {

    List<HealthProblem> findAllByPet(Pet pet);

}
