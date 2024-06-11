package vehbook.vehiclebooker.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DriveRecordUserLinksDto {
  private Long driverId;
  private List<Long> usersIds;
}
