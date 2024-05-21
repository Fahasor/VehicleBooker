package vehbook.vehiclebooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vehbook.vehiclebooker.model.DriveRecord;
import vehbook.vehiclebooker.service.DriveRecordService;

import java.util.List;

@RestController
@RequestMapping("/pending")
public class DriveRecordController {
    private final DriveRecordService driveRecordService;

    @Autowired
    public DriveRecordController(DriveRecordService service){
        driveRecordService = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody DriveRecord driveRecord) {
        driveRecordService.create(driveRecord);
    }
    @GetMapping
    public DriveRecord getById(@RequestParam long id) {
        return driveRecordService.findById(id);
    }
    @GetMapping("/all")
    public List<DriveRecord> getAll() {
        return driveRecordService.getAll();
    }
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody DriveRecord driveRecord) {
        driveRecordService.update(driveRecord);
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
