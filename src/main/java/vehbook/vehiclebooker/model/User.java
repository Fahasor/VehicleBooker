package vehbook.vehiclebooker.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "users")
@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String phoneNumber;

    @Column
    private String surname;

    @Column
    private String name;

    @Column
    private String patronymic;
}