package vehbook.vehiclebooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vehbook.vehiclebooker.model.PendingDriveRecord;

@Repository
public interface PendingDriveRecordRepository extends JpaRepository<PendingDriveRecord, Long> {}
