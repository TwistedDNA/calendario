package learn.by.practice.calendario.timeslot;

public class TimeslotWithMessage extends Timeslot{
    public String message;
    public TimeslotWithMessage(Timeslot timeslot, String message) {
        super(timeslot.start, timeslot.end);
        this.message = message;
    }
}
