package vehbook.vehiclebooker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vehbook.vehiclebooker.model.User;
import vehbook.vehiclebooker.service.UserService;

@RestController
public class UserController {
    private final UserService userService = new UserService();
    @GetMapping("/users")
    public User getUser(@RequestParam Long id) {
        return userService.getUser(id);
    }
}
