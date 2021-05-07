package springApplication.Clinics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class ClinicController {

    @Autowired
    private ClinicRepository clinicRepository;

    @GetMapping("/clinics")
    public List<Clinic> getAllClinics(){
        return clinicRepository.findAll();
    }

    @GetMapping("/clinics/{clinicId}")
    public Clinic getClinicById(@PathVariable UUID clinicId){
        return clinicRepository.findById(clinicId);
    }

}
