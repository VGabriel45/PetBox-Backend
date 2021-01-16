package springApplication.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerService.findAll();
    }

    @GetMapping("/customers/lastCustomer")
    public Customer getLastCustomer(){
        return customerService.findLastCustomer();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable UUID customerId){
        return customerService.findById(customerId);
    }

    @PostMapping("/customers")
    public void addCustomer(@RequestBody Customer customer){
        customerService.saveCustomer(customer);
    }

    @PutMapping("/customers/{personId}")
    public void updateCustomer(@RequestBody Customer customer){
        Customer updatedCustomer = customerService.findById(customer.getId());
        customer.setFirstName(customer.getFirstName());
        customer.setLastName(customer.getLastName());

        customerService.saveCustomer(updatedCustomer);
    }

    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomer(@PathVariable UUID customerId){
        Customer customer = customerService.findById(customerId);
        customerService.deleteCustomer(customer);
    }
}
