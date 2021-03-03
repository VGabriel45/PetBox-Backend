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

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class S3StorageController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    private S3StorageService service;

    @PostMapping("/upload/customer/{customerName}")
    public ResponseEntity<String> uploadCustomerPhoto(@RequestParam(value = "file") MultipartFile file,
                                                     @PathVariable String customerName) {
        service.uploadFile(file,customerName);
        Customer customer = customerRepository.findByUsernameIs(customerName);
        customer.setImage(service.downloadFile(customerName));
        customerRepository.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/upload/pet/{petName}")
    public ResponseEntity<String> uploadPetPhoto(@RequestParam(value = "file") MultipartFile file,
                                                  @PathVariable String petName) {
        service.uploadFile(file,petName);
        Pet pet = petRepository.findPetByName(petName);
        pet.setImage(service.downloadFile(petName));
        petRepository.save(pet);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return new ResponseEntity<>(service.deleteFile(fileName), HttpStatus.OK);
    }

    @GetMapping("image/pet/{name}")
    public byte[] getPetImage(@PathVariable("name") String name) {
        Pet pet = petRepository.findPetByName(name);
        return pet.getImage();
    }

    @GetMapping("image/customer/{name}")
    public byte[] getCustomerImage(@PathVariable("name") String name) {
        Customer customer = customerRepository.findByUsernameIs(name);
        return customer.getImage();
    }

}