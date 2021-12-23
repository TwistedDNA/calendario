package learn.by.practice.calendario.timeslot;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static learn.by.practice.calendario.timeslot.Timeslot.TIMESLOT_DURATION_MINUTES;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeslotTest {

    @Test
    public void shouldThrowExceptionOnNotRoundStartTime(){
        LocalDateTime start =  LocalDateTime.of(2021,12,2,7,58);
        LocalDateTime end =  start.plusMinutes(TIMESLOT_DURATION_MINUTES);

        Exception exception = assertThrows(InvalidTimeSlotException.class, () -> new Timeslot(start, end));
        assertTrue(exception.getMessage().contains("Start timestamp minutes must be rounded to"));
    }

    @Test
    public void shouldThrowExceptionOnNotRoundEndTime(){
        LocalDateTime start =  LocalDateTime.of(2021,12,2,8,0);
        LocalDateTime end =  start.plusMinutes(TIMESLOT_DURATION_MINUTES-1);

        Exception exception = assertThrows(InvalidTimeSlotException.class, () -> new Timeslot(start, end));
        assertTrue(exception.getMessage().contains("End timestamp minutes must be rounded to "));
    }

    @Test
    public void shouldThrowExceptionOnLongerTimeslot(){
        LocalDateTime start =  LocalDateTime.of(2021,12,2,8,0);
        LocalDateTime end =  start.plusMinutes(TIMESLOT_DURATION_MINUTES * 2);

        Exception exception = assertThrows(InvalidTimeSlotException.class, () -> new Timeslot(start, end));
        assertTrue(exception.getMessage().contains("Timeslot duration should be "));
    }

    @Test
    public void shouldCreateValidTimeslot(){
        LocalDateTime start =  LocalDateTime.of(2021,12,2,8,0);
        LocalDateTime end =  start.plusMinutes(TIMESLOT_DURATION_MINUTES);

        Timeslot timeslot = new Timeslot(start, end);
    }

}