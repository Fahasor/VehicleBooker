package vehbook.vehiclebooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vehbook.vehiclebooker.model.Driver;
import vehbook.vehiclebooker.service.DriverService;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService service){
        driverService = service;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Driver driver) {
        driverService.create(driver);
    }
    @GetMapping
    public Driver getByPhoneNumber(@RequestParam String phoneNumber) {
        return driverService.findByPhoneNumber(phoneNumber);
    }
    @PostMapping
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
