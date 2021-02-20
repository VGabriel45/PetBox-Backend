package springApplication.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
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

    @GetMapping("/clinic/{clinicId}/customers")
    public List<CustomerDto> getAllCustomersByClinic(@PathVariable UUID clinicId){
        List<Customer> customers = customerService.findAllByClinic(clinicId);
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
        updatedCustomer.setFirstName(customer.getFirstName());
        updatedCustomer.setEmail(customer.getEmail());
        updatedCustomer.setUsername(customer.getUsername());
        updatedCustomer.setLastName(customer.getLastName());
        updatedCustomer.setAddress(customer.getAddress());
        updatedCustomer.setAge(customer.getAge());
        updatedCustomer.setPhoneNumber(customer.getPhoneNumber());
        updatedCustomer.setGender(customer.getGender());

        customerService.saveCustomer(updatedCustomer);
    }

    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomer(@PathVariable UUID customerId){
        Customer customer = customerService.findById(customerId);
        customerService.deleteCustomer(customer);
    }
}
