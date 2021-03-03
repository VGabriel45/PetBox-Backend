package springApplication.awsS3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springApplication.customers.Customer;
import springApplication.customers.CustomerRepository;
import springApplication.pets.Pet;
import springApplication.pets.PetRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class S3StorageController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;

    @PostMapping("/upload/company/{customerName}")
    public ResponseEntity<String> uploadCustomerPhoto(@RequestParam(value = "file") MultipartFile file,
                                                     @PathVariable String customerName) {
        service.uploadFile(file,customerName);
        Customer customer = customerRepository.findByName(customerName);
        customer.setImage(service.downloadFile(customerName));
        customerRepository.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/upload/user/{petName}")
    public ResponseEntity<String> uploadPetPhoto(@RequestParam(value = "file") MultipartFile file,
                                                  @PathVariable String petName) {
        service.uploadFile(file,petName);
        Pet pet = petRepository.findPetByName(petName);
        pet.setImage(service.downloadFile(petName));
        petRepository.save(pet);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
