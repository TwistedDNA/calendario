package learn.by.practice.calendario;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
class HealthcheckController {

    public static final String SHORT_STATUS_OK = "{ \"status\": \"OK\" }";
    public static final String FULL_HEALTHCHECK_TEMPLATE = "{ \"currentTime\": \"%s\", \"status\": \"OK\" }";

    @GetMapping(value = "/healthcheck")
    @ResponseBody
    public ResponseEntity<String> healthcheck(@RequestParam("format") String format) {
        if (!"short".equals(format) && !"full".equals(format)) {
            return ResponseEntity.badRequest().build();
        }
        String responseBody = format.equals("short") ? SHORT_STATUS_OK : String.format(FULL_HEALTHCHECK_TEMPLATE, ZonedDateTime.now());

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseBody);
    }

    @PutMapping(value = "/healthcheck")
    public ResponseEntity<String> healthcheckPut() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    @PostMapping(value = "/healthcheck")
    public ResponseEntity<String> healthcheckPost() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    @DeleteMapping(value = "/healthcheck")
    public ResponseEntity<String> healthcheckDelete() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }
}