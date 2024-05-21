package vehbook.vehiclebooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehbook.vehiclebooker.cache.DriverCache;
import vehbook.vehiclebooker.model.Driver;
import vehbook.vehiclebooker.repository.DriverRepository;

import java.util.List;

@Service
public class DriverService {
    private final DriverRepository driverRepository;
    private final DriverCache<String, Driver> cache;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
        this.cache = new DriverCache<>();
    }

    public void create(Driver driver) {
        driverRepository.save(driver);
    }
    public Driver findByPhoneNumber(String phoneNumber) {
        Driver driver = cache.get(phoneNumber);

        if(driver != null)
            return driver;

        driver = driverRepository.
            findByPhoneNumber(phoneNumber).
            orElseThrow();
        cache.assign(phoneNumber, driver);
        return driver;
    }
    public void update(Driver driver) {
        driverRepository.findById(driver.getId()).orElseThrow();

        driverRepository.save(driver);
        cache.assign(driver.getPhoneNumber(), driver);
    }
    public void deleteById(Long id) {
        Driver driver = driverRepository.
            findById(id).
            orElseThrow();
        driverRepository.delete(driver);
        cache.wipe(driver.getPhoneNumber());
    }
    public List<Driver> getAll() {
        return driverRepository.findAll();
    }
}
