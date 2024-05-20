package vehbook.vehiclebooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vehbook.vehiclebooker.model.User;
import vehbook.vehiclebooker.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService service){
        this.userService = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody User user) {
        userService.create(user);
    }
    @GetMapping
    public User getByPhoneNumber(@RequestParam String phoneNumber) {
        return userService.findByPhoneNumber(phoneNumber);
    }
    @GetMapping("/all")
    public List<User> getAll() {
        return userService.getAll();
    }
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user) {
        userService.update(user);
    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@RequestParam Long id) {
        userService.deleteById(id);
    }
}
