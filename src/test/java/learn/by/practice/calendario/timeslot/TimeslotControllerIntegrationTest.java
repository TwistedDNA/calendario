package learn.by.practice.calendario.timeslot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static learn.by.practice.calendario.timeslot.TimeslotController.LIST_URI;
import static learn.by.practice.calendario.timeslot.TimeslotController.TIMESLOTS_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TimeslotTestConfiguration.class })
@WebMvcTest(TimeslotController.class)
@WebAppConfiguration
public class TimeslotControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper om;

    @Test
    public void shouldReturnEmptyList() throws Exception {
        String emptyList = om.writeValueAsString(new ArrayList());
        System.out.println("Empty list:" + emptyList);
        this.mockMvc.perform(
                get(TIMESLOTS_URL + LIST_URI))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(emptyList));
    }
}