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
import vehbook.vehiclebooker.model.Driver;
import vehbook.vehiclebooker.service.DriverService;

@WebMvcTest(DriverController.class)
class DriverControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private DriverService driverService;

  @Autowired private ObjectMapper objectMapper;

  @Test
  void testCreate() throws Exception {
    Driver driver = new Driver();
    mockMvc
        .perform(
            post("/drivers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(driver)))
        .andExpect(status().isCreated());
    verify(driverService).create(driver);
  }

  @Test
  void testCreateBulk() throws Exception {
    List<Driver> drivers = Collections.singletonList(new Driver());
    mockMvc
        .perform(
            post("/drivers/bulk")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(drivers)))
        .andExpect(status().isCreated());
    verify(driverService).create(drivers);
  }

  @Test
  void testGetByPhoneNumber() throws Exception {
    Driver driver = new Driver();
    String phoneNumber = "1234567890";
    when(driverService.findByPhoneNumber(phoneNumber)).thenReturn(driver);
    mockMvc
        .perform(get("/drivers").param("phoneNumber", phoneNumber))
        .andExpect(status().isOk());
    verify(driverService).findByPhoneNumber(phoneNumber);
  }

  @Test
  void testGetAll() throws Exception {
    List<Driver> drivers = Collections.singletonList(new Driver());
    when(driverService.getAll()).thenReturn(drivers);
    mockMvc.perform(get("/drivers/all")).andExpect(status().isOk());
    verify(driverService).getAll();
  }

  @Test
  void testUpdate() throws Exception {
    Driver driver = new Driver();
    mockMvc
        .perform(
            put("/drivers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(driver)))
        .andExpect(status().isNoContent());
    verify(driverService).update(driver);
  }

  @Test
  void testDeleteById() throws Exception {
    Long id = 1L;
    mockMvc.perform(delete("/drivers").param("id", id.toString())).andExpect(status().isNoContent());
    verify(driverService).deleteById(id);
  }
}