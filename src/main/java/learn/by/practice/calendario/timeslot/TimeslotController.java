package learn.by.practice.calendario.timeslot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping(TimeslotController.TIMESLOTS_URL)
public class TimeslotController {

    public static final String TIMESLOTS_URL = "/timeslots";
    public static final String BOOK_URL = "/book";
    public static final String CANCEL_URL = "/cancel";
    public static final String LIST_URI = "/list";

    @Autowired
    private TimeslotService timeslotService;

    @Autowired
    ObjectMapper om;

    @PostMapping(BOOK_URL)
    public ResponseEntity<String> bookTimeslot(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
                                                            @RequestBody String message) {
        try {
            Timeslot timeslot = new Timeslot(start, end);
            Optional<TimeslotWithMessage> booked = timeslotService.bookTimeslot(timeslot, message);
            String payload = booked.isEmpty() ? "" : om.writeValueAsString(booked.get());
            return ResponseEntity.accepted().contentType(MediaType.APPLICATION_JSON).body(payload);
        } catch (InvalidTimeSlotException | JsonProcessingException e) {
            // TODO log
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(CANCEL_URL)
    public void cancelTimeslotBooking(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        Timeslot timeslot = new Timeslot(start, end);
        timeslotService.cancelTimeslotBooking(timeslot);
    }

    @GetMapping(LIST_URI)
    public ResponseEntity<String> getAllBookedTimeslots() throws JsonProcessingException {
        String bookedTimeslots = om.writeValueAsString(timeslotService.listAllBookedTimeslots());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(bookedTimeslots);
    }
}
