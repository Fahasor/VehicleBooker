package vehbook.vehiclebooker.model;

import jakarta.persistence.*;
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