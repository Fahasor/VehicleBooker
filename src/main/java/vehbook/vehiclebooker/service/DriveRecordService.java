package vehbook.vehiclebooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vehbook.vehiclebooker.model.DriveRecord;
import vehbook.vehiclebooker.repository.DriveRecordRepository;
import vehbook.vehiclebooker.repository.UserRepository;

import java.util.Collection;
import java.util.List;

@Service
public class DriveRecordService {

  private final DriveRecordRepository driveRecordRepository;
  private final UserRepository userRepository;

  @Autowired
  public DriveRecordService(DriveRecordRepository driveRecordRepository,
      UserRepository userRepository) {
    this.driveRecordRepository = driveRecordRepository;
    this.userRepository = userRepository;
  }

  public void create(DriveRecord driver) {
    driveRecordRepository.save(driver);
  }

  public DriveRecord findById(long id) {
    return driveRecordRepository.findById(id).orElseThrow();
  }

  public List<DriveRecord> findAllWithUsersMoreThan(int usersNum) {
    return driveRecordRepository.findAllWhereUsersMoreThan(usersNum);
  }

  public List<DriveRecord> getAll() {
    return driveRecordRepository.findAll();
  }

  public void update(DriveRecord driveRecord) {
    driveRecordRepository.findById(driveRecord.getId()).orElseThrow();
    driveRecordRepository.save(driveRecord);
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
