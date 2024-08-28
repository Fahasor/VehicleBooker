package vehbook.vehiclebooker.service;

import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
  public DriverService(DriverRepository driverRepository, DriverCache<String, Driver> driverCache) {
    this.driverRepository = driverRepository;
    this.cache = driverCache;
  }

  public void create(Driver driver) {
    driverRepository.save(driver);
  }

  public void create(List<Driver> drivers) {
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

  public Page<Driver> getPage(PageRequest request) {
    return driverRepository.findAll(request);
  }
}
