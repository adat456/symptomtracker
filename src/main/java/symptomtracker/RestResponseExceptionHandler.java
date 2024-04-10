package symptomtracker;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import symptomtracker.exceptions.InvalidAllowableValues;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {InvalidAllowableValues.class})
    protected ResponseEntity<String> handleInvalidAllowableValues(InvalidAllowableValues ex) {
        // https://stackoverflow.com/questions/7939137/what-http-status-code-should-be-used-for-wrong-input
        return ResponseEntity
                .status(HttpStatusCode.valueOf(422))
                .body(ex.getMessage());
    };

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<String> handleEntityNotFound (EntityNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatusCode.valueOf(404))
                .body(ex.getMessage());
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    protected ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        // https://stackoverflow.com/questions/25015592/whats-the-best-return-code-for-unique-violations
        return ResponseEntity
                .status(HttpStatusCode.valueOf(409))
                .body(ex.getMostSpecificCause().getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field= ((FieldError)error).getField();
            String errMsg = error.getDefaultMessage();
            errors.put(field, errMsg);
        });
        return handleExceptionInternal(ex, errors, headers, status, request);
    }
}
