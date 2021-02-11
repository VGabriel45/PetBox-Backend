package springApplication.pets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springApplication.customers.Customer;

import java.util.List;
import java.util.UUID;

public interface PetRepository extends JpaRepository<Pet, String> {
    Pet findById(UUID petId);
    List<Pet> findAll();

    void deleteById(UUID petId);

    List<Pet> findAllByCustomerId(UUID customerId);

    @Query(
            value = "SELECT p FROM Pet p WHERE p.customer = :customer AND p.id = :petId")
    Pet findByCustomerAndPetId(@Param("customer") Customer customer, @Param("petId") UUID petId);

    List<Pet> findAllByCustomer(Customer customer);

    List<Pet> findAllByCustomerIs(Customer customer);
}
