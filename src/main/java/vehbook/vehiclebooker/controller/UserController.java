package vehbook.vehiclebooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vehbook.vehiclebooker.model.User;
import vehbook.vehiclebooker.service.UserService;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public User getUser(@RequestParam Long id) {
        return userService.getUser(id);
    }
}
