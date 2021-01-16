package springApplication.questions;

import lombok.Getter;
import lombok.Setter;
import springApplication.customers.Customer;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class QuestionDto {
    private UUID id;
    private Date date;
    private boolean solved;
    private String text;
    private String author;
    private boolean seen;
    private String response;
    private Customer customer;
}
