package springApplication.pets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springApplication.customers.Customer;

import java.util.List;
import java.util.UUID;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;


    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public List<Pet> findAllByCustomerId(Customer customer) {
        return petRepository.findAllByCustomer(customer);
    }

    public Pet findById(UUID petId) {
        return petRepository.findById(petId);
    }

    public void savePet(Pet pet) {
        petRepository.save(pet);
    }

    public void deletePet(Pet pet) {
        petRepository.delete(pet);
    }
}
