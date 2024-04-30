package vehbook.vehiclebooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehbook.vehiclebooker.model.PendingDriveRecord;
import vehbook.vehiclebooker.repository.PendingDriveRecordRepository;

import java.util.List;

@Service
public class PendingDriveRecordService {
    private final PendingDriveRecordRepository driveRecordRepository;

    @Autowired
    public PendingDriveRecordService(PendingDriveRecordRepository driveRecordRepository) {
        this.driveRecordRepository = driveRecordRepository;
    }

    public void create(PendingDriveRecord driverIdentity) {
        driveRecordRepository.save(driverIdentity);
    }
    public PendingDriveRecord findById(long id) {
        return driveRecordRepository.findById(id).orElseThrow();
    }
    public List<PendingDriveRecord> findAllWithUsersMoreThan(int usersNum) {
        return driveRecordRepository.findAllWhereUsersMoreThan(usersNum);
    }
    public void update(PendingDriveRecord pendingDriveRecord) {
        driveRecordRepository.findById(pendingDriveRecord.getId()).orElseThrow();
        driveRecordRepository.save(pendingDriveRecord);
    }
    public void deleteById(Long id) {
        driveRecordRepository.delete(driveRecordRepository.findById(id).orElseThrow());
    }
}
