package vehbook.vehiclebooker.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class PendingDriveRecord {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  long id;

  @ManyToMany(cascade = CascadeType.REFRESH)
  List<UserIdentity> assignedUsers;

  @ManyToOne(cascade = CascadeType.REFRESH)
  DriverIdentity driver;
}