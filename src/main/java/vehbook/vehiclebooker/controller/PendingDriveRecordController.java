package vehbook.vehiclebooker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vehbook.vehiclebooker.model.PendingDriveRecord;
import vehbook.vehiclebooker.service.PendingDriveRecordService;

@RestController
@RequestMapping("/drivers")
public class PendingDriveRecordController {
    private final PendingDriveRecordService driveRecordService;

    @Autowired
    public PendingDriveRecordController(PendingDriveRecordService service){
        driveRecordService = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody PendingDriveRecord pendingDriveRecord) {
        driveRecordService.update(pendingDriveRecord);
    }
    @GetMapping
    public PendingDriveRecord getByPhoneNumber(@RequestParam long id) {
        return driveRecordService.findById(id);
    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@RequestParam Long id) {
        driveRecordService.deleteById(id);
    }
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody PendingDriveRecord pendingDriveRecord) {
        driveRecordService.create(pendingDriveRecord);
    }
}
