package vehbook.vehiclebooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehbook.vehiclebooker.model.DriverIdentity;
import vehbook.vehiclebooker.repository.DriverIdentityRepository;

@Service
public class DriverIdentityService {
    private final DriverIdentityRepository driverIdentityRepository;

    @Autowired
    public DriverIdentityService(DriverIdentityRepository driverIdentityRepository) {
        this.driverIdentityRepository = driverIdentityRepository;
    }

    public void create(DriverIdentity driverIdentity) {
        driverIdentityRepository.save(driverIdentity);
    }
    public DriverIdentity findByPhoneNumber(String phoneNumber) {
        return driverIdentityRepository.findByPhoneNumber(phoneNumber).orElseThrow();
    }
    public void update(DriverIdentity driverIdentity) {
        driverIdentityRepository.findById(driverIdentity.getId()).orElseThrow();
        driverIdentityRepository.save(driverIdentity);
    }
    public void deleteById(Long id) {
        driverIdentityRepository.delete(driverIdentityRepository.findById(id).orElseThrow());
    }
}
