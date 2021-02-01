package springApplication.customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findById(UUID customerId);
    List<Customer> findAll();

    void deleteById(UUID customerId);

//    SELECT * FROM table
//    WHERE Dates IN (SELECT max(Dates) FROM table);

    @Query(
            value = "SELECT c FROM Customer c WHERE c.lastSeen in (SELECT max(lastSeen) FROM Customer)")
    Customer findLastCustomer();

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<Customer> findByUsername(String username);
}
