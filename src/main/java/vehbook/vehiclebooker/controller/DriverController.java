package vehbook.vehiclebooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import vehbook.vehiclebooker.model.Driver;
import vehbook.vehiclebooker.service.DriverService;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

  private final DriverService driverService;

  @Autowired
  public DriverController(DriverService service) {
    driverService = service;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody Driver driver) {
    driverService.create(driver);
  }

  @PostMapping("/bulk")
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody List<Driver> drivers) {
    driverService.create(drivers);
  }

  @GetMapping
  public Driver getByPhoneNumber(@RequestParam String phoneNumber) {
    return driverService.findByPhoneNumber(phoneNumber);
  }

  @GetMapping("/all")
  public List<Driver> getAll() {
    return driverService.getAll();
  }

  @PutMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@RequestBody Driver driver) {
    driverService.update(driver);
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@RequestParam Long id) {
    driverService.deleteById(id);
  }
}
