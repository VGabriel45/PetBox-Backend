package springApplication.appointments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import springApplication.customers.Customer;
import springApplication.questions.Question;

import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    Appointment findById(UUID appointmentId);

    List<Appointment> findAllByCustomerId(UUID customerId);

    @Query(
            value = "SELECT a FROM Appointment a WHERE a.customer = :customer AND a.id = :appointmentId")
    Appointment findByCustomerAndAppointmentId(Customer customer, UUID appointmentId);

    @Query(
            value = "SELECT COUNT(a) FROM Appointment a WHERE a.seen = false")
    int getNumOfUnseenAppointments();

    List<Appointment> findAllByCustomerIs(Customer customer);

    Appointment findAppointmentByCustomerIsAndIdIs(Customer customer, UUID appointmentId);

    void deleteAllByCustomerIs(Customer customer);
}
