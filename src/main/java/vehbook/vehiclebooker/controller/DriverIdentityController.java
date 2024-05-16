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
import vehbook.vehiclebooker.model.DriverIdentity;
import vehbook.vehiclebooker.service.DriverIdentityService;

@RestController
@RequestMapping("/drivers")
public class DriverIdentityController {

  private final DriverIdentityService driverIdentityService;

  @Autowired
  public DriverIdentityController(DriverIdentityService service) {
    driverIdentityService = service;
  }

  @PutMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody DriverIdentity driverIdentity) {
    driverIdentityService.create(driverIdentity);
  }

  @GetMapping
  public DriverIdentity getByPhoneNumber(@RequestParam String phoneNumber) {
    return driverIdentityService.findByPhoneNumber(phoneNumber);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@RequestBody DriverIdentity driverIdentity) {
    driverIdentityService.update(driverIdentity);
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@RequestParam Long id) {
    driverIdentityService.deleteById(id);
  }
}
