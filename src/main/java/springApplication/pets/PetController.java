package springApplication.pets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springApplication.Clinics.Clinic;
import springApplication.Clinics.ClinicRepository;
import springApplication.customers.Customer;
import springApplication.customers.CustomerRepository;
import springApplication.customers.CustomerService;

import java.util.List;
import java.util.UUID;

@RestController
@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
@CrossOrigin(origins = "*")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private PetConverter petConverter;

    @GetMapping("pets")
    @PreAuthorize("hasRole('ADMIN')")
    public List<PetDto> getAllPets(){
        List<Pet> pets = petService.findAll();
        return petConverter.modelToDto(pets);
    }

    @GetMapping("clinic/{clinicId}/pets")
    public List<PetDto> getAllPetsByClinic(@PathVariable UUID clinicId){
        List<Pet> pets = petService.findAllByClinicId(clinicId);
        return petConverter.modelToDto(pets);
    }

    @GetMapping("customers/{customerId}/pets")
    public List<PetDto> getAllPetsByCustomer(@PathVariable UUID customerId){
        Customer customer = customerService.findById(customerId);
        List<Pet> pets = petService.findAllByCustomerId(customer);
        return petConverter.modelToDto(pets);
    }

    @GetMapping("customers/{customerId}/pets/{petId}")
    public PetDto getPet(@PathVariable UUID petId){
        Pet pet = petService.findById(petId);
        return petConverter.modelToDto(pet);
    }

    @PostMapping("/clinic/{clinicId}/customers/{customerId}/pets")
    @PreAuthorize("hasRole('ADMIN') and isAuthenticated()")
    public void addPet(@RequestBody Pet pet, @PathVariable UUID customerId, @PathVariable UUID clinicId){
        Clinic clinic = clinicRepository.findById(clinicId);
        pet.setClinic(clinic);
        pet.setCustomer(customerService.findById(customerId));
        petService.savePet(pet);
    }

    @PutMapping("/customers/{customerId}/pets/{petId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updatePet(@PathVariable UUID petId, @RequestBody Pet pet){
        Pet updatedPet = petService.findById(petId);
        updatedPet.setName(pet.getName());
        updatedPet.setRace(pet.getRace());
        updatedPet.setCustomer(pet.getCustomer());
        updatedPet.setGender(pet.getGender());
        updatedPet.setAge(pet.getAge());
        updatedPet.setColor(pet.getColor());
        updatedPet.setType(pet.getType());
        updatedPet.setWeight(pet.getWeight());

        petService.savePet(updatedPet);
    }

    @DeleteMapping("/customers/{customerId}/pets/{petId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deletePet(@PathVariable UUID petId){
        Pet pet = petService.findById(petId);
        petService.deletePet(pet);
    }

    @GetMapping("/customers/{customerId}/pets/{petId}/hasVaccine")
    public Boolean updatePetHasVaccine(@PathVariable UUID petId){
        Pet updatedPet = petService.findById(petId);
        if (updatedPet.getHasVaccine() == Boolean.TRUE){
            updatedPet.setHasVaccine(Boolean.FALSE);
        } else {
            updatedPet.setHasVaccine(Boolean.TRUE);
        }
        petService.savePet(updatedPet);
        return updatedPet.getHasVaccine();
    }

    @GetMapping("/customers/{customerId}/pets/{petId}/isSick")
    public Boolean updatePetIsSick(@PathVariable UUID petId){
        Pet updatedPet = petService.findById(petId);
        if (updatedPet.getIsSick() == Boolean.TRUE){
            updatedPet.setIsSick(Boolean.FALSE);
        } else {
            updatedPet.setIsSick(Boolean.TRUE);
        }
        petService.savePet(updatedPet);
        return updatedPet.getIsSick();
    }

    @GetMapping("/customers/{customerId}/pets/{petId}/isAlergic")
    public Boolean updatePetIsAlergic(@PathVariable UUID petId){
        Pet updatedPet = petService.findById(petId);
        if (updatedPet.getIsAlergic() == Boolean.TRUE){
            updatedPet.setIsAlergic(Boolean.FALSE);
        } else {
            updatedPet.setIsAlergic(Boolean.TRUE);
        }
        petService.savePet(updatedPet);
        return updatedPet.getIsAlergic();
    }

    @GetMapping("/customers/{customerId}/pets/{petId}/hasInjuries")
    public Boolean updatePetHasInjuries(@PathVariable UUID petId){
        Pet updatedPet = petService.findById(petId);
        if (updatedPet.getHasInjuries() == Boolean.TRUE){
            updatedPet.setHasInjuries(Boolean.FALSE);
        } else {
            updatedPet.setHasInjuries(Boolean.TRUE);
        }
        petService.savePet(updatedPet);
        return updatedPet.getHasInjuries();
    }

    @GetMapping("/customers/{customerId}/pets/{petId}/hadInjuries")
    public Boolean updatePetHadInjuries(@PathVariable UUID petId){
        Pet updatedPet = petService.findById(petId);
        if (updatedPet.getHadInjuries() == Boolean.TRUE){
            updatedPet.setHadInjuries(Boolean.FALSE);
        } else {
            updatedPet.setHadInjuries(Boolean.TRUE);
        }
        petService.savePet(updatedPet);
        return updatedPet.getHadInjuries();
    }
}
