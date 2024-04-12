package vehbook.vehiclebooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vehbook.vehiclebooker.model.UserIdentity;
import vehbook.vehiclebooker.service.UserIdentityService;

import java.util.List;
import java.util.NoSuchElementException;

@RequestMapping("/users")
@RestController
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
    public UserIdentity getUserIdentityByPhoneNumber(@RequestParam String phoneNumber) {
        return userIdentityService.getUserIdentity(phoneNumber);
    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserIdentityById(@RequestParam Long id) {
        userIdentityService.deleteById(id);
    }
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserIdentity userIdentity) {
        userIdentityService.createUserIdentity(userIdentity);
    }
}
