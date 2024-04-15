package vehbook.vehiclebooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehbook.vehiclebooker.model.DriverIdentity;
import vehbook.vehiclebooker.repository.DriverIdentityRepository;

@Service
public class DriverIdentityService {
    private final DriverIdentityRepository driverIdentityService;

    @Autowired
    public DriverIdentityService(DriverIdentityRepository driverIdentityRepository) {
        this.driverIdentityService = driverIdentityRepository;
    }

    public void create(DriverIdentity driverIdentity) {
        driverIdentityService.save(driverIdentity);
    }
    public DriverIdentity findByPhoneNumber(String phoneNumber) {
        return driverIdentityService.findByPhoneNumber(phoneNumber).orElseThrow();
    }
    public void update(DriverIdentity driverIdentity) {
        driverIdentityService.findById(driverIdentity.getId()).orElseThrow();
        driverIdentityService.save(driverIdentity);
    }
    public void deleteById(Long id) {
        driverIdentityService.delete(driverIdentityService.findById(id).orElseThrow());
    }
}
