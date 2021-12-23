package learn.by.practice.calendario.timeslot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static learn.by.practice.calendario.timeslot.TimeslotController.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ComponentScan
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
                .andExpect(status().isOk())
                .andExpect(content().json(emptyList));
    }

    @Test
    public void shouldBookATimeslot() throws Exception {

        LocalDateTime start = LocalDateTime.of(2021, 12, 23, 16, 15);
        LocalDateTime end = LocalDateTime.of(2021, 12, 23, 16, 30);

        String message = "Fifteen minute stretch session";
        String expectedResponse = "{\"start\":\"2021-12-23T16:15:00\",\"end\":\"2021-12-23T16:30:00\",\"message\":\"\\\"Fifteen minute stretch session\\\"\"}";
        this.mockMvc.perform(
                        post(TIMESLOTS_URL + BOOK_URL)
                                .param("start", DateTimeFormatter.ISO_DATE_TIME.format(start))
                                .param("end", DateTimeFormatter.ISO_DATE_TIME.format(end))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(om.writeValueAsString(message))
                )
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(expectedResponse));

    }
}