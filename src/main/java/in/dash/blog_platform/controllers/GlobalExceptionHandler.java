package in.dash.blog_platform.controllers;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import in.dash.blog_platform.dtos.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleGlobalExceptions(RuntimeException e,WebRequest req){
        ErrorResponse response=new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleIllegalArgumentExceptions(RuntimeException e,WebRequest req){
        ErrorResponse response=new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundException(RuntimeException e,WebRequest req){
        ErrorResponse response=new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(RuntimeException e,WebRequest req){
        ErrorResponse response=new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                e.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
    }

}