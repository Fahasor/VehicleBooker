package vehbook.vehiclebooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vehbook.vehiclebooker.model.DriverIdentity;
import vehbook.vehiclebooker.service.DriverIdentityService;

@RestController
@RequestMapping("/drivers")
public class DriverIdentityController {
    private final DriverIdentityService driverIdentityService;

    @Autowired
    public DriverIdentityController (DriverIdentityService service){
        driverIdentityService = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody DriverIdentity driverIdentity) {
        driverIdentityService.update(driverIdentity);
    }
    @GetMapping
    public DriverIdentity getByPhoneNumber(@RequestParam String phoneNumber) {
        return driverIdentityService.findByPhoneNumber(phoneNumber);
    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@RequestParam Long id) {
        driverIdentityService.deleteById(id);
    }
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody DriverIdentity driverIdentity) {
        driverIdentityService.create(driverIdentity);
    }
}
