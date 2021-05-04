package springApplication.meds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springApplication.Clinics.Clinic;
import springApplication.pets.Pet;
import springApplication.pets.PetDto;
import springApplication.pets.PetService;

import java.util.List;
import java.util.UUID;

@RestController
@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
@CrossOrigin(origins = "*")
public class MedController {

    @Autowired
    private MedRepository medRepository;

    @Autowired
    private PetService petService;

    @GetMapping("/meds")
    public List<Medicament> getAllMeds(){
        List<Medicament> medicaments = medRepository.findAll();
        return medicaments;
    }

    @GetMapping("/meds/pet/{petId}")
    public List<Medicament> getAllMedsByPet(@PathVariable UUID petId){
        Pet pet = petService.findById(petId);
        List<Medicament> medicaments = medRepository.findAllByPet(pet);
        return medicaments;
    }

    @PostMapping("/meds/pet/{petId}")
    public void addMed(@RequestBody Medicament medicament, @PathVariable UUID petId){
        Pet pet = petService.findById(petId);
        medicament.setPet(pet);
        medRepository.save(medicament);
    }

    @DeleteMapping("/meds/pet/{petId}")
    public void deleteMed(@PathVariable UUID medId){
        medRepository.deleteById(medId);
    }

}
