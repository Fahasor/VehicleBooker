package vehbook.vehiclebooker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriveRecordPutDto extends DriveRecordDto {
  private Long id;
}
