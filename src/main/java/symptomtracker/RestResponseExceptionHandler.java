package symptomtracker;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import symptomtracker.exceptions.InvalidAllowableValues;

@ControllerAdvice
public class RestResponseExceptionHandler {
    @ExceptionHandler(value = {InvalidAllowableValues.class})
    protected ResponseEntity<String> handleInvalidAllowableValues(InvalidAllowableValues e) {
        // https://stackoverflow.com/questions/7939137/what-http-status-code-should-be-used-for-wrong-input
        return ResponseEntity
                .status(HttpStatusCode.valueOf(422))
                .body(e.getMessage());
    };
}
