package vehbook.vehiclebooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vehbook.vehiclebooker.model.DriverIdentity;

import java.util.Optional;

@Repository
public interface DriverIdentityRepository extends JpaRepository<DriverIdentity, Long> {
    Optional<DriverIdentity> findByPhoneNumber(String phoneNumber);
}
