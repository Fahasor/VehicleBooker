package vehbook.vehiclebooker.dto;

import java.util.Date;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriveRecordPostDto {
  private String driverPhoneNumber;
  private Date departureDate;
  private Set<String> usersPhoneNumbers;
}
