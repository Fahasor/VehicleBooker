package vehbook.vehiclebooker.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import vehbook.vehiclebooker.dto.DriveRecordPostDto;
import vehbook.vehiclebooker.dto.DriveRecordUserLinksDto;
import vehbook.vehiclebooker.model.DriveRecord;
import vehbook.vehiclebooker.model.User;
import vehbook.vehiclebooker.repository.DriveRecordRepository;
import vehbook.vehiclebooker.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class DriveRecordServiceTest {

  @Mock private DriveRecordRepository driveRecordRepository;
  @Mock private UserRepository userRepository;
  @InjectMocks private DriveRecordService driveRecordService;

  @Test
  void testCreate() {
    DriveRecord driveRecord = new DriveRecordPostDto();

    driveRecordService.create(driveRecord);

    verify(driveRecordRepository).save(driveRecord);
  }

  @Test
  void testCreateBulk() {
    List<DriveRecord> driveRecords = Arrays.asList(new DriveRecord(), new DriveRecord());

    driveRecordService.create(driveRecords);

    verify(driveRecordRepository).saveAll(driveRecords);
  }

  @Test
  void testFindById() {
    DriveRecord driveRecord = new DriveRecord();
    driveRecord.setId(1L);

    when(driveRecordRepository.findById(1L)).thenReturn(Optional.of(driveRecord));

    DriveRecord foundDriveRecord = driveRecordService.findById(1L);

    assertEquals(driveRecord, foundDriveRecord);
  }

  @Test
  void testFindAllWithUsersMoreThan() {
    List<DriveRecord> driveRecords = Arrays.asList(new DriveRecord(), new DriveRecord());

    when(driveRecordRepository.findAllWhereUsersMoreThan(anyInt())).thenReturn(driveRecords);

    List<DriveRecord> foundDriveRecords = driveRecordService.findAllWithUsersMoreThan(2);

    assertEquals(driveRecords, foundDriveRecords);
  }

  @Test
  void testGetAll() {
    List<DriveRecord> driveRecords = Arrays.asList(new DriveRecord(), new DriveRecord());

    when(driveRecordRepository.findAll()).thenReturn(driveRecords);

    List<DriveRecord> foundDriveRecords = driveRecordService.getAll();

    assertEquals(driveRecords, foundDriveRecords);
  }

  @Test
  void testUpdate() {
    DriveRecord driveRecord = new DriveRecord();
    driveRecord.setId(1L);

    when(driveRecordRepository.findById(1L)).thenReturn(Optional.of(new DriveRecord()));

    driveRecordService.update(driveRecord);

    verify(driveRecordRepository).findById(1L);
    verify(driveRecordRepository).save(driveRecord);
  }

  @Test
  void testAddUsersToRecord_Bulk() {
    List<DriveRecordUserLinksDto> connections =
        Arrays.asList(
            new DriveRecordUserLinksDto(1L, Arrays.asList(2L, 3L)),
            new DriveRecordUserLinksDto(4L, Arrays.asList(5L, 6L)));
    User user1 = new User();
    user1.setId(2L);
    User user2 = new User();
    user2.setId(3L);
    User user3 = new User();
    user3.setId(5L);
    User user4 = new User();
    user4.setId(6L);

    when(driveRecordRepository.findById(1L)).thenReturn(Optional.of(
        new DriveRecord(
            1L,
            new HashSet<>(),
            null,
            null
        )
    ));

    when(driveRecordRepository.findById(4L)).thenReturn(Optional.of(
        new DriveRecord(
            4L,
            new HashSet<User>(),
            null,
            null
        )
    ));

    when(userRepository.findById(2L)).thenReturn(Optional.of(user1));
    when(userRepository.findById(3L)).thenReturn(Optional.of(user2));
    when(userRepository.findById(5L)).thenReturn(Optional.of(user3));
    when(userRepository.findById(6L)).thenReturn(Optional.of(user4));

    driveRecordService.addUsersToRecord(connections);

    verify(driveRecordRepository, times(2)).findById(anyLong());
    verify(userRepository, times(4)).findById(anyLong());
  }

  @Test
  void testAddUsersToRecord() {
    long recordId = 1L;
    List<Long> userIds = Arrays.asList(2L, 3L);
    User user1 = new User();
    user1.setId(2L);
    User user2 = new User();
    user2.setId(3L);

    when(driveRecordRepository.findById(recordId)).thenReturn(Optional.of(
        new DriveRecord(
            1L,
            new HashSet<>(),
            null,
            null
        )
    ));

    when(userRepository.findById(2L)).thenReturn(Optional.of(user1));
    when(userRepository.findById(3L)).thenReturn(Optional.of(user2));

    driveRecordService.addUsersToRecord(recordId, userIds);

    verify(driveRecordRepository).findById(recordId);
    verify(userRepository, times(2)).findById(anyLong());
  }

  @Test
  void testDeleteById() {
    DriveRecord driveRecord = new DriveRecord();
    driveRecord.setId(1L);
    when(driveRecordRepository.findById(1L)).thenReturn(Optional.of(driveRecord));
    doNothing().when(driveRecordRepository).delete(any());

    driveRecordService.deleteById(1L);

    verify(driveRecordRepository).findById(1L);
    verify(driveRecordRepository).delete(driveRecord);
  }

  @Test
  void testDeleteUsersFromRecord() {
    DriveRecord driveRecord = new DriveRecord();
    driveRecord.setId(1L);
    User user1 = new User();
    user1.setId(2L);
    User user2 = new User();
    user2.setId(3L);
    List<Long> userIds = Arrays.asList(2L, 3L);
    driveRecord.setAssignedUsers(new HashSet<>(Arrays.asList(user1, user2)));

    when(driveRecordRepository.findById(1L)).thenReturn(Optional.of(driveRecord));

    driveRecordService.deleteUsersFromRecord(1L, userIds);

    verify(driveRecordRepository).findById(1L);
    assertTrue(driveRecord.getAssignedUsers().isEmpty());
  }
}