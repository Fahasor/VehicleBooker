package vehbook.vehiclebooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehbook.vehiclebooker.cache.Cache;
import vehbook.vehiclebooker.model.DriverIdentity;
import vehbook.vehiclebooker.repository.DriverIdentityRepository;

@Service
public class DriverIdentityService {
    private final DriverIdentityRepository driverIdentityService;
    private final Cache<String, DriverIdentity> cache;

    @Autowired
    public DriverIdentityService(DriverIdentityRepository driverIdentityRepository,
                                 Cache<String, DriverIdentity> cache) {
        this.driverIdentityService = driverIdentityRepository;
        this.cache = cache;
    }

    public void create(DriverIdentity driverIdentity) {
        driverIdentityService.save(driverIdentity);
    }
    public DriverIdentity findByPhoneNumber(String phoneNumber) {
        DriverIdentity driverIdentity = cache.get(phoneNumber);

        if(driverIdentity != null)
            return driverIdentity;

        driverIdentity = driverIdentityService.
            findByPhoneNumber(phoneNumber).
            orElseThrow();
        cache.assign(phoneNumber, driverIdentity);
        return driverIdentity;
    }
    public void update(DriverIdentity driverIdentity) {
        driverIdentityService.findById(driverIdentity.getId()).orElseThrow();

        driverIdentityService.save(driverIdentity);
        cache.assign(driverIdentity.getPhoneNumber(), driverIdentity);
    }
    public void deleteById(Long id) {
        DriverIdentity driverIdentity = driverIdentityService.
            findById(id).
            orElseThrow();
        driverIdentityService.delete(driverIdentity);
        cache.wipe(driverIdentity.getPhoneNumber());
    }
}
