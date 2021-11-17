package project.errorhandling;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Value
public class ApiError {

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss"
    )
    LocalDateTime timestamp;

    HttpStatus httpStatus;

    int status;

    String error;

    String message;

    String path;

    ApiError(HttpStatus httpStatus, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.httpStatus = httpStatus;
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = message;
        this.path = path;
    }

    HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
