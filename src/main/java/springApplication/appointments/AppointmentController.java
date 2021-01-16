package springApplication.appointments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import springApplication.customers.Customer;
import springApplication.customers.CustomerRepository;
import springApplication.pets.Pet;
import springApplication.pets.PetRepository;
import springApplication.questions.Question;

import java.util.List;
import java.util.UUID;

@RestController
public class AppointmentController {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("appointments")
    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }

    @GetMapping("customers/{customerId}/appointments")
    public List<Appointment> getAllAppointmentsByCustomer(@PathVariable UUID customerId){
        return appointmentRepository.findAllByCustomerId(customerId);
    }

    @GetMapping("customers/{customerId}/appointments/{appointmentId}")
    public Appointment getAppointment(@PathVariable UUID customerId, @PathVariable UUID appointmentId){
        Customer customer = customerRepository.findById(customerId);
        return appointmentRepository.findByCustomerAndAppointmentId(customer,appointmentId);
    }

    @GetMapping("/customers/{customerId}/appointments/{appointmentId}/setSeen")
    public void markAsSeen(@PathVariable UUID appointmentId){
        Appointment updatedAppointment = appointmentRepository.findById(appointmentId);
        System.out.println(updatedAppointment);
        updatedAppointment.setSeen(true);
        appointmentRepository.save(updatedAppointment);
    }

    @GetMapping("/appointments/seen")
    public int getNumberOfUnseenAppointments() {
        return appointmentRepository.getNumOfUnseenAppointments();
    }

    @GetMapping("/customers/{customerId}/appointments/{appointmentId}/accept")
    public void acceptAppointment(@PathVariable UUID appointmentId){
        Appointment updatedAppointment = appointmentRepository.findById(appointmentId);
        updatedAppointment.setAccepted(true);
        updatedAppointment.setDeclined(false);
        appointmentRepository.save(updatedAppointment);
    }

    @GetMapping("/customers/{customerId}/appointments/{appointmentId}/decline")
    public void declineAppointment(@PathVariable UUID appointmentId){
        Appointment updatedAppointment = appointmentRepository.findById(appointmentId);
        updatedAppointment.setDeclined(true);
        updatedAppointment.setAccepted(false);
        appointmentRepository.save(updatedAppointment);
    }
}
