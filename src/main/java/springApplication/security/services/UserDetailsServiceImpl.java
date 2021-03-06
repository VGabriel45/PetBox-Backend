package springApplication.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springApplication.Clinics.Clinic;
import springApplication.Clinics.ClinicRepository;
import springApplication.customers.Customer;
import springApplication.customers.CustomerRepository;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ClinicRepository clinicRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsernameIs(username);
        Clinic clinic = clinicRepository.findByClinicName(username);

        if (customer != null) {
            return UserDetailsImpl.build(customer);
        }
        return UserDetailsImpl.buildClinic(clinic);
    }

}
