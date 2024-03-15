package vehbook.vehiclebooker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vehbook.vehiclebooker.model.User;
import vehbook.vehiclebooker.service.UserService;

@RestController
public class UserController {
    @GetMapping("/users")
    public User getUser(@RequestParam Long id) {
        return UserService.getUser(id);
    }
}
