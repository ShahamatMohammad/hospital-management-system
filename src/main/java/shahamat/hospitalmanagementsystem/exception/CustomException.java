package shahamat.hospitalmanagementsystem.exception;


import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String message;
    private final HttpStatus httpStatus;
    private final int errorCode; // Add errorCode field

    public CustomException(String message, HttpStatus httpStatus, int errorCode) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.errorCode = errorCode; // Initialize errorCode
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getErrorCode() { // Add getter for errorCode
        return errorCode;
    }
}
