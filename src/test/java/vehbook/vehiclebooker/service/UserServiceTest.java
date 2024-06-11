package vehbook.vehiclebooker.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import vehbook.vehiclebooker.model.User;
import vehbook.vehiclebooker.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock private UserRepository userRepository;
  @InjectMocks private UserService userService;

  @Test
  void testCreate() {
    User user = new User();
    user.setId(1L);

    when(userRepository.save(user)).thenReturn(user);

    userService.create(user);

    verify(userRepository).save(user);
  }

  @Test
  void testCreateBulk() {
    List<User> users = Arrays.asList(new User(), new User());

    when(userRepository.saveAll(users)).thenReturn(users);

    userService.create(users);

    verify(userRepository).saveAll(users);
  }

  @Test
  void testFindByPhoneNumber() {
    String phoneNumber = "1234567890";
    User user = new User();
    user.setPhoneNumber(phoneNumber);
    user.setId(1L);

    when(userRepository.findByPhoneNumber(phoneNumber)).thenReturn(Optional.of(user));

    User foundUser = userService.findByPhoneNumber(phoneNumber);

    assertEquals(user, foundUser);
  }

  @Test
  void testFindByPhoneNumber_NotFound() {
    String phoneNumber = "1234567890";
    when(userRepository.findByPhoneNumber(phoneNumber)).thenReturn(Optional.empty());

    assertThrows(
        NoSuchElementException.class,
        () -> userService.findByPhoneNumber(phoneNumber),
        "No value present");
  }

  @Test
  void testGetAll() {
    List<User> users = Arrays.asList(new User(), new User());

    when(userRepository.findAll()).thenReturn(users);

    List<User> foundUsers = userService.getAll();

    assertEquals(users, foundUsers);
  }

  @Test
  void testUpdate() {
    User user = new User();
    user.setId(1L);

    when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
    when(userRepository.save(user)).thenReturn(user);

    userService.update(user);

    verify(userRepository).findById(1L);
    verify(userRepository).save(user);
  }

  @Test
  void testDeleteById() {
    User user = new User();
    user.setId(1L);
    when(userRepository.findById(1L)).thenReturn(Optional.of(user));
    doNothing().when(userRepository).delete(any());

    userService.deleteById(1L);

    verify(userRepository).findById(1L);
    verify(userRepository).delete(user);
  }
}