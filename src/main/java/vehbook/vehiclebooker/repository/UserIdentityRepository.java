package vehbook.vehiclebooker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vehbook.vehiclebooker.model.UserIdentity;

import java.math.BigInteger;

@Repository
public interface UserIdentityRepository extends JpaRepository<UserIdentity, BigInteger> {
}
