package springApplication.Clinics;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClinicRepository extends JpaRepository<Clinic, String> {
    boolean existsByClinicName(String clinicName);

    boolean existsByEmail(String email);

    Clinic findByClinicName(String name);

    Clinic findById(UUID id);
}
