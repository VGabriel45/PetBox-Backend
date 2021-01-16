package springApplication.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/")
    public String home(){
        return "Home page";
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @GetMapping("/customers/lastCustomer")
    public Customer getLastCustomer(){
//        System.out.println("Customer: " + customerRepository.findLastCustomer());
        return customerRepository.findLastCustomer();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable UUID customerId){
        return customerRepository.findById(customerId);
    }

    @PostMapping("/customers")
    public void addCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
    }

    @PutMapping("/customers/{personId}")
    public void updateCustomer(@RequestBody Customer customer){
        Customer updatedCustomer = customerRepository.findById(customer.getId());
        customer.setFirstName(customer.getFirstName());
        customer.setLastName(customer.getLastName());

        customerRepository.save(updatedCustomer);
    }

    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomer(@PathVariable UUID customerId){
        Customer customer = customerRepository.findById(customerId);
        customerRepository.delete(customer);
    }
}
