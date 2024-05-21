package vehbook.vehiclebooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vehbook.vehiclebooker.model.DriveRecord;

import java.util.List;

@Repository
public interface DriveRecordRepository extends JpaRepository<DriveRecord, Long> {
    @Query("SELECT record " +
            "FROM DriveRecord record " +
            "JOIN record.assignedUsers users " +
            "GROUP BY record " +
            "HAVING COUNT(users.id) > ?1")
    List<DriveRecord> findAllWhereUsersMoreThan(int usersCount);
}
