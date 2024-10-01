package net.projects.MovieManagement.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.projects.MovieManagement.dto.response.ApiErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            Exception.class,
            ObjectoNotFoundException.class,
            InvalidPasswordException.class,
            MethodArgumentTypeMismatchException.class,
            MethodArgumentNotValidException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMessageNotReadableException.class,
            DuplicateRatingException.class
    })
    public ResponseEntity<ApiErrorDTO> handleAllException(Exception exception, HttpServletRequest request, HttpServletResponse response){

        ZoneId zoneId = ZoneId.of("America/Bogota");
        LocalDateTime timestamp = LocalDateTime.now(zoneId);

        if (exception instanceof ObjectoNotFoundException objectoNotFoundException) {
            return this.handleObjectNotFoundException(objectoNotFoundException, request, response, timestamp);
        }      

        if (exception instanceof InvalidPasswordException invalidPasswordException) {
            return this.handleInvalidPassworException(invalidPasswordException, request, response, timestamp);
        }

        if (exception instanceof MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
            return this.handledMethodArgumentTypeMismatchException(methodArgumentTypeMismatchException, request, response, timestamp);
        }

        if (exception instanceof MethodArgumentNotValidException methodArgumentNotValidException) {
            return this.handledMethodArgumentNotValidException(methodArgumentNotValidException, request, response, timestamp);
        }

        if (exception instanceof HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {
            return this.handledHttpRequestMethodNotSupportedException(httpRequestMethodNotSupportedException, request, response, timestamp);
        }

        if (exception instanceof HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException) {
            return this.handleHttpMediaTypeNotSupportedException(httpMediaTypeNotSupportedException, request, response, timestamp);
        }

        if (exception instanceof HttpMessageNotReadableException httpMessageNotReadableException) {
            return this.handleHttpMessageNotReadableException(httpMessageNotReadableException, request, response, timestamp);
        }

        if (exception instanceof DuplicateRatingException duplicateRatingException) {
            return  this.handleDuplicateRatingException(duplicateRatingException, request, response, timestamp);
        }

        return this.handleException(exception, request, response, timestamp);

    }

    private ResponseEntity<ApiErrorDTO> handleInvalidPassworException(InvalidPasswordException invalidPasswordException, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamp){

        int httpStatus = HttpStatus.BAD_REQUEST.value();

        ApiErrorDTO apiErrorDto = new ApiErrorDTO();
        apiErrorDto.setHttpCode(httpStatus);
        apiErrorDto.setUrl(request.getRequestURL().toString());
        apiErrorDto.setHttpMethod(request.getMethod());
        apiErrorDto.setMessage("Invalid password: the provided password does not meet the required criteria, " + invalidPasswordException.getErrorDescription());
        apiErrorDto.setBackendMessage(invalidPasswordException.getMessage());
        apiErrorDto.setTimestamp(timestamp);
        apiErrorDto.setDetails(null);

        return ResponseEntity.status(httpStatus).body(apiErrorDto);
    }

    private ResponseEntity<ApiErrorDTO> handleObjectNotFoundException(ObjectoNotFoundException objectoNotFoundException, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamp){

        int httpStatus = HttpStatus.NOT_FOUND.value();

        ApiErrorDTO apiErrorDto = new ApiErrorDTO();
        apiErrorDto.setHttpCode(httpStatus);
        apiErrorDto.setUrl(request.getRequestURL().toString());
        apiErrorDto.setHttpMethod(request.getMethod());
        apiErrorDto.setMessage("I'm sorry, the request information could not be found. Please check the URL or try another search" + objectoNotFoundException.getMessage());
        apiErrorDto.setBackendMessage(objectoNotFoundException.getMessage());
        apiErrorDto.setTimestamp(timestamp);
        apiErrorDto.setDetails(null);

        return ResponseEntity.status(httpStatus).body(apiErrorDto);
    }

    private ResponseEntity<ApiErrorDTO> handleException(Exception exception, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamp){

        int httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();

        ApiErrorDTO apiErrorDto = new ApiErrorDTO();
        apiErrorDto.setHttpCode(httpStatus);
        apiErrorDto.setUrl(request.getRequestURL().toString());
        apiErrorDto.setHttpMethod(request.getMethod());
        apiErrorDto.setMessage("Opps! Something went wrong on our server. Please try again later ");
        apiErrorDto.setBackendMessage(exception.getMessage());
        apiErrorDto.setTimestamp(timestamp);
        apiErrorDto.setDetails(null);

        return ResponseEntity.status(httpStatus).body(apiErrorDto);
    }

    private ResponseEntity<ApiErrorDTO> handledMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamp){

        int httpStatus = HttpStatus.BAD_REQUEST.value();
        Object valueRejected = methodArgumentTypeMismatchException.getValue();
        String propertyName = methodArgumentTypeMismatchException.getName();

        ApiErrorDTO apiErrorDto = new ApiErrorDTO();
        apiErrorDto.setHttpCode(httpStatus);
        apiErrorDto.setUrl(request.getRequestURL().toString());
        apiErrorDto.setHttpMethod(request.getMethod());
        apiErrorDto.setMessage("Invalid Request: The provided value '" + valueRejected + "' does not have the expected data type for the '" + propertyName + "'.");
        apiErrorDto.setBackendMessage(methodArgumentTypeMismatchException.getMessage());
        apiErrorDto.setTimestamp(timestamp);
        apiErrorDto.setDetails(null);

        return ResponseEntity.status(httpStatus).body(apiErrorDto);
    }

    private ResponseEntity<ApiErrorDTO> handledMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamp){

        int httpStatus = HttpStatus.BAD_REQUEST.value();

        List<ObjectError> errors = methodArgumentNotValidException.getAllErrors();
        List<String> details = errors.stream().map(error -> {

            if (error instanceof FieldError fieldError) {
                return fieldError.getField() + ": " + fieldError.getDefaultMessage();
            }

            return error.getDefaultMessage();

        }).toList();

        ApiErrorDTO apiErrorDto = new ApiErrorDTO();
        apiErrorDto.setHttpCode(httpStatus);
        apiErrorDto.setUrl(request.getRequestURL().toString());
        apiErrorDto.setHttpMethod(request.getMethod());
        apiErrorDto.setMessage("The request contains invalid or incomplete parameters. Please verify and provide the required information before trying again.");
        apiErrorDto.setBackendMessage(methodArgumentNotValidException.getMessage());
        apiErrorDto.setTimestamp(timestamp);
        apiErrorDto.setDetails(details);

        return ResponseEntity.status(httpStatus).body(apiErrorDto);
    }

    private ResponseEntity<ApiErrorDTO> handledHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamp){

        int httpStatus = HttpStatus.METHOD_NOT_ALLOWED.value();

        ApiErrorDTO apiErrorDto = new ApiErrorDTO();
        apiErrorDto.setHttpCode(httpStatus);
        apiErrorDto.setUrl(request.getRequestURL().toString());
        apiErrorDto.setHttpMethod(request.getMethod());
        apiErrorDto.setMessage("Oops! Method not allowed. Check the HTTP method of your request.");
        apiErrorDto.setBackendMessage(httpRequestMethodNotSupportedException.getMessage());
        apiErrorDto.setTimestamp(timestamp);
        apiErrorDto.setDetails(null);

        return ResponseEntity.status(httpStatus).body(apiErrorDto);
    }

    private ResponseEntity<ApiErrorDTO> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamp){

        int httpStatus = HttpStatus.UNSUPPORTED_MEDIA_TYPE.value();

        ApiErrorDTO apiErrorDto = new ApiErrorDTO();
        apiErrorDto.setHttpCode(httpStatus);
        apiErrorDto.setUrl(request.getRequestURL().toString());
        apiErrorDto.setHttpMethod(request.getMethod());
        apiErrorDto.setMessage("Unsupported Media Type: The server is unable to process the requested entity in the format provided in the request. Supported media types are: " + httpMediaTypeNotSupportedException.getSupportedMediaTypes() +" and you send: " + httpMediaTypeNotSupportedException.getContentType() + httpMediaTypeNotSupportedException.getMessage());
        apiErrorDto.setBackendMessage(httpMediaTypeNotSupportedException.getMessage());
        apiErrorDto.setTimestamp(timestamp);
        apiErrorDto.setDetails(null);

        return ResponseEntity.status(httpStatus).body(apiErrorDto);

    }

    private ResponseEntity<ApiErrorDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamp){

        int httpStatus = HttpStatus.BAD_REQUEST.value();

        ApiErrorDTO apiErrorDto = new ApiErrorDTO();
        apiErrorDto.setHttpCode(httpStatus);
        apiErrorDto.setUrl(request.getRequestURL().toString());
        apiErrorDto.setHttpMethod(request.getMethod());
        apiErrorDto.setMessage("Oops! Error reading the HTTP message body. Make sure the request is correctly formatted and contains valid data." + httpMessageNotReadableException.getMessage());
        apiErrorDto.setBackendMessage(httpMessageNotReadableException.getMessage());
        apiErrorDto.setTimestamp(timestamp);
        apiErrorDto.setDetails(null);

        return ResponseEntity.status(httpStatus).body(apiErrorDto);

    }

    private ResponseEntity<ApiErrorDTO> handleDuplicateRatingException(DuplicateRatingException duplicateRatingException, HttpServletRequest request, HttpServletResponse response, LocalDateTime timestamp){

        int httpStatus = HttpStatus.CONFLICT.value();

        ApiErrorDTO apiErrorDto = new ApiErrorDTO();
        apiErrorDto.setHttpCode(httpStatus);
        apiErrorDto.setUrl(request.getRequestURL().toString());
        apiErrorDto.setHttpMethod(request.getMethod());
        apiErrorDto.setMessage(duplicateRatingException.getMessage());
        apiErrorDto.setBackendMessage(duplicateRatingException.getMessage());
        apiErrorDto.setTimestamp(timestamp);
        apiErrorDto.setDetails(null);

        return ResponseEntity.status(httpStatus).body(apiErrorDto);

    }

}
