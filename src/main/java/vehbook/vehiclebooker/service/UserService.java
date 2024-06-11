package vehbook.vehiclebooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    userRepository.save(user);
  }

  public void create(List<User> users) {
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
