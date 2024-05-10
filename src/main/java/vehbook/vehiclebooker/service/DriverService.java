package vehbook.vehiclebooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehbook.vehiclebooker.model.Driver;
import vehbook.vehiclebooker.repository.DriverRepository;

import java.util.List;

@Service
public class DriverService {
    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public void create(Driver driver) {
        driverRepository.save(driver);
    }
    public Driver findByPhoneNumber(String phoneNumber) {
        return driverRepository.findByPhoneNumber(phoneNumber).orElseThrow();
    }
    public List<Driver> getAll() {
        return driverRepository.findAll();
    }
    public void update(Driver driver) {
        driverRepository.findById(driver.getId()).orElseThrow();
        driverRepository.save(driver);
    }
    public void deleteById(Long id) {
        driverRepository.delete(driverRepository.findById(id).orElseThrow());
    }
}
