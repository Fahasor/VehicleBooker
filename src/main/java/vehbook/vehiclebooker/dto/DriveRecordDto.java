package vehbook.vehiclebooker.dto;

import java.util.Date;
import java.util.Set;
import lombok.Data;

@Data
public abstract class DriveRecordDto {
  private String driverPhoneNumber;
  private Date departureDate;
  private Set<String> usersPhoneNumbers;
}
