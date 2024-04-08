package vehbook.vehiclebooker.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "user_identity")
@Entity
@Data
@NoArgsConstructor
public class UserIdentity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;
}