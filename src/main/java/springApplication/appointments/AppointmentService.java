package springApplication.appointments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springApplication.App;
import springApplication.customers.Customer;

import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAllAppointmentsByCustomer(Customer customer){
        return appointmentRepository.findAllByCustomerIs(customer);
    }

    public Appointment findByCustomerAndAppointmentId(Customer customer, UUID appointmentId) {
        return appointmentRepository.findAppointmentByCustomerIsAndIdIs(customer,appointmentId);
    }

    public Appointment getAppointmentById(UUID appointmentId) {
        return appointmentRepository.findById(appointmentId);
    }

    public void saveAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public int getNumOfUnseenAppointments() {
        return appointmentRepository.getNumOfUnseenAppointments();
    }

    public void deleteAppointment(Appointment appointmentToDelete) {
        appointmentRepository.delete(appointmentToDelete);
    }

    public List<Appointment> getAllAppointmentsByClinicId(UUID clinicId) {
        return appointmentRepository.findAllByClinicId(clinicId);
    }
}
