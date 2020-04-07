package gr.university.thesis.service;

import gr.university.thesis.entity.Schedule;
import gr.university.thesis.exception.ResourceNotFoundException;
import gr.university.thesis.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    public Schedule addSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public Schedule updateSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public Schedule findScheduleById(Integer id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isPresent()) {
            return schedule.get();
        } else {
            throw new ResourceNotFoundException("Could not find schedule with id: " + id);
        }
    }

    public List<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

    public void deleteScheduleById(Integer id) {
        scheduleRepository.deleteById(id);
    }

    public void deleteAllSchedules() {
        scheduleRepository.deleteAll();
    }

}
