package project.errorhandling;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class ApiRestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String LOGGING_EXCEPTION_MESSAGE = "{}: {}";

    private static ResponseEntity<ApiError> buildResponse(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiError> handleUpdateSupplyAgreementException(Exception ex,
                                                                  HttpServletRequest request) {
        log.warn(LOGGING_EXCEPTION_MESSAGE, ex.getMessage(), ExceptionUtils.getRootCauseMessage(ex));
        ApiError apiError = new ApiError(BAD_REQUEST, ex.getMessage(), request.getRequestURI());
        return buildResponse(apiError);
    }
}
