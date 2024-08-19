package ssafy.closetoyou.global.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class ErrorResponse {

    private final HttpStatus statusCode;
    private final String error;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ValidationError> errors;

    @Builder
    public ErrorResponse(HttpStatus statusCode, String error, String message, List<ValidationError> errors) {
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
        this.errors = errors;
    }

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class ValidationError {
        private final String field;
        private final String message;


        public static ValidationError of(final FieldError fieldError) {
            return ValidationError.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage()).
                    build();
        }
    }
}

