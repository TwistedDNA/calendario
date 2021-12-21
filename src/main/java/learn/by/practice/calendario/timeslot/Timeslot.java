package learn.by.practice.calendario.timeslot;

import java.time.LocalDateTime;

public class Timeslot {
    public static final int TIMESLOT_DURATION_MINUTES = 15;
    public LocalDateTime start;
    public LocalDateTime end;

    public Timeslot(LocalDateTime start, LocalDateTime end)  {
        if(start.getMinute() % TIMESLOT_DURATION_MINUTES != 0){
            String errorMessage = String.format("Start timestamp minutes must be rounded to %d minutes. Actual: %d",TIMESLOT_DURATION_MINUTES, start.getMinute());
            throw new InvalidTimeSlotException(errorMessage);
        }
        if(end.getMinute() % TIMESLOT_DURATION_MINUTES != 0){
            String errorMessage = String.format("End timestamp minutes must be rounded to %d minutes. Actual: %d", TIMESLOT_DURATION_MINUTES, end.getMinute());
            throw new InvalidTimeSlotException(errorMessage);
        }
        if(!start.plusMinutes(TIMESLOT_DURATION_MINUTES).equals(end)){
            String errorMessage = String.format("Timeslot duration should be %d minutes.",TIMESLOT_DURATION_MINUTES);
            throw new InvalidTimeSlotException(errorMessage);
        }
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "Timeslot{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Timeslot timeslot = (Timeslot) o;

        if (!start.equals(timeslot.start)) return false;
        return end.equals(timeslot.end);
    }

    @Override
    public int hashCode() {
        int result = start.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }
}
