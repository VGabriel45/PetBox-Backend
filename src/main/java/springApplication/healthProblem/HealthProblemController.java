package springApplication.healthProblem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springApplication.meds.Medicament;
import springApplication.pets.Pet;
import springApplication.pets.PetService;

import java.util.List;
import java.util.UUID;

@RestController
@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
@CrossOrigin(origins = "*")
public class HealthProblemController {

    @Autowired
    private HealthProblemRepository healthProblemRepository;

    @Autowired
    private PetService petService;

    @GetMapping("/healthProblems")
    public List<HealthProblem> getAllHealthProblems(){
        return healthProblemRepository.findAll();
    }

    @GetMapping("/healthProblems/pet/{petId}")
    public List<HealthProblem> getAllHealthProblemsByPet(@PathVariable UUID petId){
        Pet pet = petService.findById(petId);
        return healthProblemRepository.findAllByPet(pet);
    }

    @PostMapping("/healthProblems/pet/{petId}")
    public void addHealthProblem(@RequestBody HealthProblem healthProblem, @PathVariable UUID petId){
        Pet pet = petService.findById(petId);
        healthProblem.setPet(pet);
        healthProblemRepository.save(healthProblem);
    }

    @DeleteMapping("/healthProblem/{hpId}}")
    public void deleteHealthProblem(@PathVariable UUID hpId){
        healthProblemRepository.deleteById(hpId);
    }
}
