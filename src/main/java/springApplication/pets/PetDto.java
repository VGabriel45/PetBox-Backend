package springApplication.pets;

import lombok.Getter;
import lombok.Setter;
import springApplication.customers.Customer;

import java.util.UUID;

@Getter
@Setter
public class PetDto {
    private UUID id;
    private String name;
    private String type;
    private String picture;
    private String gender;
    private String race;
    private String age;
    private String color;
    private byte[] image;
    private Customer customer;
}
