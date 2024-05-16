package vehbook.vehiclebooker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class DriverIdentity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  long id;

  @Column(unique = true)
  String phoneNumber;
}
