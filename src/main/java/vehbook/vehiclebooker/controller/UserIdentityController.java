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
import vehbook.vehiclebooker.model.UserIdentity;
import vehbook.vehiclebooker.service.UserIdentityService;

@RestController
@RequestMapping("/users")
public class UserIdentityController {

  private final UserIdentityService userIdentityService;

  @Autowired
  public UserIdentityController(UserIdentityService service) {
    this.userIdentityService = service;
  }

  @PutMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody UserIdentity userIdentity) {
    userIdentityService.create(userIdentity);
  }

  @GetMapping
  public UserIdentity getByPhoneNumber(@RequestParam String phoneNumber) {
    return userIdentityService.findByPhoneNumber(phoneNumber);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@RequestBody UserIdentity userIdentity) {
    userIdentityService.update(userIdentity);
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@RequestParam Long id) {
    userIdentityService.deleteById(id);
  }
}
