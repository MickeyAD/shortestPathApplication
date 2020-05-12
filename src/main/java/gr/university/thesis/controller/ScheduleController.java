package gr.university.thesis.controller;

import gr.university.thesis.entity.Schedule;
import gr.university.thesis.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/schedule/add")
    public ResponseEntity<Schedule> addSchedule(@RequestBody Schedule schedule) {
        return scheduleService.addSchedule(schedule);
    }

    @PutMapping("/schedule/update/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable int id, @RequestBody Schedule schedule) {
        return scheduleService.updateSchedule(id, schedule);
    }

    @GetMapping("/schedule/view/{id}")
    public ResponseEntity<Schedule> viewSchedule(@PathVariable int id) {
        return scheduleService.findScheduleById(id);
    }

    @GetMapping("/schedule/viewAll")
    public ResponseEntity<List<Schedule>> viewAllSchedules() {
        return scheduleService.findAllSchedules();
    }

    @DeleteMapping("/schedule/delete/{id}")
    public ResponseEntity<HttpStatus> deleteSchedule(@PathVariable int id) {
        return scheduleService.deleteScheduleById(id);
    }

    @DeleteMapping("/schedule/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllSchedule() {
        return scheduleService.deleteAllSchedules();
    }
}
