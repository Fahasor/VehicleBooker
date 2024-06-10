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
import vehbook.vehiclebooker.model.Driver;
import vehbook.vehiclebooker.model.User;
import vehbook.vehiclebooker.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService service) {
    this.userService = service;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody User user) {
    userService.create(user);
  }

  @PostMapping("/bulk")
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody List<User> users) {
    userService.create(users);
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
