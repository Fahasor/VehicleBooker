package vehbook.vehiclebooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vehbook.vehiclebooker.model.UserIdentity;
import vehbook.vehiclebooker.service.UserIdentityService;

@RestController
@RequestMapping("/users")
public class UserIdentityController {
    private final UserIdentityService userIdentityService;

    @Autowired
    public UserIdentityController (UserIdentityService service){
        this.userIdentityService = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody UserIdentity userIdentity) {
        userIdentityService.update(userIdentity);
    }
    @GetMapping
    public UserIdentity getByPhoneNumber(@RequestParam String phoneNumber) {
        return userIdentityService.findByPhoneNumber(phoneNumber);
    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@RequestParam Long id) {
        userIdentityService.deleteById(id);
    }
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserIdentity userIdentity) {
        userIdentityService.create(userIdentity);
    }
}
