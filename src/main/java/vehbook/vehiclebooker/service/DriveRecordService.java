package vehbook.vehiclebooker.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vehbook.vehiclebooker.dto.DriveRecordDto;
import vehbook.vehiclebooker.dto.DriveRecordPostDto;
import vehbook.vehiclebooker.dto.DriveRecordPutDto;
import vehbook.vehiclebooker.dto.DriveRecordUserLinksDto;
import vehbook.vehiclebooker.model.DriveRecord;
import vehbook.vehiclebooker.model.User;
import vehbook.vehiclebooker.repository.DriveRecordRepository;
import vehbook.vehiclebooker.repository.DriverRepository;
import vehbook.vehiclebooker.repository.UserRepository;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class DriveRecordService {

  private final DriveRecordRepository driveRecordRepository;
  private final UserRepository userRepository;
  private final DriverRepository driverRepository;

  DriveRecord convert(DriveRecordDto driveRecordRawData) {
    DriveRecord driveRecord = new DriveRecord();
    driveRecord.setDepartureDate(driveRecordRawData.getDepartureDate());

    driveRecord.setDriver(
        driverRepository
            .findByPhoneNumber(
                driveRecordRawData
                    .getDriverPhoneNumber())
            .orElseThrow());

    Set<User> driveRecords = driveRecordRawData.getUsersPhoneNumbers().stream()
        .map(phoneNumber -> (userRepository.findByPhoneNumber(phoneNumber).orElseThrow()))
        .collect(Collectors.toSet());

    driveRecord.setAssignedUsers(driveRecords);

    return driveRecord;
  }

  public void create(DriveRecordPostDto driveRecordRawData) {
    driveRecordRepository.save(convert(driveRecordRawData));
  }

  public void create(List<DriveRecordPostDto> driveRecordsRawData) {
    driveRecordRepository.saveAll(driveRecordsRawData.stream().map(this::convert).toList());
  }

  public DriveRecord findById(long id) {
    return driveRecordRepository.findById(id).orElseThrow();
  }

  public List<DriveRecord> findAllWithUsersMoreThan(int usersNum) {
    return driveRecordRepository.findAllWhereUsersMoreThan(usersNum);
  }

  public Page<DriveRecord> getPage(PageRequest request) {
    return driveRecordRepository.findAll(request);
  }

  public void update(DriveRecordPutDto driveRecordRawData) {
    DriveRecord driveRecord = convert(driveRecordRawData);
    driveRecord.setId(driveRecordRawData.getId());
    driveRecordRepository.save(driveRecord);
  }

  public void update(Long id, String newDriverPhone) {
    DriveRecord driveRecord = driveRecordRepository.findById(id).orElseThrow();
    driveRecord.setDriver(driverRepository.findByPhoneNumber(newDriverPhone).orElseThrow());
    driveRecordRepository.save(driveRecord);
  }

  @Transactional
  public void addUsersToRecord(List<DriveRecordUserLinksDto> connections) {
    connections.stream()
        .forEach(
            (connection) -> {
              DriveRecord record = driveRecordRepository
                  .findById(connection.getDriveId())
                  .orElseThrow();

              connection.getUsersIds().forEach(
                  (id) -> {
                    record
                        .getAssignedUsers()
                        .add(
                            userRepository
                                .findById(id)
                                .orElseThrow());
                  });
            });
  }

  /**
   * Adds User entities with userIds ids to DriveRecord with recordId id.
   * @param recordId id of DriveRecord entity.
   * @param usersIds ids of User entities that will be added to DriveRecord entity.
   * @throws java.util.NoSuchElementException If one of id does not have associated entity.
   */
  @Transactional
  public void addUsersToRecord(Long recordId, Collection<Long> usersIds) {
    DriveRecord driveRecord = driveRecordRepository.findById(recordId).orElseThrow();

    for (Long id : usersIds) {
      driveRecord.getAssignedUsers().add(userRepository.findById(id).orElseThrow());
    }
  }

  public void deleteById(Long id) {
    driveRecordRepository.delete(driveRecordRepository.findById(id).orElseThrow());
  }

  @Transactional
  public void deleteUsersFromRecord(Long recordId, Collection<Long> usersIds) {
    DriveRecord driveRecord = driveRecordRepository.findById(recordId).orElseThrow();
    driveRecord.getAssignedUsers().removeIf(user -> usersIds.contains(user.getId()));
  }
}
