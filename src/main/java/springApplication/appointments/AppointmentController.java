package springApplication.appointments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springApplication.customers.Customer;
import springApplication.customers.CustomerRepository;
import springApplication.pets.Pet;
import springApplication.pets.PetRepository;
import springApplication.questions.Question;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
public class AppointmentController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentConverter appointmentConverter;

    @GetMapping("/appointments")
    public List<AppointmentDto> getAllAppointments(){
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return appointmentConverter.modelToDto(appointments);
    }

    @PostMapping("customers/{customerId}/appointments")
    public void addAppointment(@RequestBody Appointment appointment, @PathVariable UUID customerId){
        appointment.setCustomer(customerRepository.findById(customerId));
        appointmentService.saveAppointment(appointment);
    }

    @GetMapping("customers/{customerId}/appointments")
    public List<AppointmentDto> getAllAppointmentsByCustomer(@PathVariable UUID customerId){
        Customer customer = customerRepository.findById(customerId);
        List<Appointment> appointments = appointmentService.getAllAppointmentsByCustomer(customer);
        return appointmentConverter.modelToDto(appointments);
    }

    @GetMapping("customers/{customerId}/appointments/{appointmentId}")
    public AppointmentDto getAppointment(@PathVariable UUID appointmentId){
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        return appointmentConverter.modelToDto(appointment);
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

    @DeleteMapping("/customers/{customerId}/appointments/{appointmentId}")
    public void deleteAppointment(@PathVariable UUID customerId, @PathVariable UUID appointmentId){
        Appointment appointmentToDelete= appointmentService.findByCustomerAndAppointmentId(customerRepository.findById(customerId),appointmentId);
        appointmentService.deleteAppointment(appointmentToDelete);
    }
}
