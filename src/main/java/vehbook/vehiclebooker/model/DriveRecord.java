package vehbook.vehiclebooker.model;

import jakarta.persistence.*;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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