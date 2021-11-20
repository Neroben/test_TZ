package project.errorhandling;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import project.errorhandling.exception.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class ApiRestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String LOGGING_EXCEPTION_MESSAGE = "{}: {}";

    private static ResponseEntity<ApiError> buildResponse(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }

    @ExceptionHandler(UsernameExistException.class)
    ResponseEntity<ApiError> handleUsernameExistException(UsernameExistException ex,
                                                                  HttpServletRequest request) {
        log.warn(LOGGING_EXCEPTION_MESSAGE, ex.getMessage(), ExceptionUtils.getRootCauseMessage(ex));
        ApiError apiError = new ApiError(BAD_REQUEST, ex.getMessage(), request.getRequestURI());
        return buildResponse(apiError);
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException ex,
                                                                  HttpServletRequest request) {
        log.warn(LOGGING_EXCEPTION_MESSAGE, ex.getMessage(), ExceptionUtils.getRootCauseMessage(ex));
        ApiError apiError = new ApiError(BAD_REQUEST, ex.getMessage(), request.getRequestURI());
        return buildResponse(apiError);
    }

    @ExceptionHandler(OrderLinePermissionException.class)
    ResponseEntity<ApiError> handleOrderLinePermissionException(OrderLinePermissionException ex,
                                                         HttpServletRequest request) {
        log.warn(LOGGING_EXCEPTION_MESSAGE, ex.getMessage(), ExceptionUtils.getRootCauseMessage(ex));
        ApiError apiError = new ApiError(BAD_REQUEST, ex.getMessage(), request.getRequestURI());
        return buildResponse(apiError);
    }

    @ExceptionHandler(ActualPriceNotFoundException.class)
    ResponseEntity<ApiError> handleActualPriceNotFoundException(ActualPriceNotFoundException ex,
                                                                HttpServletRequest request) {
        log.warn(LOGGING_EXCEPTION_MESSAGE, ex.getMessage(), ExceptionUtils.getRootCauseMessage(ex));
        ApiError apiError = new ApiError(BAD_REQUEST, ex.getMessage(), request.getRequestURI());
        return buildResponse(apiError);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    ResponseEntity<ApiError> handleProductNotFoundException(ProductNotFoundException ex,
                                                                HttpServletRequest request) {
        log.warn(LOGGING_EXCEPTION_MESSAGE, ex.getMessage(), ExceptionUtils.getRootCauseMessage(ex));
        ApiError apiError = new ApiError(BAD_REQUEST, ex.getMessage(), request.getRequestURI());
        return buildResponse(apiError);
    }

    @ExceptionHandler(PricePeriodException.class)
    ResponseEntity<ApiError> handlePricePeriodException(PricePeriodException ex,
                                                            HttpServletRequest request) {
        log.warn(LOGGING_EXCEPTION_MESSAGE, ex.getMessage(), ExceptionUtils.getRootCauseMessage(ex));
        ApiError apiError = new ApiError(BAD_REQUEST, ex.getMessage(), request.getRequestURI());
        return buildResponse(apiError);
    }

    @ExceptionHandler(ProductPriceNotFoundException.class)
    ResponseEntity<ApiError> handleProductPriceNotFoundException(ProductPriceNotFoundException ex,
                                                        HttpServletRequest request) {
        log.warn(LOGGING_EXCEPTION_MESSAGE, ex.getMessage(), ExceptionUtils.getRootCauseMessage(ex));
        ApiError apiError = new ApiError(BAD_REQUEST, ex.getMessage(), request.getRequestURI());
        return buildResponse(apiError);
    }
}
