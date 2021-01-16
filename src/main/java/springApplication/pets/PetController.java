package springApplication.pets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springApplication.customers.Customer;
import springApplication.customers.CustomerRepository;
import springApplication.customers.CustomerService;

import java.util.List;
import java.util.UUID;

@RestController
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PetConverter petConverter;

    @GetMapping("pets")
    public List<PetDto> getAllPets(){
        List<Pet> pets = petService.findAll();
        return petConverter.modelToDto(pets);
    }

    @GetMapping("customers/{customerId}/pets")
    public List<PetDto> getAllPetsByPerson(@PathVariable UUID customerId){
        Customer customer = customerService.findById(customerId);
        List<Pet> pets = petService.findAllByCustomerId(customer);
        return petConverter.modelToDto(pets);
    }

    @GetMapping("customers/{customerId}/pets/{petId}")
    public PetDto getPet(@PathVariable UUID petId){
        Pet pet = petService.findById(petId);
        return petConverter.modelToDto(pet);
    }

    @PostMapping("customers/{customerId}/pets")
    public void addPet(@RequestBody Pet pet, @PathVariable UUID customerId){
        pet.setCustomer(customerService.findById(customerId));
        petService.savePet(pet);
    }

    @PutMapping("/customers/{customerId}/pets/{petId}")
    public void updatePet(@PathVariable UUID petId, @RequestBody Pet pet){
        Pet updatedPet = petService.findById(petId);
        updatedPet.setName(pet.getName());
        updatedPet.setRace(pet.getRace());
        updatedPet.setCustomer(pet.getCustomer());
        updatedPet.setGender(pet.getGender());

        petService.savePet(updatedPet);
    }

    @DeleteMapping("/customers/{customerId}/pets/{petId}")
    public void deletePet(@PathVariable UUID petId){
        Pet pet = petService.findById(petId);
        petService.deletePet(pet);
    }
}
