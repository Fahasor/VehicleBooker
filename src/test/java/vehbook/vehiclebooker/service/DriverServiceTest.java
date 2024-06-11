package vehbook.vehiclebooker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import vehbook.vehiclebooker.cache.DriverCache;
import vehbook.vehiclebooker.model.Driver;
import vehbook.vehiclebooker.repository.DriverRepository;

@ExtendWith(MockitoExtension.class)
class DriverServiceTest {

  @Mock private DriverRepository driverRepository;
  @Mock private DriverCache<String, Driver> cache;
  @InjectMocks private DriverService driverService;

  @Test
  void testCreate() {
    Driver driver = new Driver();
    driver.setId(1L);
    when(driverRepository.save(driver)).thenReturn(driver);

    driverService.create(driver);

    verify(driverRepository).save(driver);
  }

  @Test
  void testCreateBulk() {
    List<Driver> drivers = Arrays.asList(new Driver(), new Driver());
    when(driverRepository.saveAll(anyCollection())).thenReturn(drivers);

    driverService.create(drivers);

    verify(driverRepository).saveAll(drivers);
  }

  @Test
  void testFindByPhoneNumber_CacheHit() {
    String phoneNumber = "1234567890";
    Driver driver = new Driver();
    driver.setPhoneNumber(phoneNumber);

    when(cache.get(phoneNumber)).thenReturn(driver);

    Driver foundDriver = driverService.findByPhoneNumber(phoneNumber);

    assertEquals(driver, foundDriver);
    verify(driverRepository, times(0)).findByPhoneNumber(anyString());
  }

  @Test
  void testFindByPhoneNumber_CacheMiss() {
    String phoneNumber = "1234567890";
    Driver driver = new Driver();
    driver.setPhoneNumber(phoneNumber);
    driver.setId(1L);

    when(cache.get(phoneNumber)).thenReturn(null);
    when(driverRepository.findByPhoneNumber(phoneNumber)).thenReturn(Optional.of(driver));
    doNothing().when(cache).assign(anyString(), any());

    Driver foundDriver = driverService.findByPhoneNumber(phoneNumber);

    assertEquals(driver, foundDriver);
    verify(driverRepository).findByPhoneNumber(phoneNumber);
    verify(cache).assign(phoneNumber, driver);
  }

  @Test
  void testFindByPhoneNumber_NotFound() {
    String phoneNumber = "1234567890";
    when(cache.get(phoneNumber)).thenReturn(null);
    when(driverRepository.findByPhoneNumber(phoneNumber)).thenReturn(Optional.empty());

    assertThrows(
        NoSuchElementException.class,
        () -> driverService.findByPhoneNumber(phoneNumber),
        "No value present");
  }

  @Test
  void testUpdate() {
    Driver driver = new Driver();
    driver.setId(1L);
    driver.setPhoneNumber("1234567890");

    when(driverRepository.findById(1L)).thenReturn(Optional.of(new Driver()));
    when((driverRepository).save(driver)).thenReturn(driver);

    driverService.update(driver);

    verify(driverRepository).findById(1L);
    verify(driverRepository).save(driver);
    verify(cache).assign("1234567890", driver);
  }

  @Test
  void testDeleteById() {
    Driver driver = new Driver();
    driver.setId(1L);
    driver.setPhoneNumber("1234567890");
    when(driverRepository.findById(1L)).thenReturn(Optional.of(driver));
    doNothing().when(driverRepository).delete(any());
    doNothing().when(cache).wipe(anyString());

    driverService.deleteById(1L);

    verify(driverRepository).findById(1L);
    verify(driverRepository).delete(driver);
    verify(cache).wipe("1234567890");
  }

  @Test
  void testGetAll() {
    List<Driver> drivers = Arrays.asList(new Driver(), new Driver());

    when(driverRepository.findAll()).thenReturn(drivers);

    List<Driver> foundDrivers = driverService.getAll();

    assertEquals(drivers, foundDrivers);
  }

  @Test
  void testCache_AfterUpdate() {
    String phoneNumber = "1234567890";
    Driver driver = new Driver();
    driver.setId(1L);
    driver.setPhoneNumber(phoneNumber);

    when(driverRepository.findById(1L)).thenReturn(Optional.of(driver));
    when(driverRepository.save(driver)).thenReturn(driver);

    driverService.update(driver);

    verify(cache).assign(phoneNumber, driver);
  }

  @Test
  void testCache_AfterDelete() {
    String phoneNumber = "1234567890";
    Driver driver = new Driver();
    driver.setId(1L);
    driver.setPhoneNumber(phoneNumber);

    when(driverRepository.findById(1L)).thenReturn(Optional.of(driver));
    doNothing().when(driverRepository).delete(any());
    doNothing().when(cache).wipe(anyString());

    driverService.deleteById(1L);

    Driver cachedDriver = cache.get(phoneNumber);

    assertNull(cachedDriver);
  }
}