package springApplication.Clinics;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClinicRepository extends JpaRepository<Clinic, String> {
    boolean existsByClinicName(String clinicName);

    boolean existsByEmail(String email);

    Clinic findByClinicName(String name);
}
