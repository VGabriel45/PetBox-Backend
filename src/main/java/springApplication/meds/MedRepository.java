package springApplication.meds;

import org.springframework.data.jpa.repository.JpaRepository;
import springApplication.pets.Pet;

import java.util.List;
import java.util.UUID;

public interface MedRepository extends JpaRepository<Medicament, UUID> {

    List<Medicament> findAllByPet(Pet pet);

}
