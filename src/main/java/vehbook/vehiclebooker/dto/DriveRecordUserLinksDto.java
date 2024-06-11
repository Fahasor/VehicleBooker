package vehbook.vehiclebooker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriveRecordUserLinksDto {
  private Long driverId;
  private List<Long> usersIds;
}
