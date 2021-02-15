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

    @GetMapping("customers/{customerId}/pets")
    public List<PetDto> getAllPetsByCustomer(@PathVariable UUID customerId){
        Customer customer = customerService.findById(customerId);
        List<Pet> pets = petService.findAllByCustomerId(customer);
        return petConverter.modelToDto(pets);
    }

    @GetMapping("customers/{customerId}/pets/{petId}")
    @PreAuthorize("hasRole('ADMIN')")
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

        petService.savePet(updatedPet);
    }

    @DeleteMapping("/customers/{customerId}/pets/{petId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deletePet(@PathVariable UUID petId){
        Pet pet = petService.findById(petId);
        petService.deletePet(pet);
    }
}
