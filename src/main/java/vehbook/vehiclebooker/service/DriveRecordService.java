package vehbook.vehiclebooker.service;

import jakarta.transaction.Transactional;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehbook.vehiclebooker.model.DriveRecord;
import vehbook.vehiclebooker.repository.DriveRecordRepository;

import java.util.List;
import vehbook.vehiclebooker.repository.UserRepository;

@Service
public class DriveRecordService {
    private final DriveRecordRepository driveRecordRepository;
    private final UserRepository userRepository;

    @Autowired
    public DriveRecordService(DriveRecordRepository driveRecordRepository, UserRepository userRepository) {
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
    @Transactional
    public void addUsersToRecord(Long recordId, Collection<Long> usersIds) {
        DriveRecord record = driveRecordRepository.findById(recordId).orElseThrow();

        for(Long id : usersIds) {
            record.getAssignedUsers().add(userRepository.findById(id).orElseThrow());
        }
    }
    public void deleteById(Long id) {
        driveRecordRepository.delete(driveRecordRepository.findById(id).orElseThrow());
    }

    @Transactional
    public void deleteUsersFromRecord(Long recordId, Collection<Long> usersIds) {
        DriveRecord record = driveRecordRepository.findById(recordId).orElseThrow();
        record.getAssignedUsers().removeIf(user -> usersIds.contains(user.getId()));
    }
}
