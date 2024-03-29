package vehbook.vehiclebooker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserIdentity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String phoneNumber;
}