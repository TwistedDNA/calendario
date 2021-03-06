package learn.by.practice.calendario.timeslot;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TimeslotStorage {
    private Map<Timeslot, String> storage;

    public TimeslotStorage(Map<Timeslot, String> storage) {
        this.storage = storage;
    }

    public Optional<TimeslotWithMessage> findTimeslot(Timeslot timeslot) {
        if(!storage.containsKey(timeslot)){
            return Optional.empty();
        }
        String message = storage.get(timeslot);
        return Optional.of(new TimeslotWithMessage(timeslot, message));
    }

    public void addTimeslot(Timeslot timeslot, String message) {
        storage.put(timeslot, message);
    }

    public void removeTimeslot(Timeslot timeslot) {
        storage.remove(timeslot);
    }

    public List<TimeslotWithMessage> listTimeslots() {
        return storage.entrySet()
                .stream()
                .map((entry) -> new TimeslotWithMessage(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
    }
}
