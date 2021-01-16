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

    @Autowired
    private CustomerConverter customerConverter;

    @GetMapping("/customers")
    public List<CustomerDto> getAllCustomers(){
        List<Customer> customers = customerService.findAll();
        return customerConverter.modelToDto(customers);
    }

    @GetMapping("/customers/lastCustomer")
    public CustomerDto getLastCustomer(){
        Customer customer = customerService.findLastCustomer();
        return customerConverter.modelToDto(customer);
    }

    @GetMapping("/customers/{customerId}")
    public CustomerDto getCustomer(@PathVariable UUID customerId){
        Customer customer = customerService.findById(customerId);
        return customerConverter.modelToDto(customer);
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
