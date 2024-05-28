package vehbook.vehiclebooker.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class DriveRecord {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  long id;

  @ManyToMany(cascade = CascadeType.REFRESH)
  private Set<User> assignedUsers;

  @ManyToOne(cascade = CascadeType.REFRESH)
  private Driver driver;

  @Column
  private Date departureDate;
}