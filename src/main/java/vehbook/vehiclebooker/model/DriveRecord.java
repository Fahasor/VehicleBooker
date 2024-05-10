package vehbook.vehiclebooker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class DriveRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @ManyToMany(cascade = CascadeType.REFRESH)
    Set<User> assignedUsers;

    @ManyToOne(cascade =  CascadeType.REFRESH)
    Driver driver;
}