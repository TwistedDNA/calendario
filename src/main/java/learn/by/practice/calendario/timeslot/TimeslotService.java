package learn.by.practice.calendario.timeslot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeslotService {

    @Autowired
    private TimeslotStorage storage;

    public Optional<TimeslotWithMessage> bookTimeslot(Timeslot timeslot, String message) {
        Optional<TimeslotWithMessage> found = storage.findTimeslot(timeslot);
        return  Optional.of(found.orElse(storage.addTimeslot(timeslot,message)));
    }

    public void cancelTimeslotBooking(Timeslot timeslot) {
        storage.removeTimeslot(timeslot);
    }

    public List<TimeslotWithMessage> listAllBookedTimeslots() {
        return storage.listTimeslots();
    }
}
