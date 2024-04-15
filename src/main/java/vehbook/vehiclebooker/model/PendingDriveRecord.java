package vehbook.vehiclebooker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@NoArgsConstructor
public class PendingDriveRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
}
