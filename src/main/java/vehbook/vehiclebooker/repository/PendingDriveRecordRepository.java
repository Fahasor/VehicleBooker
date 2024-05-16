package vehbook.vehiclebooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vehbook.vehiclebooker.model.PendingDriveRecord;

import java.util.List;

@Repository
public interface PendingDriveRecordRepository
    extends JpaRepository<PendingDriveRecord, Long> {

  @Query("SELECT record "
      + "FROM PendingDriveRecord record "
      + "JOIN record.assignedUsers users "
      + "GROUP BY record "
      + "HAVING COUNT(users.id) > ?1")
  List<PendingDriveRecord> findAllWhereUsersMoreThan(int usersCount);
}
