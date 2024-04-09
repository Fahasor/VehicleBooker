package vehbook.vehiclebooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vehbook.vehiclebooker.model.UserIdentity;
import vehbook.vehiclebooker.service.UserIdentityService;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserIdentityController {
    private final UserIdentityService userIdentityService;

    @Autowired
    public UserIdentityController (UserIdentityService service){
        this.userIdentityService = service;
    }

    @PostMapping
    public UserIdentity createUserIdentity(@RequestBody UserIdentity userIdentity) {
        return userIdentityService.createUserIdentity(userIdentity);
    }
    @GetMapping
    public UserIdentity getUserIdentityByPhoneNumber(@RequestParam String phoneNumber) {
        return userIdentityService.getUserIdentity(phoneNumber);
    }
}
