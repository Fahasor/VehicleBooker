package vehbook.vehiclebooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vehbook.vehiclebooker.model.DriveRecord;

@Repository
public interface DriveRecordRepository extends JpaRepository<DriveRecord, Long> {}
