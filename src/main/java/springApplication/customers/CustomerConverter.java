package springApplication.customers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerConverter {

    @Autowired
    private ModelMapper modelMapper;

    public CustomerDto modelToDto(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }

    public List<CustomerDto> modelToDto(List<Customer> customers) {
        return customers.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    public Customer dtoToModel(CustomerDto customerDto) {
        return modelMapper.map(customerDto, Customer.class);
    }

    public List<Customer> dtoToModel(List<CustomerDto> customerDtos) {
        return customerDtos.stream().map(this::dtoToModel).collect(Collectors.toList());
    }
}
