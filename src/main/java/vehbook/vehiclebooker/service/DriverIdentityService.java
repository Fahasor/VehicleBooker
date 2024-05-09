package vehbook.vehiclebooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehbook.vehiclebooker.cache.DriverIdentityCache;
import vehbook.vehiclebooker.model.DriverIdentity;
import vehbook.vehiclebooker.repository.DriverIdentityRepository;

@Service
public class DriverIdentityService {
    private final DriverIdentityRepository driverIdentityRepository;
    private final DriverIdentityCache<String, DriverIdentity> cache;

    @Autowired
    public DriverIdentityService(DriverIdentityRepository driverIdentityRepository) {
        this.driverIdentityRepository = driverIdentityRepository;
        this.cache = new DriverIdentityCache<>();
    }

    public void create(DriverIdentity driverIdentity) {
        driverIdentityRepository.save(driverIdentity);
    }
    public DriverIdentity findByPhoneNumber(String phoneNumber) {
        DriverIdentity driverIdentity = cache.get(phoneNumber);

        if(driverIdentity != null)
            return driverIdentity;

        driverIdentity = driverIdentityRepository.
            findByPhoneNumber(phoneNumber).
            orElseThrow();
        cache.assign(phoneNumber, driverIdentity);
        return driverIdentity;
    }
    public void update(DriverIdentity driverIdentity) {
        driverIdentityRepository.findById(driverIdentity.getId()).orElseThrow();

        driverIdentityRepository.save(driverIdentity);
        cache.assign(driverIdentity.getPhoneNumber(), driverIdentity);
    }
    public void deleteById(Long id) {
        DriverIdentity driverIdentity = driverIdentityRepository.
            findById(id).
            orElseThrow();
        driverIdentityRepository.delete(driverIdentity);
        cache.wipe(driverIdentity.getPhoneNumber());
    }
}
