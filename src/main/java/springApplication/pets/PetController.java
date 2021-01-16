package springApplication.pets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springApplication.customers.Customer;
import springApplication.customers.CustomerRepository;

import java.util.List;
import java.util.UUID;

@RestController
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("pets")
    public List<Pet> getAllPets(){
        return petRepository.findAll();
    }

    @GetMapping("customers/{customerId}/pets")
    public List<Pet> getAllPetsByPerson(@PathVariable UUID customerId){
        return petRepository.findAllByCustomerId(customerId);
    }

    @GetMapping("customers/{customerId}/pets/{petId}")
    public Pet getPet(@PathVariable UUID petId, @PathVariable UUID customerId){
        Customer customer = customerRepository.findById(customerId);
        return petRepository.findByCustomerAndPetId(customer,petId);
    }

    @PostMapping("customers/{customerId}/pets")
    public void addPet(@RequestBody Pet pet, @PathVariable UUID customerId){
        pet.setCustomer(customerRepository.findById(customerId));
        petRepository.save(pet);
    }

    @PutMapping("/customers/{customerId}/pets/{petId}")
    public void updatePet(@PathVariable UUID petId, @RequestBody Pet pet){
        Pet updatedPet = petRepository.findById(petId);
        updatedPet.setName(pet.getName());
        updatedPet.setRace(pet.getRace());
        updatedPet.setCustomer(pet.getCustomer());
        updatedPet.setGender(pet.getGender());

        petRepository.save(updatedPet);
    }

    @DeleteMapping("/customers/{customerId}/pets/{petId}")
    public void deletePet(@PathVariable UUID petId){
        Pet pet = petRepository.findById(petId);
        petRepository.delete(pet);
    }
}
