package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleNotValidException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(fieldErrors.stream().map(NotValidErrorResponse::new).toList());
    }

    private record NotValidErrorResponse(String field, String message) {
        public NotValidErrorResponse(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
