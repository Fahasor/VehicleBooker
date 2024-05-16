package vehbook.vehiclebooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import vehbook.vehiclebooker.model.PendingDriveRecord;
import vehbook.vehiclebooker.service.PendingDriveRecordService;

import java.util.List;

@RestController
@RequestMapping("/pending")
public class PendingDriveRecordController {

  private final PendingDriveRecordService driveRecordService;

  @Autowired
  public PendingDriveRecordController(PendingDriveRecordService service) {
    driveRecordService = service;
  }

  @PutMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody PendingDriveRecord pendingDriveRecord) {
    driveRecordService.create(pendingDriveRecord);
  }

  @GetMapping
  public PendingDriveRecord getById(@RequestParam long id) {
    return driveRecordService.findById(id);
  }

  @GetMapping("/all")
  public List<PendingDriveRecord> getAllWithUsersMoreThan(@RequestParam int usersNum) {
    return driveRecordService.findAllWithUsersMoreThan(usersNum);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@RequestBody PendingDriveRecord pendingDriveRecord) {
    driveRecordService.update(pendingDriveRecord);
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@RequestParam Long id) {
    driveRecordService.deleteById(id);
  }
}
