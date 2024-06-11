package vehbook.vehiclebooker.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import vehbook.vehiclebooker.model.User;
import vehbook.vehiclebooker.service.UserService;

@WebMvcTest(UserController.class)
class UserControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private UserService userService;

  @Autowired private ObjectMapper objectMapper;

  @Test
  void testCreate() throws Exception {
    User user = new User();
    mockMvc
        .perform(
            post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
        .andExpect(status().isCreated());
    verify(userService).create(user);
  }

  @Test
  void testCreateBulk() throws Exception {
    List<User> users = Collections.singletonList(new User());
    mockMvc
        .perform(
            post("/users/bulk")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(users)))
        .andExpect(status().isCreated());
    verify(userService).create(users);
  }

  @Test
  void testGetByPhoneNumber() throws Exception {
    User user = new User();
    String phoneNumber = "1234567890";
    when(userService.findByPhoneNumber(phoneNumber)).thenReturn(user);
    mockMvc
        .perform(get("/users").param("phoneNumber", phoneNumber))
        .andExpect(status().isOk());
    verify(userService).findByPhoneNumber(phoneNumber);
  }

  @Test
  void testGetAll() throws Exception {
    List<User> users = Collections.singletonList(new User());
    when(userService.getAll()).thenReturn(users);
    mockMvc.perform(get("/users/all")).andExpect(status().isOk());
    verify(userService).getAll();
  }

  @Test
  void testUpdate() throws Exception {
    User user = new User();
    mockMvc
        .perform(
            put("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
        .andExpect(status().isNoContent());
    verify(userService).update(user);
  }

  @Test
  void testDeleteById() throws Exception {
    Long id = 1L;
    mockMvc.perform(delete("/users").param("id", id.toString())).andExpect(status().isNoContent());
    verify(userService).deleteById(id);
  }
}