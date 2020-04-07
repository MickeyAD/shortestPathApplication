package gr.university.thesis.controller;

import gr.university.thesis.entity.Schedule;
import gr.university.thesis.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/addSchedule")
    public Schedule addSchedule(@ModelAttribute Schedule schedule) {
        return scheduleService.addSchedule(schedule);
    }

    @PostMapping("/updateSchedule")
    public Schedule updateSchedule(@ModelAttribute Schedule schedule) {
        return scheduleService.updateSchedule(schedule);
    }

    @GetMapping("/viewSchedule/{id}")
    public Schedule viewSchedule(@PathVariable int id) {
        return scheduleService.findScheduleById(id);
    }

    @GetMapping("/viewAllSchedules")
    public List<Schedule> viewAllSchedules() {
        return scheduleService.findAllSchedules();
    }

    @DeleteMapping("/deleteSchedule/{id}")
    public void deleteSchedule(@PathVariable int id) {
        scheduleService.deleteScheduleById(id);
    }

    @DeleteMapping("/deleteAllSchedules")
    public void deleteAllSchedule() {
        scheduleService.deleteAllSchedules();
    }
}
