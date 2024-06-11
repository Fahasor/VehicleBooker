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
import vehbook.vehiclebooker.dto.DriveRecordUserLinksDto;
import vehbook.vehiclebooker.model.DriveRecord;
import vehbook.vehiclebooker.service.DriveRecordService;

@WebMvcTest(DriveRecordController.class)
class DriveRecordControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private DriveRecordService driveRecordService;

  @Autowired private ObjectMapper objectMapper;

  @Test
  void testCreate() throws Exception {
    DriveRecord driveRecord = new DriveRecord();
    mockMvc
        .perform(
            post("/pending")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(driveRecord)))
        .andExpect(status().isCreated());
    verify(driveRecordService).create(driveRecord);
  }

  @Test
  void testCreateBulk() throws Exception {
    List<DriveRecord> driveRecords = Collections.singletonList(new DriveRecord());
    mockMvc
        .perform(
            post("/pending/bulk")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(driveRecords)))
        .andExpect(status().isCreated());
    verify(driveRecordService).create(driveRecords);
  }

  @Test
  void testGetById() throws Exception {
    DriveRecord driveRecord = new DriveRecord();
    long id = 1L;
    when(driveRecordService.findById(id)).thenReturn(driveRecord);
    mockMvc
        .perform(get("/pending").param("id", String.valueOf(id)))
        .andExpect(status().isOk());
    verify(driveRecordService).findById(id);
  }

  @Test
  void testGetAllWithUsersMoreThan() throws Exception {
    List<DriveRecord> driveRecords = Collections.singletonList(new DriveRecord());
    int usersNum = 2;
    when(driveRecordService.findAllWithUsersMoreThan(usersNum)).thenReturn(driveRecords);
    mockMvc
        .perform(get("/pending/all").param("usersNum", String.valueOf(usersNum)))
        .andExpect(status().isOk());
    verify(driveRecordService).findAllWithUsersMoreThan(usersNum);
  }

  @Test
  void testUpdate() throws Exception {
    DriveRecord driveRecord = new DriveRecord();
    mockMvc
        .perform(
            put("/pending")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(driveRecord)))
        .andExpect(status().isNoContent());
    verify(driveRecordService).update(driveRecord);
  }

  @Test
  void testAddUsersToRecord() throws Exception {
    long id = 1L;
    List<Long> userIds = Collections.singletonList(2L);
    mockMvc
        .perform(
            put("/pending/users")
                .param("id", String.valueOf(id))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userIds)))
        .andExpect(status().isNoContent());
    verify(driveRecordService).addUsersToRecord(id, userIds);
  }

  @Test
  void testAddUsersToRecordBulk() throws Exception {
    List<DriveRecordUserLinksDto> connections =
        Collections.singletonList(new DriveRecordUserLinksDto(1L, Collections.singletonList(2L)));
    mockMvc
        .perform(
            put("/pending/users/bulk")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(connections)))
        .andExpect(status().isNoContent());
    verify(driveRecordService).addUsersToRecord(connections);
  }

  @Test
  void testDeleteById() throws Exception {
    Long id = 1L;
    mockMvc.perform(delete("/pending").param("id", id.toString())).andExpect(status().isNoContent());
    verify(driveRecordService).deleteById(id);
  }

  @Test
  void testDeleteUsersFromRecord() throws Exception {
    Long id = 1L;
    List<Long> userIds = Collections.singletonList(2L);
    mockMvc
        .perform(
            delete("/pending/users")
                .param("id", String.valueOf(id))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userIds)))
        .andExpect(status().isNoContent());
    verify(driveRecordService).deleteUsersFromRecord(id, userIds);
  }
}