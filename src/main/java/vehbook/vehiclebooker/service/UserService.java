package vehbook.vehiclebooker.service;

import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehbook.vehiclebooker.model.Driver;
import vehbook.vehiclebooker.model.User;
import vehbook.vehiclebooker.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void create(User user) {
    if(userRepository.findById(user.getId()).isPresent()) {
      throw new EntityExistsException("user with id: "
          + user.getId().toString()
          + " already exists in database.");
    }

    userRepository.save(user);
  }

  public void create(List<User> users) {
    users.stream()
        .forEach(
            (user) -> {
              if(this.userRepository.findById(user.getId()).isPresent()) {
                throw new EntityExistsException("User with id: "
                    + user.getId().toString()
                    + " already exists in database.");
              }
            });

    userRepository.saveAll(users);
  }


  public User findByPhoneNumber(String phoneNumber) {
    return userRepository.findByPhoneNumber(phoneNumber).orElseThrow();
  }

  public List<User> getAll() {
    return userRepository.findAll();
  }

  public void update(User user) {
    userRepository.findById(user.getId()).orElseThrow();
    userRepository.save(user);
  }

  public void deleteById(Long id) {
    userRepository.delete(userRepository.findById(id).orElseThrow());
  }
}
