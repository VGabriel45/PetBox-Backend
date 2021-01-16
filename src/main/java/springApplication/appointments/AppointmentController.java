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
    private CustomerRepository customerRepository;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("appointments")
    public List<Appointment> getAllAppointments(){
        return appointmentService.getAllAppointments();
    }

    @GetMapping("customers/{customerId}/appointments")
    public List<Appointment> getAllAppointmentsByCustomer(@PathVariable UUID customerId){
        Customer customer = customerRepository.findById(customerId);
        return appointmentService.getAllAppointmentsByCustomer(customer);
    }

    @GetMapping("customers/{customerId}/appointments/{appointmentId}")
    public Appointment getAppointment(@PathVariable UUID appointmentId){
        return appointmentService.getAppointmentById(appointmentId);
    }

    @GetMapping("/customers/{customerId}/appointments/{appointmentId}/setSeen")
    public void markAsSeen(@PathVariable UUID appointmentId){
        Appointment updatedAppointment = appointmentService.getAppointmentById(appointmentId);
        updatedAppointment.setSeen(true);
        appointmentService.saveAppointment(updatedAppointment);
    }

    @GetMapping("/appointments/seen")
    public int getNumberOfUnseenAppointments() {
        return appointmentService.getNumOfUnseenAppointments();
    }

    @GetMapping("/customers/{customerId}/appointments/{appointmentId}/accept")
    public void acceptAppointment(@PathVariable UUID appointmentId){
        Appointment updatedAppointment = appointmentService.getAppointmentById(appointmentId);
        updatedAppointment.setAccepted(true);
        updatedAppointment.setDeclined(false);
        appointmentService.saveAppointment(updatedAppointment);
    }

    @GetMapping("/customers/{customerId}/appointments/{appointmentId}/decline")
    public void declineAppointment(@PathVariable UUID appointmentId){
        Appointment updatedAppointment = appointmentService.getAppointmentById(appointmentId);
        updatedAppointment.setDeclined(true);
        updatedAppointment.setAccepted(false);
        appointmentService.saveAppointment(updatedAppointment);
    }
}
