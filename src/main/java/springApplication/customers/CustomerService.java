package springApplication.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springApplication.appointments.Appointment;
import springApplication.appointments.AppointmentRepository;
import springApplication.pets.Pet;
import springApplication.pets.PetRepository;
import springApplication.questions.Question;
import springApplication.questions.QuestionRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    QuestionRepository questionRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public List<Customer> findAllByClinic(UUID clinicId) {
        return customerRepository.findAllByClinicId(clinicId);
    }

    public Customer findLastCustomer() {
        return customerRepository.findLastCustomer();
    }

    public Customer findById(UUID customerId) {
        return customerRepository.findById(customerId);
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomer(Customer customer) {
        List<Appointment> appointments = appointmentRepository.findAllByCustomerIs(customer);
        List<Question> questions = questionRepository.findAllByCustomerIs(customer);
        List<Pet> pets = petRepository.findAllByCustomerIs(customer);
        appointmentRepository.deleteAll(appointments);
        petRepository.deleteAll(pets);
        questionRepository.deleteAll(questions);
        customerRepository.delete(customer);
    }
}
