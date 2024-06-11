package vehbook.vehiclebooker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @Column(unique = true)
  private String phoneNumber;

  @Column
  private String surname;

  @Column
  private String name;

  @Column
  private String patronymic;
}
