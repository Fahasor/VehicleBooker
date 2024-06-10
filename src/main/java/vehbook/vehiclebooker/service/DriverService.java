package vehbook.vehiclebooker.service;

import jakarta.persistence.EntityExistsException;
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
    if(driverRepository.findById(driver.getId()).isPresent()) {
      throw new EntityExistsException("Driver with id: "
          + driver.getId().toString()
          + " already exists in database.");
    }

    driverRepository.save(driver);
  }

  public void create(List<Driver> drivers) {
    drivers.stream()
        .forEach(
            (driver) -> {
              if(driverRepository.findById(driver.getId()).isPresent()) {
                throw new EntityExistsException("Driver with id: "
                    + driver.getId().toString()
                    + " already exists in database.");
              }
            });

    driverRepository.saveAll(drivers);
  }

  /**
   * Finds driver information by specified phone number.
   *
   * @param phoneNumber phone number to find by.
   * @return driver information with specified phoneNumber
   * @throws java.util.NoSuchElementException If element with specified phoneNumber is not present.
   */
  public Driver findByPhoneNumber(String phoneNumber) {
    Driver driver = cache.get(phoneNumber);

    if (driver != null) {
      return driver;
    }

    driver = driverRepository
        .findByPhoneNumber(phoneNumber)
        .orElseThrow();
    cache.assign(phoneNumber, driver);
    return driver;
  }

  /**
   * Set new information to existing entity.
   *
   * @param driver Information to set.
   * @throws java.util.NoSuchElementException If element with specified id is not present.
   */
  public void update(Driver driver) {
    driverRepository.findById(driver.getId()).orElseThrow();

    driverRepository.save(driver);
    cache.assign(driver.getPhoneNumber(), driver);
  }

  /**
   * Removes existing entity by specified id.
   *
   * @param id specified id.
   * @throws java.util.NoSuchElementException If element with specified id is not present.
   */
  public void deleteById(Long id) {
    Driver driver = driverRepository
        .findById(id)
        .orElseThrow();
    driverRepository.delete(driver);
    cache.wipe(driver.getPhoneNumber());
  }

  public List<Driver> getAll() {
    return driverRepository.findAll();
  }
}
