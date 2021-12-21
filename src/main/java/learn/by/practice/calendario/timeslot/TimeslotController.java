package learn.by.practice.calendario.timeslot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller("timeslots")
public class TimeslotController {

    @Autowired
    private TimeslotService timeslotService;

    @PostMapping("/book")
    public TimeslotWithMessage bookTimeslot(@RequestBody LocalDateTime start, @RequestBody LocalDateTime end, @RequestBody String message) {
        try {
            Timeslot timeslot = new Timeslot(start, end);
            Optional<TimeslotWithMessage> saved = timeslotService.bookTimeslot(timeslot, message);
            return saved.orElse(null); //TODO return "already booked" status
        } catch (InvalidTimeSlotException e) {
            return null; //TODO return "invalid parameters" status
        }
    }

    @PostMapping("/cancel")
    public void cancelTimeslotBooking(@RequestBody LocalDateTime start, @RequestBody LocalDateTime end) {
        Timeslot timeslot = new Timeslot(start, end);
        timeslotService.cancelTimeslotBooking(timeslot);
    }

    @GetMapping("/list")
    public List<TimeslotWithMessage> getAllBookedTimeslots() {
        return timeslotService.listAllBookedTimeslots();
    }
}
