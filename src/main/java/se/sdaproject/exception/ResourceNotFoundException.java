package se.sdaproject.exception;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.lang.RuntimeException;
import org.springframework.http.HttpStatus;

/**
 * Creating ResourceNotFoundException.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
}