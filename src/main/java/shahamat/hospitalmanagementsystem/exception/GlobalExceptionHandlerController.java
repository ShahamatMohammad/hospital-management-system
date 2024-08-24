package shahamat.hospitalmanagementsystem.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandlerController {

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
                return super.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults().excluding(ErrorAttributeOptions.Include.EXCEPTION));
            }
        };
    }

    @ExceptionHandler(CustomException.class)
    public void handleCustomException(HttpServletResponse res, CustomException ex) throws IOException {
        res.setStatus(ex.getHttpStatus().value());
        Map<String, Object> response = new HashMap<>();
        response.put("errorCode", ex.getErrorCode());
        response.put("message", ex.getMessage());
        response.put("status", ex.getHttpStatus().value());
        res.setContentType("application/json");
        res.getWriter().write(new ObjectMapper().writeValueAsString(response));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public void handleAccessDeniedException(HttpServletResponse res) throws IOException {
        res.setStatus(HttpStatus.FORBIDDEN.value());
        Map<String, Object> response = new HashMap<>();
        response.put("errorCode", 1001); // Custom error code for access denied
        response.put("message", "Access denied");
        response.put("status", HttpStatus.FORBIDDEN.value());
        res.setContentType("application/json");
        res.getWriter().write(new ObjectMapper().writeValueAsString(response));
    }

    @ExceptionHandler(Exception.class)
    public void handleException(HttpServletResponse res) throws IOException {
        res.setStatus(HttpStatus.BAD_REQUEST.value());
        Map<String, Object> response = new HashMap<>();
        response.put("errorCode", 1002); // Custom error code for generic errors
        response.put("message", "Something went wrong");
        response.put("status", HttpStatus.BAD_REQUEST.value());
        res.setContentType("application/json");
        res.getWriter().write(new ObjectMapper().writeValueAsString(response));
    }
}