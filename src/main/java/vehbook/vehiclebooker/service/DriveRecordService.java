package vehbook.vehiclebooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vehbook.vehiclebooker.model.DriveRecord;
import vehbook.vehiclebooker.repository.DriveRecordRepository;

import java.util.List;

@Service
public class DriveRecordService {
    private final DriveRecordRepository driveRecordRepository;

    @Autowired
    public DriveRecordService(DriveRecordRepository driveRecordRepository) {
        this.driveRecordRepository = driveRecordRepository;
    }

    public void create(DriveRecord driverIdentity) {
        driveRecordRepository.save(driverIdentity);
    }
    public DriveRecord findById(long id) {
        return driveRecordRepository.findById(id).orElseThrow();
    }
    public List<DriveRecord> getAll() {
        return driveRecordRepository.findAll();
    }
    public void update(DriveRecord record) {
        driveRecordRepository.findById(record.getId()).orElseThrow();
        driveRecordRepository.save(record);
    }
    public void deleteById(Long id) {
        driveRecordRepository.delete(driveRecordRepository.findById(id).orElseThrow());
    }
}