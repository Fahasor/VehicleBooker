package vehbook.vehiclebooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vehbook.vehiclebooker.model.DriveRecord;
import vehbook.vehiclebooker.service.DriveRecordService;

@RestController
@RequestMapping("/pending")
public class DriveRecordController {
    private final DriveRecordService driveRecordService;

    @Autowired
    public DriveRecordController(DriveRecordService service){
        driveRecordService = service;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody DriveRecord record) {
        driveRecordService.create(record);
    }
    @GetMapping
    public DriveRecord getById(@RequestParam long id) {
        return driveRecordService.findById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody DriveRecord record) {
        driveRecordService.update(record);
    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@RequestParam Long id) {
        driveRecordService.deleteById(id);
    }
}
