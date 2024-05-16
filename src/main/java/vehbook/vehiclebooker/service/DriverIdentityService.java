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

  /**
   * Finds driver information by specified phone number.
   * @param phoneNumber phone number to find by.
   * @return driver information with specified phoneNumber
   * @throws java.util.NoSuchElementException If element with specified phoneNumber is not present.
   */
  public DriverIdentity findByPhoneNumber(String phoneNumber) {
    DriverIdentity driverIdentity = cache.get(phoneNumber);

    if (driverIdentity != null) {
      return driverIdentity;
    }

    driverIdentity = driverIdentityRepository
        .findByPhoneNumber(phoneNumber)
        .orElseThrow();
    cache.assign(phoneNumber, driverIdentity);
    return driverIdentity;
  }

  /**
   * Set new information to existing entity.
   * @param driverIdentity Information to set.
   * @throws java.util.NoSuchElementException If element with specified id is not present.
   */
  public void update(DriverIdentity driverIdentity) {
    driverIdentityRepository.findById(driverIdentity.getId()).orElseThrow();

    driverIdentityRepository.save(driverIdentity);
    cache.assign(driverIdentity.getPhoneNumber(), driverIdentity);
  }

  /**
   * Removes existing entity by specified id.
   * @param id specified id.
   * @throws java.util.NoSuchElementException If element with specified id is not present.
   */
  public void deleteById(Long id) {
    DriverIdentity driverIdentity = driverIdentityRepository
        .findById(id)
        .orElseThrow();
    driverIdentityRepository.delete(driverIdentity);
    cache.wipe(driverIdentity.getPhoneNumber());
  }
}
