package vehbook.vehiclebooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import vehbook.vehiclebooker.dto.DriveRecordPostDto;
import vehbook.vehiclebooker.dto.DriveRecordPutDto;
import vehbook.vehiclebooker.dto.DriveRecordUserLinksDto;
import vehbook.vehiclebooker.model.DriveRecord;
import vehbook.vehiclebooker.service.DriveRecordService;

import java.util.List;

@RestController
@RequestMapping("/pending")
@CrossOrigin
public class DriveRecordController {

  private final DriveRecordService driveRecordService;

  @Autowired
  public DriveRecordController(DriveRecordService service) {
    driveRecordService = service;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody DriveRecordPostDto driveRecord) {
    driveRecordService.create(driveRecord);
  }

  @PostMapping("/bulk")
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody List<DriveRecordPostDto> driveRecords) {
    driveRecordService.create(driveRecords);
  }

  @GetMapping
  public DriveRecord getById(@RequestParam long id) {
    return driveRecordService.findById(id);
  }

  @GetMapping("/all")
  public Page<DriveRecord> getAll(
      @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
    return driveRecordService.getPage(PageRequest.of(pageNumber, pageSize));
  }

  @GetMapping("/all/usersMoreThen")
  public List<DriveRecord> getAllWithUsersMoreThan(@RequestParam int usersNum) {
    return driveRecordService.findAllWithUsersMoreThan(usersNum);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@RequestBody DriveRecordPutDto driveRecord) {
    driveRecordService.update(driveRecord);
  }

  @PutMapping("/users")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void addUsersToRecord(@RequestParam Long id, @RequestBody List<Long> userIds) {
    driveRecordService.addUsersToRecord(id, userIds);
  }

  @PutMapping("/driver")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@RequestParam Long id, @RequestParam String newDriverPhone) {
    driveRecordService.update(id, newDriverPhone);
  }

  @PutMapping("/users/bulk")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@RequestBody List<DriveRecordUserLinksDto> connections) {
    driveRecordService.addUsersToRecord(connections);
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@RequestParam Long id) {
    driveRecordService.deleteById(id);
  }

  @DeleteMapping("/users")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUsersFromRecord(@RequestParam Long id, @RequestBody List<Long> userIds) {
    driveRecordService.deleteUsersFromRecord(id, userIds);
  }
}
