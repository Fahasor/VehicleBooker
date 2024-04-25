package vehbook.vehiclebooker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class DriverIdentity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(unique = true)
    String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL)
    Set<PendingDriveRecord> pendingDriveRecords;
}
