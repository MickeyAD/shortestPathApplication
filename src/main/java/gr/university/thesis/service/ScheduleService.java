package gr.university.thesis.service;

import gr.university.thesis.entity.Schedule;
import gr.university.thesis.exception.ResourceExistsException;
import gr.university.thesis.exception.ResourceNotFoundException;
import gr.university.thesis.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public ResponseEntity<Schedule> addSchedule(Schedule schedule) {
        try {
            Schedule newSchedule = new Schedule();
            newSchedule.setTimetable(schedule.getTimetable());
            scheduleRepository.save(newSchedule);
            return new ResponseEntity<>(newSchedule, HttpStatus.CREATED);
        } catch (ResourceExistsException resourceExistsException) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<Schedule> updateSchedule(int id, Schedule scheduleDetails) {
        try {
            Optional<Schedule> schedule = scheduleRepository.findById(id);
            Schedule currentSchedule = schedule.get();
            currentSchedule.setTimetable(scheduleDetails.getTimetable());
            scheduleRepository.save(currentSchedule);
            return new ResponseEntity<>(currentSchedule, HttpStatus.CREATED);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Schedule> findScheduleById(int id) {
        try {
            Optional<Schedule> scheduleOptional = scheduleRepository.findById(id);
            Schedule schedule = scheduleOptional.get();
            return new ResponseEntity<>(schedule, HttpStatus.OK);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Schedule>> findAllSchedules() {
        try {
            List<Schedule> scheduleList = new ArrayList<>();
            scheduleRepository.findAll().forEach(scheduleList::add);
            if (!scheduleList.isEmpty()) {
                return new ResponseEntity<>(scheduleList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteScheduleById(int id) {
        try {
            scheduleRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllSchedules() {
        try {
            scheduleRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
