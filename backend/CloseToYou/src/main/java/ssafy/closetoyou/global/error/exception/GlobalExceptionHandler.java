package ssafy.closetoyou.global.error.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ssafy.closetoyou.global.common.response.ErrorResponse;
import ssafy.closetoyou.global.error.errorcode.CommonErrorCode;
import ssafy.closetoyou.global.error.errorcode.ErrorCode;
import ssafy.closetoyou.global.error.errorcode.UserErrorCode;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CloseToYouException.class)
    public ResponseEntity<ErrorResponse> handleCustomExceptionHandler(CloseToYouException e, HttpServletRequest request) {
        ErrorCode code = e.getErrorCode();
        ErrorResponse errorResponse = new ErrorResponse(code.getStatus(), code.name(), code.getMessage());
        return ResponseEntity.status(code.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception e) {
        log.warn("handleAllException", e);
        CommonErrorCode commonErrorCode = CommonErrorCode.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(commonErrorCode);
    }

    @ExceptionHandler(MailException.class)
    public ResponseEntity<Object> handleMailException(MailException mailException) {
        UserErrorCode userErrorCode = UserErrorCode.MAIL_SEND_FAIL;
        return handleExceptionInternal(userErrorCode);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        log.warn("handleIllegalArgument", e);
        CommonErrorCode commonErrorCode = CommonErrorCode.INVALID_PARAMETER;
        return handleExceptionInternal(e, commonErrorCode);
    }

    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getStatus())
                .body(makeErrorResponse(errorCode));
    }


    private ResponseEntity<Object> handleExceptionInternal(BindException e, ErrorCode errorCode)  {
        return ResponseEntity.status(errorCode.getStatus())
                .body(makeErrorResponse(e, errorCode));
    }

    private ErrorResponse makeErrorResponse(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .statusCode(errorCode.getStatus())
                .message(errorCode.getMessage())
                .build();
    }


    private ErrorResponse makeErrorResponse(BindException e, ErrorCode errorCode) {
        List<ErrorResponse.ValidationError> validationErrorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ErrorResponse.ValidationError::of)
                .collect(Collectors.toList());

        return ErrorResponse.builder()
                .statusCode(errorCode.getStatus())
                .message(errorCode.getMessage())
                .errors(validationErrorList)
                .build();
    }

}
