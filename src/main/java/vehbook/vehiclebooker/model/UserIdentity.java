package vehbook.vehiclebooker.model;

import jakarta.persistence.*;
import lombok.*;

@Table
@Entity
@Data
@NoArgsConstructor
public class UserIdentity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String phoneNumber;
}